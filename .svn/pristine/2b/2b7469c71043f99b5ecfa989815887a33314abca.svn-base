package service;

import bean.AndroidLog;
import dao.IAndroidLogOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DealAndroidLog {

    ApplicationContext ctx = null;
    IAndroidLogOperation iAndroidLogOperation = null;

    private static DealAndroidLog dealAndroidLog;

    public static DealAndroidLog getInstance() {
        if (dealAndroidLog == null) {
            synchronized (DealAndroidLog.class) {
                if (dealAndroidLog == null) {
                    dealAndroidLog = new DealAndroidLog();
                }
            }
        }
        return dealAndroidLog;
    }
    //方法0：构造函数，初始化配置文件以及接口
    public DealAndroidLog(){

        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        iAndroidLogOperation = (IAndroidLogOperation) ctx.getBean("androidLogDao");
    }

    //方法1：获取AndroidLog表所有数据
    public List<AndroidLog> getAndroidLogs(){

        List<AndroidLog> androidLogs = iAndroidLogOperation.selectAndroidLogs();

        return androidLogs;
    }

    public int getAndroidLogsNum(){

        int num = iAndroidLogOperation.selectAndroidLogsNum();

        return num;
    }
    //方法2：通过产品名称获取文件名
    public List<String> getNamesByProductName(String product_name){

        List<String> filenames = iAndroidLogOperation.selectAndroidLogByProductName(product_name);

        return filenames;
    }

    //模糊查询获取androidlog中相似file_name的数据总数
    public List<AndroidLog> getAndroidLogFilenames(int start_flag, int end_flag, String file_name_section){

        List<AndroidLog> androidLogs = iAndroidLogOperation.selectAndroidLogFilenames(start_flag,end_flag,"%"+file_name_section+"%");

        return androidLogs;
    }

    //模糊查询获取androidlog中相似file_name的数据总集合
    public int getAndroidLogFilenameNums(String file_name_section){

        int filenames_num = iAndroidLogOperation.selectAndroidLogFilenameNums("%"+file_name_section+"%");

        return filenames_num;
    }


    //方法3：通过产品名称、主干/分支、debug/enterprise来获取filename
    public List<String> getNamesByPEM(String product_name,String edition,String mode){

        List<String> filenames = iAndroidLogOperation.selectAndroidLogByPEM(product_name,edition,mode);

        return filenames;
    }

    //方法4：通过id获取单行数据
    public AndroidLog getAndroidLogByID(int id) {

        AndroidLog androidLog = iAndroidLogOperation.selectAndroidLogByID(id);

        return androidLog;
    }

    //方法5：通过debug/enterprise类型返回所有同类型数据
    public List<AndroidLog> getAndroidLogsByMode(String mode){

        List<AndroidLog> androidLogs = iAndroidLogOperation.selectAndroidLogsByMode(mode);

        return androidLogs;
    }

    //方法6：通过packname
    public List<AndroidLog> getAndroidLogList(String packname) {

        List<AndroidLog> androidLogs = iAndroidLogOperation.selectAndroidLogByName(packname);

        return androidLogs;
    }


    //方法7：分段查询packname
    public List<AndroidLog> getAndroidLogSection(int start_flag, int end_flag){

        List<AndroidLog> androidLogList ;

        androidLogList = iAndroidLogOperation.selectAndroidLogSection(start_flag, end_flag);

        return androidLogList;
    }

    //方法8：分段查询filename
    public List<String> getFilenamesSection(int start_flag,int end_flag){

        List<String> androidLogs ;

        androidLogs = iAndroidLogOperation.selectFilenamesSection(start_flag, end_flag);

        return androidLogs;
    }

    //方法9：添加androidlog信息
    public String addAndroidLog(AndroidLog androidLog){

        String flag_add_androidLog = "0";

        iAndroidLogOperation.addAndroidLog(androidLog);

        return flag_add_androidLog;
    }

    //方法10：更新androidlog表信息
    public String updateAndroidLog(AndroidLog androidLog) {

        String flag_update_androidLog = "0";

        iAndroidLogOperation.updateAndroidLog(androidLog);

        return flag_update_androidLog;
    }

    //方法11：删除单行信息
    public void deleteAndroidLog(String packname) {

        iAndroidLogOperation.deleteAndroidLog(packname);
    }
    //方法12：获取ready包
    public List<AndroidLog> getandroidreadyinfo() {

        return iAndroidLogOperation.getandroidreadyinfo();
    }

    public AndroidLog getLastPackinfo(String svn_url){
        return iAndroidLogOperation.getLastPackinfo(svn_url);
    }

}
