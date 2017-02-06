package process.pack;

import bean.*;
import helper.date.MyTime;
import helper.file.MyFile;
import helper.file.MyScript;
import helper.log.MyLogTest;
import helper.svn.MySVN;
import interfaces.pack.AndroidPackInterface;
import service.*;

import java.io.File;

/**
 * Created by apple on 2016/12/15.
 */
public class AndroidPack implements AndroidPackInterface{

    String packname;

    SvnInfo svnInfo_android;
    Config config_android;
    Project project_android;
    GlobalSet globalSet;

    DealSvnInfo dealSvnInfo ;
    DealConfig dealConfig ;
    DealAndroidLog dealAndroidLog ;


    MyFile myFile;
    MySVN mysvn ;
    MyTime myTime ;
    MyScript myScript ;
    MyLogTest log ;

    private static AndroidPack androidpack;
    public static AndroidPack getInstance(String packname) {
        if (androidpack == null) {
            synchronized (AndroidPack.class) {
                if (androidpack == null) {
                    androidpack = new AndroidPack(packname);
                }
            }
        }
        return androidpack;
    }

    public static AndroidPack getInstance() {
        if (androidpack == null) {
            synchronized (AndroidPack.class) {
                if (androidpack == null) {
                    androidpack = new AndroidPack();
                }
            }
        }
        return androidpack;
    }
    public AndroidPack(String packname) {
        this.packname = packname;
        init();
    }

