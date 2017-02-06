package helper.unit;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by apple on 2017/1/18.
 */
public class MyUnit {
    private static MyUnit myUnit;

    public static MyUnit getInstance() {
        if (myUnit == null) {
            synchronized (MyUnit.class) {
                if (myUnit == null) {
                    myUnit = new MyUnit();
                }
            }
        }
        return myUnit;
    }

    public MyUnit() {
    }

    public String getSystem_IP(){

        String flag_getIP;

        try {

            flag_getIP = InetAddress.getLocalHost().getHostAddress();

        }catch(UnknownHostException e){

            e.printStackTrace();

            flag_getIP = "error";

        }

        return flag_getIP;
    }
}
