package service;

import bean.SvnInfo;
import dao.ISvnInfoOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

//处理svninfo表的专属类
public class DealSvnInfo {

    private static ApplicationContext ctx = null;
    private static ISvnInfoOperation iSvnInfoOperation = null;

    private static DealSvnInfo dealsvninfo;

    public static DealSvnInfo getInstance() {
        if (dealsvninfo == null) {
            synchronized (DealSvnInfo.class) {
                if (dealsvninfo == null) {
                    dealsvninfo = new DealSvnInfo();
                }
            }
        }
        return dealsvninfo;
    }

    //方法0：构造函数，文件加载，类加载初始化
    public DealSvnInfo(){
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        iSvnInfoOperation = (ISvnInfoOperation) ctx.getBean("svnInfoDao");
    }

    //方法1：获取svninfo表中的所有数据
    public List<SvnInfo> getSvnInfos(){

//        ApplicationContext  ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        ISvnInfoOperation iSvnInfoOperation = (ISvnInfoOperation) ctx.getBean("svnInfoDao");
        SvnInfo svnInfo = new SvnInfo();

        List<SvnInfo> svnInfos = iSvnInfoOperation.selectSvnInfos();

        return svnInfos;
    }

    //方法2：获取svninfo表中的数据行数
    public int getSvnInfoNums(){

        int svninfo_nums = 0;
        svninfo_nums = iSvnInfoOperation.selectSvnInfoNums();

        return svninfo_nums;
    }

    //方法3：获取打包名称用于界面显示
    public List<String> getPackNamesByPlatform(String platform){

        List<String> packnames = new ArrayList<String>();

        List<SvnInfo> svnInfos = iSvnInfoOperation.selectSvnInfoNamesByPlatform(platform);

        for (SvnInfo svnInfo : svnInfos) {

            packnames.add(svnInfo.getPackname());
        }

        return packnames;

    }

    //方法4：获取相关平台的所有数据
    public List<SvnInfo> getSvnInfosByPlatform(String platform){


        List<SvnInfo> svnInfos = iSvnInfoOperation.selectSvnInfosByPlatform(platform);

        return svnInfos;
    }

    //方法5：返回所有数据的packname值
    public List<String> getAllSvnInfoNames() {

        List<String> packnames  = iSvnInfoOperation.getAllSvnInfoNames();

        return packnames;

    }

    //方法6：通过id获取改条记录在数据库中得所有信息
    public SvnInfo getSvnInfoById(int pid) {

        SvnInfo svnInfo = iSvnInfoOperation.selectSvnInfoById(pid);

        return svnInfo;
    }

    //方法7：通过用户名获取该条记录在数据库中得所有信息
    public SvnInfo getSvnInfoByName(String packname) {

        SvnInfo svnInfo = iSvnInfoOperation.selectSvnInfoByName(packname);

        return svnInfo;
    }

    //方法8：获取数据库中最大的pid值
    public int getMaxPid() {

        int result_pid = iSvnInfoOperation.selectMaxPid();

        return result_pid;
    }

    //方法9：获取改用户名在数据库的记录条数
    public int selectSvnInfoNums(String packname) {

        int packname_nums = iSvnInfoOperation.selectSvnInfoNumsByName(packname);

        return packname_nums;
    }

    //方法10：添加新的数据
    public boolean addSvnInfo(SvnInfo svnInfo) {

        iSvnInfoOperation.addSvnInfo(svnInfo);
        return svnInfo.getId()>0?true:false;

    }

    //方法11：更新逻辑：页面上除了packname外其他选项都可以更新。从界面接收所有修改或未修改的内容，同时进行更新。
    public Boolean updateSvnInfo(SvnInfo svnInfo) {

        return iSvnInfoOperation.updateSvnInfo(svnInfo)>=0?true:false;
    }

    //方法12：通过平台类型更新繁忙标志
    public String updateBusyStatusByPlatform(String busy_status,String platform,String productname){

        String flag_update_svninfo = "0";

        List<SvnInfo> svnInfos = iSvnInfoOperation.selectSvnInfosByPlatformAndProductname(platform,productname);

        for (SvnInfo svnInfo : svnInfos) {

            svnInfo.setBusy_status(busy_status);

            iSvnInfoOperation.updateSvnInfo(svnInfo);

        }

        return flag_update_svninfo;
        }

    //方法13：根据packname删除记录
    public String deleteSvnInfo(String packname) {

        String flag_delete_svninfo = "0";

        iSvnInfoOperation.deleteSvnInfo(packname);

        return flag_delete_svninfo;

    }

    //方法13：
    public Boolean flagnotbusy(String platform ,String productname) {


        List<SvnInfo> listnotbusy= iSvnInfoOperation.selectnotbusy(platform,productname);

        return listnotbusy.size()==0?true:false;

    }

    public List<SvnInfo> getAllSvninfoByProjectname (String projectname){
        return iSvnInfoOperation.getAllSvninfoByProjectname(projectname);

    }

}
