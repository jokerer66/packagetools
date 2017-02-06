package service;

import bean.GlobalSet;
import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.GlobalsetDao;
import helper.log.MyLogTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by apple on 2016/12/29.
 */
public class DealGlobalset {
    ApplicationContext ctx =null;
    MyLogTest log = null;
    GlobalsetDao globalsetDao;

    private static DealGlobalset dealGlobalset;

    public static DealGlobalset getInstance() {
        if (dealGlobalset == null) {
            synchronized (DealGlobalset.class) {
                if (dealGlobalset == null) {
                    dealGlobalset = new DealGlobalset();
                }
            }
        }
        return dealGlobalset;
    }

    //方法0：构造函数，文件加载，类加载初始化
    public DealGlobalset(){
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        globalsetDao = (GlobalsetDao) ctx.getBean("grobalsetDao");
        log = MyLogTest.getInstance();
    }

    public Boolean updateGlobalset(GlobalSet globalset){
        return  globalsetDao.updateGlobalset(globalset) == 1?true:false;
    }

    public GlobalSet getGlobalset(){
        return  globalsetDao.getGlobalset(1);
    }
}
