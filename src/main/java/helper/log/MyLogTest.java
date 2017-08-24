package helper.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class MyLogTest {

    private static Logger logger = Logger.getLogger(MyLogTest.class);

    private static MyLogTest mylog;
    public static MyLogTest getInstance() {
        if (mylog == null) {
            synchronized (MyLogTest.class) {
                if (mylog == null) {
                    mylog = new MyLogTest();
                }
            }
        }
        return mylog;
    }

    public MyLogTest() {
    }

    public void level(String leixing, String context)
    {
        String filePath = this.getClass().getResource("/").getPath();
        PropertyConfigurator.configure(filePath+ "log4j.properties");

        if(leixing.contains("debug")){
            logger.debug(context);
        }else if(leixing.contains("info")){
            logger.info(context);
        }else if(leixing.contains("warn")){
            logger.warn(context);
        }else if(leixing.contains("error")){
            logger.error(context);
        }

    }

    public String output_root_path(){

        String filePath = this.getClass().getResource("/").getPath();

        return filePath;

    }

}
