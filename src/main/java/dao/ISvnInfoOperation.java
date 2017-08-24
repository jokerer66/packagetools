package dao;

import bean.SvnInfo;

import java.util.List;

public interface ISvnInfoOperation {

    public List<SvnInfo> selectSvnInfos();

    public int selectSvnInfoNums();

    public int selectMaxPid();

    public List<SvnInfo> selectSvnInfoNamesByPlatform(String platform);

    public List<SvnInfo> selectSvnInfosByPlatform(String platform);

    public List<SvnInfo> selectSvnInfosByPlatformAndProductname(String platform, String productname);

    public List<String> getAllSvnInfoNames();

    public SvnInfo selectSvnInfoById(int pid);


    public SvnInfo selectSvnInfoByName(String packname);

    public int selectSvnInfoNumsByName(String packname);


    public int addSvnInfo(SvnInfo svnInfo);

    public int updateSvnInfo(SvnInfo svnInfo);

    public void updateSvnInfoBusyStatusByPlatform(SvnInfo svnInfo);

    public void deleteSvnInfo(String packname);

    public List<SvnInfo> selectnotbusy(String platform, String productname);

    public List<SvnInfo> getAllSvninfoByProjectname (String projectname);

    public List<SvnInfo> getLastSvn(String platform);
}