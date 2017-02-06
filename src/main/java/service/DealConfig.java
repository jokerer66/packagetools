package service;

import bean.Config;
import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.IConfigOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

//config 表处理类
public class DealConfig {

    private static ApplicationContext ctx = null;
    private static IConfigOperation iConfigOperation = null;

    private static DealConfig dealConfig;

    public static DealConfig getInstance() {
        if (dealConfig == null) {
            synchronized (DealConfig.class) {
                if (dealConfig == null) {
                    dealConfig = new DealConfig();
                }
            }
        }
        return dealConfig;
    }

    //方法0：构造函数，文件加载，类加载初始化
    public DealConfig(){
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        iConfigOperation = (IConfigOperation) ctx.getBean("configDao");
    }

    //方法1：获取config表中所有数据
    public List<Config> getConfigs(){

        List<Config> configs = iConfigOperation.selectConfigs();

        return configs;
    }

    public int getConfigNums(String packname){

        int config_nums = iConfigOperation.selectConfigNums(packname);

        return config_nums;
    }


    //方法2：通过id获取Config类的所有信息记录
    public Config getConfigByID(int pid) {

        Config config = iConfigOperation.selectConfigByID(pid);

        return config;
    }

    //方法3：通过name获取config类的所有信息记录
    public Config getConfigByName(String name) {

        Config config = iConfigOperation.selectConfigByName(name);

        return config;
    }

    //方法4：添加Config记录，未处理重复的情况，应为添加的时候Svninfo表会处理重复的情况
    public Boolean addConfig(Config config) {

        return iConfigOperation.addConfig(config)>0?true:false;


    }

    //方法5：更新config表
    public Boolean updateConfig(Config config) {


        return iConfigOperation.updateConfig(config)>=0?true:false;

    }

    //方法6：删除config表数据
    public String deleteConfig(String packname) {

        String flag_delete_config = "0";

        iConfigOperation.deleteConfig(packname);

        return flag_delete_config;
    }

    public String getAllConfig(String packname) {

        String flag_delete_config = "0";

        iConfigOperation.deleteConfig(packname);

        return flag_delete_config;
    }
    }
