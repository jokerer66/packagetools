package service;

import bean.SaveLink;
import bean.SvnInfo;
import dao.SaveLinkDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by catty on 2017/5/27.
 * for savelink database table operation
 */
public class DealSaveLink {

    private static ApplicationContext ctx = null;
    private static SaveLinkDao saveLinkDao = null;

    private static DealSaveLink dealSaveLink;

    public static DealSaveLink getInstance() {
        if (dealSaveLink == null) {
            synchronized (DealIosLog.class) {
                if (dealSaveLink == null) {
                    dealSaveLink = new DealSaveLink();
                }
            }
        }
        return dealSaveLink;
    }

    public DealSaveLink() {
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        saveLinkDao = (SaveLinkDao) ctx.getBean("saveLinkDao");
    }

    //方法1：添加数据到savelink表中  返回boolean？添加成功:添加失败
    public Boolean addlinks(SaveLink saveLink) {
        saveLinkDao.addlinks(saveLink);
        return saveLink.getId() >= 0 ? true : false;
    }

    //方法2:根据link查询表信息
    public SaveLink selectByLink(String link) {
        SaveLink saveLink = saveLinkDao.selectByLink(link);
        return saveLink;
    }

    //方法3:查询该链接在数据库的存在的条数
    public int selectByLinkNums(String link) {
        int link_nums = saveLinkDao.selectNumsBylink(link);
        return link_nums;
    }

    //方法4：根据link删除记录
    public String deleteSaveLink(String link) {
        String flag_delete_link = "0";
        saveLinkDao.deleteSaveLink(link);
        return flag_delete_link;
    }

//    //方法5：更新逻辑：页面上除了link外其他选项都可以更新。从界面接收所有修改或未修改的内容，同时进行更新。
//    public Boolean updateSaveLink(SaveLink link) {
//        return  saveLinkDao.updateSaveLink(link) >=0 ? true : false;
//    }

    //方法6：获取所有的savelink数据
    public List<SaveLink> getSaveLinks() {
        List<SaveLink> saveLinks = saveLinkDao.selectAllSaveLink();
        return saveLinks;
    }

}
