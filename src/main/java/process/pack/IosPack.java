package process.pack;

import bean.*;
import helper.date.MyTime;
import helper.file.MyFile;
import helper.file.MyScript;
import helper.file.ZipUtil;
import helper.log.MyLogTest;
import helper.svn.MySVN;
import interfaces.pack.IosPackInterface;
import service.*;

import java.io.File;

/**
 * Created by apple on 2016/12/16.
 */
public class IosPack implements IosPackInterface {

    String packname;
    Project project_ios;
    GlobalSet globalSet = null;
    SvnInfo svnInfo_ios = null;
    Config config_ios = null;
    DealSvnInfo dealSvnInfo ;
    DealConfig dealConfig ;
    DealIosLog dealIosLog;

    MyFile myFile;
    MySVN mysvn ;
    MyTime myTime ;
    MyScript myScript ;
    MyLogTest log ;

    private static IosPack iospack;

    public static IosPack getInstance() {
        if (iospack == null) {
            synchronized (IosPack.class) {
                if (iospack == null) {
                    iospack = new IosPack();
                }
            }
        }
        return iospack;
    }

    public IosPack() {
    }
    public void init(){
        dealSvnInfo = DealSvnInfo.getInstance();
        dealConfig = DealConfig.getInstance();
        dealIosLog = DealIosLog.getInstance();
        myFile= MyFile.getInstance();
        mysvn = MySVN.getInstance();
        myTime = MyTime.getInstance();
        myScript = MyScript.getInstance();
        log = MyLogTest.getInstance();
    }

