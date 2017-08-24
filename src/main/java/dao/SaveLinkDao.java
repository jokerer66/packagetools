package dao;

import bean.SaveLink;

import java.util.List;

/**
 * Created by apple on 2016/12/20.
 */
public interface SaveLinkDao {

    //方法1.新增savelink数据
    public int addlinks(SaveLink saveLink);

    //方法2.根据link查询表信息
    public SaveLink selectByLink(String link);

    //方法3.查询数据库中该link已经存在的数量
    public int selectNumsBylink(String link);

    //方法4.删除savelink数据
    public void deleteSaveLink(String link);

//    //方法5.修改savelink数据
//    public int updateSaveLink(SaveLink saveLink);

    //方法6.获取所有的savelink数据
    public List<SaveLink> selectAllSaveLink();

}