    public AndroidPack() {

        //init();
    }
    public void init(){
         dealSvnInfo = DealSvnInfo.getInstance();
         dealConfig = DealConfig.getInstance();
         dealAndroidLog =DealAndroidLog.getInstance();
         myFile= MyFile.getInstance();
         mysvn = MySVN.getInstance();
         myTime = MyTime.getInstance();
         myScript = MyScript.getInstance();
         log = MyLogTest.getInstance();
         globalSet  = DealGlobalset.getInstance().getGlobalset();
    }
    @Override
    public String packandroid(String packname){

        init();

        log.level("debug","**********************step 0 :start android pack**********************");
        svnInfo_android = DealSvnInfo.getInstance().getSvnInfoByName(packname);
        config_android = DealConfig.getInstance().getConfigByName(packname);
        project_android = DealProject.getInstance().getProjectByname(svnInfo_android.getProjectname());
        String falg_pack_android = "100";
        String pack_android_svn_version = null;
        String flag_svn_checkout = null;
        String pack_dir_onoff = null;
        String pack_android_time = null;
        String pack_dir_name = null;
        String pack_dir_path = null;
        String apk_abs_path = null;
        String flag_run_ant = null;
        String pack_excute_path_android = svnInfo_android.getLocal_path() + "/" + config_android.getExe_file_path();
//        String upload_enterprise_context = null;
//        String apk_to_down = null;
//        String update_file_context = null;
//        String flag_ready_ant = null;
//        String mapping_path = null;
//        String diff_com_path = null;//由于gradle与ant的代码路径不同，所以要定位文件，从com进行区分
//        String build_type = null;
        AndroidLog androidLog ;
       if(project_android.getOnoff().contains("0")){
           pack_dir_onoff = "local";
       }else if(project_android.getOnoff().contains("1")){
           pack_dir_onoff = "online";
       }else if(project_android.getOnoff().contains("2")){
           pack_dir_onoff = "yufa";
       }
        //1.1 删除源代码路径下的所有文件
        myFile.delFolder(svnInfo_android.getLocal_path());

        pack_android_svn_version = mysvn.getversion(svnInfo_android.getSvn_url(), globalSet.getSvnusername(), globalSet.getSvnpassword());
        pack_android_time = myTime.getTime();
        //打包完成后的文件名
        pack_dir_name = packname + "_" + pack_android_time + "_" + pack_android_svn_version + "_" + pack_dir_onoff;
        //包的所在路径
        log.level("debug","********** step 1 :svn doCheckOut. **********");
        pack_dir_path = config_android.getStore_root_path() + "/" + config_android.getStore_path() + "/" + pack_dir_name;
        log.level("debug","********** step 1:pack_dir_path is : " + pack_dir_path + "***********\ndo checkouting\n");


        flag_svn_checkout = mysvn.doCheckOut(svnInfo_android.getSvn_url(),globalSet.getSvnusername(), globalSet.getSvnpassword(),svnInfo_android.getLocal_path());
        log.level("debug","********** step 1.2 : check out source code success. from svn:"+flag_svn_checkout+"**********\n");
        if (!flag_svn_checkout.contains("error") && !flag_svn_checkout.contains("null")) {
            //根据build.gradle文件是否存在来判断源代码是否为gradle类型以及ant类型
            if(new File(svnInfo_android.getLocal_path()+"/build.gradle").exists()){
                //gradle编译前需要增加保存sdk路径的文件local.properties
                String local_properties_path = svnInfo_android.getLocal_path() + "/local.properties";
                File local_properties = new File(local_properties_path);
                if(!local_properties.exists()){
                    myFile.writerFile(local_properties_path,"sdk.dir="+globalSet.getSdkinfo());
                }
            }else{
            }
            //2.1.2 在本地路径下生成一个可执行文件
            myScript.create_exe_file(pack_excute_path_android, "echo 11", "debug");

            log.level("debug", "********** step 2.1 : change file context. **********");
            //使用数据库的配置替换is_test_version的值从true，为false
            String svndata = "\"\\\""+pack_android_time + "_" + pack_dir_onoff + "_" + svnInfo_android.getSvn_url()+"\\\"\"";
            svndata = svndata.replace("/","\\/");
            String command_before = "local_path=" + svnInfo_android.getLocal_path() +"\nsvn_version="+pack_android_svn_version+
                    "\nsvn_date="+svndata+"\n"+
                    "packinfo_onoff="+svnInfo_android.getOn_off()+"\n";


            log.level("debug","command_before:"+command_before+project_android.getExe_before_context()+"\n");
            myScript.run_command_file(command_before+project_android.getExe_before_context(), pack_excute_path_android, "debug");
            if(new File(svnInfo_android.getLocal_path()+"/build.gradle").exists()){
                apk_abs_path = "/app/build/outputs/apk/app-release.apk";
                //mapping_path = "/app/build/outputs/mapping/release";
            }


            //3. ******************** 编译 开始 *********************
            if(new File(svnInfo_android.getLocal_path()+"/build.gradle").exists()){
                log.level("debug","-------********** step 3 : Begin to build. start by gradle********------");
                String run_gradle_context = "cd "+svnInfo_android.getLocal_path()+"\ngradle assembleRelease";
                log.level("debug", "command_on_system_auto_set : " +run_gradle_context);
                flag_run_ant = myScript.run_command_file_env(run_gradle_context,pack_excute_path_android,"debug");
            }

            log.level("debug", "step 3:command_on_system_auto_set result  is :" + flag_run_ant);

            if (flag_run_ant.contains("400") && new File(svnInfo_android.getLocal_path() + apk_abs_path).exists()) {

                //4.1 新建文件夹并移动打好的包
                log.level("debug", "********** step 4 : mkdir new forder and move apk. **********");
                new File(pack_dir_path + "/online").mkdirs();

                //4.2 新包移动,需要区分ant编译以及gradle编译后的不同apk路径以及名称
                String zh1 = "cp " + svnInfo_android.getLocal_path() + apk_abs_path + " " + pack_dir_path + "/online/" + project_android.getProductname() + ".apk";


                //取消上传mapping文件夹功能
                //new File(config_android.getStore_root_path() + "/mapping/"+pack_android_svn_version).mkdirs();
                //String zh2 = "cp -r " + svnInfo_android.getLocal_path() + mapping_path + " " + config_android.getStore_root_path() + "/mapping/"+pack_android_svn_version;
                String zh2 ="";
                log.level("debug", "command_after_system_auto_set :" + zh1+"\n"+zh2);
                myScript.run_command_file(zh1+"\n"+zh2, pack_excute_path_android, "debug");
                zh2 = "apkname="+pack_dir_name+"\napkpath="+pack_dir_path + "/online/" + project_android.getProductname() + ".apk\n";
                if(project_android.getExe_after_context() != null && project_android.getExe_after_context().equals("null") && project_android.getExe_after_context().equals("")){
                    log.level("debug", "command_after_user_defined :" + zh2+project_android.getExe_after_context());
                    myScript.run_command_file(zh2+project_android.getExe_after_context(), pack_excute_path_android, "debug");
                }

//                log.level("debug", "********** step 5 : Upload apk to 136. **********");
//                5. 上传已经打好的包,debug、enterprise的选择,store_root_path、upload_path、forder_name
//
//                        update_file_context = "store_root_path=" + config_android.getStore_root_path() + "\nstore_path=" + config_android.getStore_path() + "\nupload_path=" + config_android.getUpload_path() + "\nforder_name=" + config_android.getForder_name() + "\n" + config_android.getExe_after_context();
//                        //update_file_context = "cd " + config_android.getStore_root_path() + "\n" + "./" + config_android.getUpload_path() + " " + pack_dir_name;
//                        run_command_file(update_file_context, pack_excute_path_android, "debug");
//                        log.level("debug", "update debug package command is : " + update_file_context);
//
//                        //6. 针对企业版进行的处理
//                        if(svnInfo_android.getEdition().contains("enterprise")){
//                            upload_enterprise_context = "cp "+config_android.getStore_root_path()+"/"+config_android.getStore_path()+"/"+config_android.getForder_name()
//                                    +"/online/"+config_android.getProduct_name()+".apk "+config_android.getEnterprise_path()+"/\n"+"cd "+config_android.getEnterprise_path()
//                                    +"sh "+config_android.getEnterprise_name()+" "+config_android.getProduct_name()+".apk";
//                            String result_upload_enterprise = run_command_file(upload_enterprise_context,pack_excute_path_android,"debug");
//                            log.level("debug","upload apk to enterprise command is : "+upload_enterprise_context+"\nand the result of upload is "+result_upload_enterprise);
//                        }

                //对AndroidLog数据库新增一条新打出来的包得记录
                config_android.setSvn_version(pack_android_svn_version);
                config_android.setForder_name(pack_dir_name);
                config_android.setPackage_time(myTime.getTime_database());
                dealConfig.updateConfig(config_android);

                androidLog = new AndroidLog();
                androidLog.setPackname(packname);
                androidLog.setMain_version(svnInfo_android.getMain_version());
                androidLog.setSvn_version(pack_android_svn_version);
                androidLog.setFile_name(config_android.getForder_name());
                androidLog.setStore_root_path(config_android.getStore_root_path());
                androidLog.setStore_path(config_android.getStore_path());
                androidLog.setSvn_url(svnInfo_android.getSvn_url());
                androidLog.setProduct_name(project_android.getProductname());
                androidLog.setEdition("");
                androidLog.setPlatform(svnInfo_android.getPlatform());
                androidLog.setMode("");
                androidLog.setOn_off(svnInfo_android.getOn_off());
                androidLog.setNums(1);
                androidLog.setIp("ip");
                androidLog.setPack_time(myTime.getTime_database());
                androidLog.setIsstore("0");
                androidLog.setViewflag("1");
                androidLog.setExone("");
                androidLog.setExtwo("");
                androidLog.setExthree("");
                dealAndroidLog.addAndroidLog(androidLog);

                //更新config_android数据库中的svn版本号以及新包存放路径


                falg_pack_android = "100";
            } else {
                falg_pack_android = "104";
            }//ant 编译文件出现异常

        } else {
            falg_pack_android = "101";
        }//svn checkout 异常

        return falg_pack_android;

    }
}