    @Override
    public String packios(String packname,String svnversion) {
        //IOS打单包方法，流程包括1.svn代码下载 2.编译环境准备，文件内容修改 3.ant编译 4.新建文件夹并移包 5.上传包到136
        init();
        IosLog iosLog=new IosLog();
        globalSet = DealGlobalset.getInstance().getGlobalset();
        svnInfo_ios = dealSvnInfo.getSvnInfoByName(packname);
        config_ios = dealConfig.getConfigByName(packname);
        project_ios = DealProject.getInstance().getProjectByname(svnInfo_ios.getProjectname());


        myFile.addDirs(config_ios.getStore_root_path()+"/"+config_ios.getVersions_path());

        log.level("debug","**********************step 0 :start ios pack**********************");

        //0.1 变量定义
        String flag_pack_ios = "200";
        String pack_ios_svn_version = null;
        String pack_ios_time = null;
        String pack_ios_time_database = null;
        String edit_mode_command = null;
        String pack_dir_name = null;
        String pack_dir_path = null;
        String command_move_store_to_apps = null;
        String upload_file_context = null;
        String pack_ios_excute_path = svnInfo_ios.getLocal_path() + "/" + config_ios.getExe_file_path();
        String pack_ios_excute_path2 = svnInfo_ios.getLocal_path() + "/cp_to_tomcat";
        String onoff_context = null;
        String ipa_in_versions = null;
        String build_txt_context = null;
        String build_source_code_context = null;
        String ipa_to_down = null;
        String move_icon_context = null;
        String upload_enterprise_context = null;
        String check_certificate_ios = "0";
        String move_forder_to_downipa = null;
        String flag_checkout_update = "0";

        if (project_ios.getOnoff().contains("0")) {
            onoff_context = "local";
        } else if (project_ios.getOnoff().contains("2")) {
            onoff_context = "yufa";
        } else {
            onoff_context = "online";
        }

        //0.2 清理临时存储中的文件
        log.level("info", "********** step 0.2 : Clean ipa in versions starting. **********");
        myFile.deleteFile(config_ios.getStore_root_path() + "/" + config_ios.getVersions_path() + "/online/" + project_ios.getProductname() + ".ipa");
        myFile.deleteFile(config_ios.getStore_root_path() + "/" + config_ios.getVersions_path() + "/local/" + project_ios.getProductname() + ".ipa");


        //0.3 获取svn版本号以及打包开始时间以及需要建立的文件夹名称,并存储到数据库中
        log.level("info", "********** step 0.3 : Get svn version and update database starting. **********");
        if (svnversion.equals("lastversion")) {
            pack_ios_svn_version = mysvn.getversion(svnInfo_ios.getSvn_url(), globalSet.getSvnusername(), globalSet.getSvnpassword());
        } else {
            pack_ios_svn_version = svnversion;
        }
        pack_ios_time = myTime.getTime();
        pack_ios_time_database = myTime.getTime_database();
        pack_dir_name = svnInfo_ios.getPackname() + "_" + pack_ios_time + "_" + pack_ios_svn_version + "_" + onoff_context;
        pack_dir_path = globalSet.getIos_packpath() + "/" + pack_dir_name;
        log.level("info", "********** pack_dir_path is : " + pack_dir_path);


        //1.1 svn源代码下载，如果本地已经存在源代码，则更新，如果不存在则checkout
        log.level("info", "********** step 1.1 : Update or checkout source code from svn url starting. **********");
        if (myFile.hasfile(svnInfo_ios.getLocal_path(), "0", "xcworkspace")) {
            log.level("info", "********** step 1.2 : Update source code from svn url starting. **********");
            flag_pack_ios = mysvn.doUpdate(svnInfo_ios.getSvn_url(), globalSet.getSvnusername(), globalSet.getSvnpassword(), svnInfo_ios.getLocal_path(), svnversion);
            flag_checkout_update = "1";//表示代码是update出来的

        } else {
            log.level("info", "********** step 1.3 : checkout source code from svn url starting. **********");
            flag_pack_ios = mysvn.doCheckOut(svnInfo_ios.getSvn_url(), globalSet.getSvnusername(), globalSet.getSvnpassword(), svnInfo_ios.getLocal_path(), svnversion);
            flag_checkout_update = "0";//表示代码是checkout出来，需要open -a Xcode XXX.xcworkspace操作
        }

        //1.2 对源代码下载是否成功的判断flag_pack_ios=-1
        if (!pack_ios_svn_version.contains("error") && !flag_pack_ios.contains("-1") && !flag_pack_ios.contains("error")) {



//                上下均注释点之后,可以短时间打包完成,第三次打包长时间
//                上下均放开,长时间打包
//                上面放开,下面注释,等待结果,长时间打包
//                下面放开,上面注释,等待结果,可行,第三次打包长时间
//                上下均放开,修改脚本执行前的脚本,去掉buid.txt相关内容,长时间打包
//                上下均放开,修改脚本执行前的脚本,清空,可行
//                上下均放开,修改脚本执行前的脚本,去掉修改online beta配置相关内容,可行


            //2.1 创建可执行文件
            myScript.create_exe_file(pack_ios_excute_path, "echo 11", "info");

            //2.2 修改文件build.txt,xxxx-info.list内容，打成需要的包
            log.level("info", "********** step 2.2 : Create build.txt starting. **********");
            build_txt_context = pack_ios_time_database + "\n" + pack_ios_svn_version + "_" + onoff_context + "_" + svnInfo_ios.getSvn_url();
            String temp_flag_localonline = "N";
            if (svnInfo_ios.getOn_off().contains("0")) {
                temp_flag_localonline = "L";
            } else if (svnInfo_ios.getOn_off().contains("1")) {
                temp_flag_localonline = "O";
            } else if (svnInfo_ios.getOn_off().contains("2")) {
                temp_flag_localonline = "B";
            }
            String command_before = "local_path=" + svnInfo_ios.getLocal_path() + "\nsvn_version=" + pack_ios_svn_version + "\nsvn_date_for_pi=" + "\" \n" + pack_ios_svn_version + "_" + temp_flag_localonline + myTime.getSimpleTime_database() + "\"" +
                    "\nsvn_date=\"" + build_txt_context + "\"\n" +
                    "packinfo_onoff=" + svnInfo_ios.getOn_off() + "\n";


            log.level("info", "not-do-command_before:" + command_before + project_ios.getExe_before_context() + "\n");
            myScript.run_command_file(command_before + project_ios.getExe_before_context() + "\n", pack_ios_excute_path, "info");
            log.level("info", "********** step 3.2 : Run command to build source code starting. **********");
            //*************** 3.2 开始编译源代码 **********

            String pack_bash = "";
            if (project_ios.getIosbuildtype().contains("Automatic")) {
                pack_bash = "xcodebuild -target " + "$project_name" + " -configuration Release clean -sdk $sdk_change\n" +
                        "xcodebuild -workspace " + "$project_name" + ".xcworkspace -scheme " + "$project_name" + " -configuration Release -sdk $sdk_change  ONLY_ACTIVE_ARCH=NO \n" +
                        "xcrun -sdk iphoneos PackageApplication -v $project_path$app_path " +
                        "-o $ipa_output_path/online/" + project_ios.getProductname() + ".ipa ";

            } else {
                pack_bash = "xcodebuild -target " + "$project_name" + " -configuration Release clean -sdk $sdk_change\n" +
                        "xcodebuild -workspace " + "$project_name" + ".xcworkspace -scheme " + "$project_name" + " -configuration Release -sdk $sdk_change  ONLY_ACTIVE_ARCH=NO  PROVISIONING_PROFILE=$provision\n" +
                        "xcrun -sdk iphoneos PackageApplication -v $project_path$app_path " +
                        "-o $ipa_output_path/online/" + project_ios.getProductname() + ".ipa " + "--embed \"$provision_path\"";
            }


            String pre_str = "project_path=" + svnInfo_ios.getLocal_path() +
                    "\nprovision=" + project_ios.getProversionprofile() + "\n" +
                    "app_path=" + project_ios.getIosapppath() + "\n" +
                    "cd $project_path\n" +
                    "project_name=$(ls | grep xcworkspace | awk -F.xcworkspace '{print $1}')\n" +
                    "ipa_output_path=" + config_ios.getStore_root_path() + "/" + config_ios.getVersions_path() + "\n" +
                    "sdk_change=$(xcodebuild -showsdks|grep iphoneos|awk '{print $4}')\n" +
                    "security unlock-keychain -p apple /Users/" + System.getProperties().getProperty("user.name") + "/Library/Keychains/login.keychain\n";


            //checkout 与 update两种方式的代码需要不同的编译脚本
            if (flag_checkout_update.contains("0")) {
                build_source_code_context = pre_str +
                        "open -a Xcode $project_path/$project_name.xcworkspace\n" +
                        "sleep 90\n" +
                        pack_bash + "\n" +
                        "killall Xcode";

            } else {

                build_source_code_context = pre_str + pack_bash;
            }

            log.level("info", "command_on:" + build_source_code_context + "\n");
            flag_pack_ios = myScript.run_command_file_env(build_source_code_context, pack_ios_excute_path, "info");
//                        flag_pack_ios ="400";
            //*************** 3.2 编译源代码结束 **********
            log.level("info", "The result of run building source code is : " + flag_pack_ios);

            ipa_in_versions = config_ios.getStore_root_path() + "/" + config_ios.getVersions_path() + "/online/" + project_ios.getProductname() + ".ipa";
            log.level("info", "Full path of ipa in versions is : " + ipa_in_versions);

            //编译脚本没有报出errors错误，ipa文件正常复制到version中，为编译正常的判断准则
            if (flag_pack_ios.contains("400") && new File(ipa_in_versions).exists()) {

                //4.1 新建文件夹并移动打好的包
                log.level("info", "********** step 4.1 : Create forder to store .ipa starting. **********");
                new File(pack_dir_path + "/online").mkdirs();
                new File(pack_dir_path + "/local").mkdirs();
                command_move_store_to_apps = "cp " + config_ios.getStore_root_path() + "/" + config_ios.getVersions_path() + "/online/" + project_ios.getProductname() + ".ipa " + pack_dir_path + "/online";
                log.level("info", "step 4.1 move command is : " + command_move_store_to_apps);
                myScript.run_command_file(command_move_store_to_apps, pack_ios_excute_path, "info");

                //4.2 把已经打好包得文件夹移动到Apache下面的downipa中，用来下载
                log.level("info", "********** step 4.2 : move ipa to apache tomcat downipa. **********");
                String downipa_path = globalSet.getTomcat_path() + "/webapps/downipa/" + globalSet.getDownipa_filename();
                myFile.addDirs(downipa_path);
                if (new File(downipa_path).exists()) {
                    String cp_to_tomcat = get_cp_to_tomcat_str(pack_dir_path, pack_dir_name, downipa_path);
                    myScript.create_exe_file(pack_ios_excute_path2, "#!/bin/sh\n" + cp_to_tomcat, "info");
                    move_forder_to_downipa = "sh " + pack_ios_excute_path2 + " " + project_ios.getProductname() + " " + project_ios.getProductname() + " " + project_ios.getIosbundleid() + " " +
                            config_ios.getStore_root_path() + "/" + config_ios.getVersions_path() + "/online/" + project_ios.getProductname() + ".ipa";

                    log.level("info", "step 4.2:move_forder_to_tomcat is : " + move_forder_to_downipa);
                    myScript.run_command_file(move_forder_to_downipa, pack_ios_excute_path, "info");
                }


                //4.3 压缩$project_path/build/XXX/Build/Products/Release-iphoneos/XXX.app.dSYM 以及 XXX.app
                log.level("info", "********** step 4.3 : compress xxx.app.dsym . **********");
                String dsym_path = svnInfo_ios.getLocal_path() + project_ios.getIosapppath() + ".dSYM";
                String app_path = svnInfo_ios.getLocal_path() + project_ios.getIosapppath();
                log.level("info", "Dsym path is : " + dsym_path);
                if (dsym_path != null) {
                    if (new File(dsym_path).exists()) {

                        try {
                            ZipUtil.getInstance().zip("", "", dsym_path);
                            ZipUtil.getInstance().zip("", "", app_path);

                        } catch (Exception e) {
                            log.level("error", e.getMessage());
                        }
                    }
                }
                String dsym_store = globalSet.getIos_packpath() + "/store_dsym";
                myFile.addDirs(dsym_store);
                String command_after = "dsym_store=" + dsym_store + "\n" +
                        "cp -r " + dsym_path + ".zip $dsym_store/" + pack_ios_time + "." + pack_ios_svn_version + "." + project_ios.getProductname() + ".app.dSYM.zip\n" +
                        "cp -r " + app_path + ".zip $dsym_store/" + pack_ios_time + "." + pack_ios_svn_version + "." + project_ios.getProductname() + ".app.zip\n";
                log.level("info", "command_after_auto_set_dsym_move :" + command_after);
                myScript.run_command_file(command_after, pack_ios_excute_path, "debug");
                command_after = command_before +
                        "ipaname=" + pack_dir_name + "\nipa_path=" + downipa_path + "/" + pack_dir_name + "/online/" + project_ios.getProductname() + ".ipa\n";
                if (project_ios.getExe_after_context() != null && !project_ios.getExe_after_context().equals("null") && !project_ios.getExe_after_context().equals("")) {
                    log.level("info", "command_after_user_defined :" + command_after + project_ios.getExe_after_context());
                    myScript.run_command_file(command_after + project_ios.getExe_after_context(), pack_ios_excute_path, "debug");
                }

                //在iosLog表中插入打包日志数据

                config_ios.setSvn_version(pack_ios_svn_version);
                config_ios.setForder_name(pack_dir_name);
                config_ios.setPackage_time(pack_ios_time_database);
                dealConfig.updateConfig(config_ios);

                iosLog = new IosLog();
                iosLog.setPackname(packname);
                iosLog.setMain_version(svnInfo_ios.getMain_version());
                iosLog.setSvn_version(pack_ios_svn_version);
                iosLog.setFile_name(config_ios.getForder_name());
                iosLog.setStore_root_path(config_ios.getStore_root_path());
                iosLog.setStore_path(config_ios.getStore_path());
                iosLog.setSvn_url(svnInfo_ios.getSvn_url());
                iosLog.setProduct_name(project_ios.getProductname());
                iosLog.setEdition("");
                iosLog.setPlatform(svnInfo_ios.getPlatform());
                iosLog.setMode("");
                iosLog.setOn_off(svnInfo_ios.getOn_off());
                iosLog.setNums(1);
                iosLog.setIp("ip");
                iosLog.setPack_time(myTime.getTime_database());
                iosLog.setIsstore("0");
                iosLog.setViewflag("1");
                iosLog.setExone("");
                iosLog.setExtwo("");
                iosLog.setExthree("");
                dealIosLog.addIosLog(iosLog);


                //打包成功后，更新打包的相关信息

                flag_pack_ios = "200";

            } else {
                flag_pack_ios = "203";
            }//203表示打包过程中编译失败
            flag_pack_ios = "200";

        } else {
            flag_pack_ios = "202";
        }//svn 地址连接出现问题

        log.level("info", "***********The result of pack ios is : " + flag_pack_ios + "****************");
        return flag_pack_ios;

    }

