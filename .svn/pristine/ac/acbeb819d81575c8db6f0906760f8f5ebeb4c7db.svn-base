package service;

import bean.GlobalValue;
import dao.IGlobalValueOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

//处理globalvalue的类
public class DealGlobalValue {

    private ApplicationContext ctx = null;
    private IGlobalValueOperation iGlobalValueOperation = null;

    //方法0：构造方法，初始化配置文件以及接口
    public DealGlobalValue(){

        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        iGlobalValueOperation = (IGlobalValueOperation) ctx.getBean("globalValueDao");
    }

    //方法1：获取globalvalue表所有数据
    public List<GlobalValue> getGlobalValues(){

         List<GlobalValue> globalValues = iGlobalValueOperation.selectGlobalValues();

        return globalValues;
    }

    //方法2：通过keyid获取globalvalue单行的所有数据
    public GlobalValue getGlobalValueByKeyID(int keyID) {

        GlobalValue globalValue = iGlobalValueOperation.selectGlobalValueByKeyID(keyID);

        return globalValue;
    }

    //方法3：通过keyname获取globalvalue单行的所有数据
    public GlobalValue getGlobalValueByName(String keyname) {

        GlobalValue globalValue = iGlobalValueOperation.selectGlobalValueByName(keyname);

        return globalValue;
    }

    //方法4：插入globalvalue数据
    public String addGlobalValue(GlobalValue globalValue) {

        String flag = "0";
        iGlobalValueOperation.addGlobalValue(globalValue);
        return flag;
    }

    //方法5：更新globalvalue数据
    public String updateGlobalValue(GlobalValue globalValue) {

        String flag_update_globalvalue = "0";

        iGlobalValueOperation.updateGlobalValue(globalValue);

        return flag_update_globalvalue;
    }

    //方法6：
    public void deleteGlobalValue(int keyid) {

        iGlobalValueOperation.deleteGlobalValue(keyid);

    }

}
