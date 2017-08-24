package service;

import bean.IosLog;
import dao.IIosLogOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

//ioslog表处理类
public class DealIosLog {

    ApplicationContext ctx = null;
    IIosLogOperation iosLogOperation = null;


    private static DealIosLog dealIosLog;

    public static DealIosLog getInstance() {
        if (dealIosLog == null) {
            synchronized (DealIosLog.class) {
                if (dealIosLog == null) {
                    dealIosLog = new DealIosLog();
                }
            }
        }
        return dealIosLog;
    }

    //方法0：构造函数，初始化配置文件以及接口
    public DealIosLog() {

        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        iosLogOperation = (IIosLogOperation) ctx.getBean("iosLogDao");
    }

    //方法1：获取iosLog表所有数据
    public List<IosLog> getIosLogs() {

        List<IosLog> iosLogs = iosLogOperation.selectIosLogs();

        return iosLogs;
    }

    public int getIosLogNums() {

        int nums = iosLogOperation.selectIosLogNums();

        return nums;
    }

    //方法2：通过产品名称获取文件名
    public List<String> getNamesByProductName(String product_name) {

        List<String> filenames = iosLogOperation.selectIosLogByProductName(product_name);

        return filenames;
    }


    //模糊查询获取androidlog中相似file_name的数据总数
    public List<IosLog> getIosLogFilenamesSection(int start_flag, int end_flag, String file_name_section) {

        List<IosLog> iosLogs = iosLogOperation.selectIosLogFilenames(start_flag, end_flag, "%" + file_name_section + "%");

        return iosLogs;
    }

    //模糊查询获取androidlog中相似file_name的数据总集合
    public int getIosLogFilenameNums(String file_name_section) {

        int filenames_num = iosLogOperation.selectIosLogFilenameNums("%" + file_name_section + "%");

        return filenames_num;
    }

    //方法3：通过产品名称、主干/分支、debug/enterprise来获取filename
    public List<String> getNamesByPEM(String product_name, String edition, String mode) {

        List<String> filenames = iosLogOperation.selectIosLogByPEM(product_name, edition, mode);

        return filenames;
    }

    //方法4：通过id获取单行数据
    public IosLog getIosLogByID(int id) {

        IosLog iosLog = iosLogOperation.selectIosLogByID(id);

        return iosLog;
    }

    //方法5：通过debug/enterprise类型返回所有同类型数据
    public List<IosLog> getIosLogsByMode(String mode) {

        List<IosLog> iosLogs = iosLogOperation.selectIosLogsByMode(mode);

        return iosLogs;
    }

    //方法6：通过packname
    public List<IosLog> getIosLogList(String packname) {

        List<IosLog> iosLogs = iosLogOperation.selectIosLogByName(packname);

        return iosLogs;
    }

    //方法7：分段查询packname
    public List<IosLog> getIosLogSection(int start_flag, int end_flag) {

        List<IosLog> ioslogs;

        ioslogs = iosLogOperation.selectIosLogSection(start_flag, end_flag);

        return ioslogs;
    }

    //方法8：分段查询filename
    public List<IosLog> getFilenamesSection(int start_flag, int end_flag) {

        List<IosLog> iosLogs;

        iosLogs = iosLogOperation.selectFilenamesSection(start_flag, end_flag);

        return iosLogs;
    }

    //方法8：添加ioslog信息
    public String addIosLog(IosLog iosLog) {

        String flag_add_iosLog = "0";

        iosLogOperation.addIosLog(iosLog);

        return flag_add_iosLog;
    }

    //方法9：更新iosLog表信息
    public String updateiosLog(IosLog iosLog) {

        String flag_update_iosLog = "0";

        iosLogOperation.updateIosLog(iosLog);

        return flag_update_iosLog;
    }

    //方法10：删除单行信息
    public void deleteIosLog(String packname) {

        iosLogOperation.deleteIosLog(packname);
    }

    //方法10：获取ready包信息
    public List<IosLog> getiosreadyinfo() {
        return iosLogOperation.getiosreadyinfo();
    }

    public IosLog getLastPackinfo(String svn_url) {
        return iosLogOperation.getLastPackinfo(svn_url);
    }

}