    /**
     *
     * @param pack_dir_path ipa包所在文件夹的路径
     * @param pack_dir_name ipa包所在文件夹的名称
     * @param downipa_path ipa包在tomcat下的存放路径
     * @return
     */
    public static String get_cp_to_tomcat_str(String pack_dir_path,String pack_dir_name,String downipa_path){
        String str ="";
        str = "if test -z \"$4\" ; then\n" +
                "\techo \"Usage: $0 <app url> <app name> <app id> <ipa file>\"\n" +
                "\techo \"Example: $0 xxx XXX com.xxx.xxx ./xxx.ipa\"\n" +
                "\texit 0\n" +
                "fi\n"+
                "app=$1\n" +
                "appname=$2\n" +
                "appid=$3\n" +
                "ipafile=$4\n" +
                "webhost="+DealGlobalset.getInstance().getGlobalset().getHostip()+"\n"+
                "# read Info.plist path\n" +
                "plistpath=`unzip -l $ipafile | grep \".app/Info.plist\" | awk '{print $4}'`\n" +
                "# extract Info.plist out\n" +
                "unzip -o -q $ipafile $plistpath -d /tmp/\n" +
                "# read app version\n" +
                "plutil -extract CFBundleShortVersionString xml1 /tmp/$plistpath\n" +
                "appversion=`grep \"<string>\" /tmp/$plistpath | awk -F \">\" '{print $2}' | awk -F \"<\" '{print $1}'`\n" +
                "# read build.txt path\n" +
                "buildpath=`unzip -l $ipafile | grep \".app/build.txt\" | awk '{print $4}'`\n" +
                "# extract build.txt out\n" +
                "unzip -o -q $ipafile $buildpath -d /tmp/\n" +
                "# read svn version\n" +
                "svnversion=`tail -n 1 /tmp/$buildpath | awk -F '_' '{print $1}'`\n"+
                "echo 'appversion= '$appversion'   buildpath = '$buildpath'    svnversion = '$svnversion \n" +
                "# build plist file\n" +
                "echo '<?xml version=\"1.0\" encoding=\"UTF-8\"?>' > /tmp/${app}.plist\n" +
                "echo '<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">' >> /tmp/${app}.plist\n" +
                "echo '<plist version=\"1.0\">' >> /tmp/${app}.plist\n" +
                "echo '<dict>' >> /tmp/${app}.plist\n" +
                "echo '\t<key>items</key>' >> /tmp/${app}.plist\n" +
                "echo '\t<array>' >> /tmp/${app}.plist\n" +
                "echo '\t\t<dict>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t<key>assets</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t<array>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<dict>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t\t<key>kind</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t\t<string>software-package</string>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t\t<key>url</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t\t<string>http://'$webhost':8080/downipa/"+DealGlobalset.getInstance().getGlobalset().getDownipa_filename()+"/"+pack_dir_name+"/online/'$app'.ipa</string>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t</dict>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t</array>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t<key>metadata</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t<dict>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<key>bundle-identifier</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<string>'$appid'</string>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<key>kind</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<string>software</string>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<key>title</key>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t\t<string>'$appname'</string>' >> /tmp/${app}.plist\n" +
                "echo '\t\t\t</dict>' >> /tmp/${app}.plist\n" +
                "echo '\t\t</dict>' >> /tmp/${app}.plist\n" +
                "echo '\t</array>' >> /tmp/${app}.plist\n" +
                "echo '</dict>' >> /tmp/${app}.plist\n" +
                "echo '</plist>' >> /tmp/${app}.plist\n";
        if(pack_dir_path.equals("")){
            str = str +"cp /tmp/${app}.plist "+downipa_path+"/"+pack_dir_name+"/online/manifest.plist\n";
        }else{
            str = str + "cp -r " + pack_dir_path + " " +downipa_path +"/\n"+
                    "cp /tmp/${app}.plist "+downipa_path+"/"+pack_dir_name+"/online/manifest.plist\n";
        }
        return str;
    }
}