package dao;

import bean.AndroidLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAndroidLogOperation {

    public List<AndroidLog> selectAndroidLogs();

    public int selectAndroidLogsNum();

    public List<AndroidLog> selectAndroidLogFilenames(@Param("start_flag") int start_flag, @Param("end_flag") int end_flag, @Param("file_name_section") String file_name_section);

    public int selectAndroidLogFilenameNums(String file_name_section);

    public List<String> selectAndroidLogByProductName(String product_name);

    public List<String> selectAndroidLogByPEM(@Param("product_name") String product_name, @Param("edition") String edition, @Param("mode") String mode);

    public AndroidLog selectAndroidLogByID(int id);

    public List<AndroidLog> selectAndroidLogsByMode(String mode);

    public List<AndroidLog> selectAndroidLogByName(String packname);

    public List<AndroidLog> selectAndroidLogSection(@Param("start_flag") int start_flag, @Param("end_flag") int end_flag);

    public List<String> selectFilenamesSection(@Param("start_flag") int start_flag, @Param("end_flag") int end_flag);

    public void addAndroidLog(AndroidLog androidLog);

    public void updateAndroidLog(AndroidLog androidLog);

    public void deleteAndroidLog(String packname);

    public List<AndroidLog> getandroidreadyinfo();

    public AndroidLog getLastPackinfo(String svn_url);
}
