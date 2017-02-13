package process.pack;

import bean.GlobalSet;
import bean.Project;
import bean.SvnInfo;
import helper.svn.MySVN;
import interfaces.pack.AndroidPackInterface;
import interfaces.pack.IosPackInterface;
import interfaces.pack.PackInterface;
import service.DealGlobalset;
import service.DealProject;
import service.DealSvnInfo;

/**
 * Created by apple on 2016/12/16.
 */
public class Pack implements PackInterface {

    Project project;
    DealSvnInfo dealSvnInfo;
    GlobalSet globalSet;

    private String packname;
    private String svnversion;
    MySVN mySVN;
    IosPackInterface iospack = new IosPack();
    AndroidPackInterface androidpack = new AndroidPack();

    public Pack(String packname,String svnversion) {
        this.packname = packname;
        this.svnversion = svnversion;
    }
    public Pack() {
    }
    @Override
    public String dopack(String packname2) {

        globalSet = DealGlobalset.getInstance().getGlobalset();
        dealSvnInfo = DealSvnInfo.getInstance();
        mySVN = MySVN.getInstance();

        SvnInfo svnInfo = dealSvnInfo.getSvnInfoByName(packname);
        project = DealProject.getInstance().getProjectByname(svnInfo.getProjectname());
        System.out.println("********************************prepare pack********************************\n svninfoname = " + project.getProductname());

        //需要用到的变量
        String flag_choose = "000";
        int nums_in_svnInfo_database = 0;
        String svn_versions_to_judge = null;
        String temp_productname = null;

        //svn、数据库校验
        nums_in_svnInfo_database = dealSvnInfo.getSvnInfoNums();
        svn_versions_to_judge = mySVN.getversion(svnInfo.getSvn_url(), globalSet.getSvnusername(),globalSet.getSvnpassword());

        System.out.println("The data nums in svninfo is : " + nums_in_svnInfo_database + "\nThe svn version is : " + svn_versions_to_judge);

        //如果svn连不上或者数据库找不到记录则返回错误代码101
        if (nums_in_svnInfo_database > 0 && !svn_versions_to_judge.contains("error")) {

            //查询数据库忙碌状态，如果忙则返回忙碌标志，如果不忙则修改忙碌标志位忙碌
            if (!svnInfo.getBusy_status().contains("busy")) {
                //&& ifallnotbusy(svnInfo)
                temp_productname =project.getProductname().toLowerCase().toString();

                //选择打包方法进行打包
                if (svnInfo.getPlatform().contains("android")) {
                    //flag_choose = "100";//修改android为忙碌状态
                    System.out.println("*************** set android flag to busy with project = " + temp_productname+"*******************");
                    dealSvnInfo.updateBusyStatusByPlatform("busy", "android", temp_productname);
                    flag_choose = androidpack.packandroid(packname,svnversion);

                    //更新android为空闲状态
                    dealSvnInfo.updateBusyStatusByPlatform("free", "android", temp_productname);
                    System.out.println("*************** set android flag to free with project = " + temp_productname+"*******************");

                } else {
                    //flag_choose = "200";//修改ios为忙碌状态
                    System.out.println("*************** set ios flag to busy with project = " + temp_productname+"*******************");
                    dealSvnInfo.updateBusyStatusByPlatform("busy", "ios", temp_productname);
                    flag_choose = iospack.packios(packname,svnversion);
                    //更新ios为空闲状态
                    dealSvnInfo.updateBusyStatusByPlatform("free", "ios", temp_productname);
                    System.out.println("*************** set ios flag to free with project = " + temp_productname+"*******************");
                }
            } else {
                flag_choose = "002";
            } //忙碌

        } else {
            flag_choose = "001";
        }//数据库或者svn连不上

        return  flag_choose;
    }


}
