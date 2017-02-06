package dao;

import bean.IosLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IIosLogOperation {

    public List<IosLog> selectIosLogs();

    public int selectIosLogNums();

    public List<String> selectIosLogByProductName(String product_name);

    public List<IosLog> selectIosLogFilenames(@Param("start_flag") int start_flag, @Param("end_flag") int end_flag, @Param("file_name_section") String file_name_section);

    public int selectIosLogFilenameNums(String file_name_section);

    public List<String> selectIosLogByPEM(@Param("product_name") String product_name, @Param("edition") String edition, @Param("mode") String mode);

    public IosLog selectIosLogByID(int id);

    public List<IosLog> selectIosLogsByMode(String mode);

    public List<IosLog> selectIosLogByName(String packname);

    public List<IosLog> selectIosLogSection(@Param("start_flag") int start_flag, @Param("end_flag") int end_flag);

    public List<IosLog> selectFilenamesSection(@Param("start_flag") int start_flag, @Param("end_flag") int end_flag);

    public void addIosLog(IosLog iosLog);

    public void updateIosLog(IosLog iosLog);

    public void deleteIosLog(String packname);

    public List<IosLog> getiosreadyinfo();
}
