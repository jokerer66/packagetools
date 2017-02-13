package process.pack;

import bean.Config;
import bean.GlobalSet;
import bean.Project;
import bean.SvnInfo;
import helper.date.MyTime;
import helper.log.MyLogTest;
import interfaces.pack.PackInfoInterface;
import interfaces.pack.PackInterface;
import service.DealConfig;
import service.DealGlobalset;
import service.DealProject;
import service.DealSvnInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2016/12/21.
 */
public class PackInfo implements PackInfoInterface {

    MyLogTest log = null;
    MyTime myTime = null;
    private static PackInfo packInfo;
    public static PackInfo getInstance() {
        if (packInfo == null) {
            synchronized (PackInfo.class) {
                if (packInfo == null) {
                    packInfo = new PackInfo();
                }
            }
        }
        return packInfo;
    }
    public PackInfo() {
        myTime = MyTime.getInstance();
        log = MyLogTest.getInstance();
    }

    @Override
    public String savepackinfo(Map page_map) {
        MyLogTest log = MyLogTest.getInstance();
        log.level("debug","save packinfo to database with packname:"+page_map.get("ctr_packname"));
        if (((String)page_map.get("ctr_pid")).contains("do not need to write")){
            //新增
            return savepackinfo(page_map,"1");
        }else{
            //编辑
            return savepackinfo(page_map,"2");

        }

    }

    /**
     * 保存
     * @param page_map
     * @param savetpye 1-新增,2-编辑
     * @return
     */
    public String savepackinfo(Map page_map,String savetpye){

        String flag="";
        Project project = DealProject.getInstance().getProjectByname((String)page_map.get("ctr_projectname"));
        SvnInfo svnInfo1 = new SvnInfo();
        Config config1 = new Config();
        GlobalSet globalSet = DealGlobalset.getInstance().getGlobalset();

        String localpath = globalSet.getCodepath() + "/" + page_map.get("ctr_packname").toString() + "_" + myTime.getYueri() + myTime.getHour() + myTime.getMinite();
        String store_root_path = null;
        String store_path_deal = null;
        String versions_path_deal = "";
        String packpath = "";
        if(project.getPlatform().equals("android")){
            packpath = globalSet.getAndroid_packpath();
        }else if(project.getPlatform().equals("ios")){
            packpath = globalSet.getIos_packpath();
            versions_path_deal = "versions";
        }

        store_path_deal = packpath.substring(packpath.lastIndexOf("/")+1);
        store_root_path = packpath.substring(0,packpath.lastIndexOf("/"));




        //增加svninfo表数据

        int xx = 0;
        if(DealSvnInfo.getInstance().getSvnInfoNums() == 0){
            xx = 1000;
        }else{
            xx = DealSvnInfo.getInstance().getMaxPid() + 1;
        }

        if(savetpye.equals("1")){
            svnInfo1.setPid(xx);
            config1.setPid(xx);
            svnInfo1.setSort(xx + "");
        }else{
            svnInfo1.setPid(Integer.valueOf((String)page_map.get("ctr_pid")));
            config1.setPid(Integer.valueOf((String)page_map.get("ctr_pid")));
            svnInfo1.setSort(xx-1 + "");
        }

        svnInfo1.setPackname(page_map.get("ctr_packname").toString());
        svnInfo1.setMain_version(page_map.get("ctr_main_version").toString());
        svnInfo1.setSvn_url(page_map.get("ctr_svnurl").toString());
        svnInfo1.setLocal_path(localpath);
        svnInfo1.setProductname(project.getProductname());
        svnInfo1.setOn_off(project.getOnoff());
        svnInfo1.setPlatform(project.getPlatform());
        svnInfo1.setBusy_status("free");
        svnInfo1.setIn_time(myTime.getTime_database());
        svnInfo1.setExone("");
        svnInfo1.setExtwo("");
        svnInfo1.setExthree("");
        svnInfo1.setProjectname(project.getProjectname());
        //增加config表数据

        config1.setPackname(page_map.get("ctr_packname").toString());
        config1.setSvn_version("");//置空
        config1.setForder_name("");//置空
        config1.setExe_file_path("pack_excute");
        config1.setStore_root_path(store_root_path);
        config1.setStore_path(store_path_deal);
        config1.setVersions_path(versions_path_deal);
        config1.setEnterprise_path("");
        config1.setEnterprise_name("");
        config1.setPackage_time("");
        config1.setExone("");
        config1.setExtwo("");

        if(savetpye.equals("1")){
            if(DealSvnInfo.getInstance().addSvnInfo(svnInfo1) && DealConfig.getInstance().addConfig(config1)){
                flag = "1";
            }else{
                flag ="0";
            }
        }else{
            if(DealSvnInfo.getInstance().updateSvnInfo(svnInfo1) && DealConfig.getInstance().updateConfig(config1)){
                flag = "3";
            }else{
                flag ="2";

            }
        }

        return flag;

    }

    public String getSystemUsername(){
        return System.getProperties().getProperty("user.name");
    }

}
