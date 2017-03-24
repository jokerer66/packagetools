package controller.databasecontrol;

import bean.AndroidLog;
import bean.Config;
import bean.IosLog;
import bean.SvnInfo;
import helper.http.HttpRequest;
import helper.log.MyLogTest;
import helper.svn.MySVN;
import helper.thread.MyThread;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import service.DealAndroidLog;
import service.DealGlobalset;
import service.DealIosLog;
import service.DealSvnInfo;

import java.util.*;

/**
 * Created by apple on 2017/3/21.
 */
@Service
public class AutoPack implements ApplicationListener<ContextRefreshedEvent> {

    Timer timer = new Timer();



    public void doautopack(){
        Calendar calendar = Calendar.getInstance();

        // 指定01:00:00点执行
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        MyLogTest.getInstance().level("info","start doautopack\n");
        final HttpRequest httpRequest = new HttpRequest();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                List<SvnInfo> listsvninfo_android  = DealSvnInfo.getInstance().getLastSvn("android");
                List<SvnInfo> listsvninfo_ios = DealSvnInfo.getInstance().getLastSvn("ios");
                AndroidLog lastAndroidPackinfo = null;
                IosLog lastIosPackinfo = null;
                String svnnumberInSvn_android = "",svnnumberInSvn_ios ="";

                for(int i=0;i<listsvninfo_android.size();i++){
                    svnnumberInSvn_android = MySVN.getInstance().getversion(listsvninfo_android.get(i).getSvn_url(), DealGlobalset.getInstance().getGlobalset().getSvnusername(),DealGlobalset.getInstance().getGlobalset().getSvnpassword());
                    lastAndroidPackinfo = DealAndroidLog.getInstance().getLastPackinfo(listsvninfo_android.get(i).getSvn_url());

                    MyLogTest.getInstance().level("info","svnnumberInSvn_android = "+svnnumberInSvn_android+" svnnumberInDB_android  = "+lastAndroidPackinfo.getSvn_version()+ "\n");

                    if(Integer.valueOf(svnnumberInSvn_android)>Integer.valueOf(lastAndroidPackinfo.getSvn_version())){
                        MyLogTest.getInstance().level("info","dopack android\n");
                        String flag1 = new MyThread(listsvninfo_android.get(i).getPackname(),svnnumberInSvn_android).output(listsvninfo_android.get(i).getPackname(),svnnumberInSvn_android);
                        MyLogTest.getInstance().level("info","flag from autopack android thread is = "+flag1 +" i = "+ i );

//                        httpRequest.sendGet("http://localhost:8080/pack", "");
                    }else{
                        MyLogTest.getInstance().level("info","donot pack android\n");

                    }
                }


                for(int i=0;i<listsvninfo_ios.size();i++){
                    svnnumberInSvn_ios = MySVN.getInstance().getversion(listsvninfo_ios.get(i).getSvn_url(), DealGlobalset.getInstance().getGlobalset().getSvnusername(),DealGlobalset.getInstance().getGlobalset().getSvnpassword());
                    lastIosPackinfo = DealIosLog.getInstance().getLastPackinfo(listsvninfo_ios.get(i).getSvn_url());
                    MyLogTest.getInstance().level("info","svnnumberInSvn_ios = "+svnnumberInSvn_ios+" svnnumberInDB_ios  = "+lastIosPackinfo.getSvn_version()+ "\n");

                    if(Integer.valueOf(svnnumberInSvn_ios)>Integer.valueOf(lastIosPackinfo.getSvn_version())){
                        MyLogTest.getInstance().level("info","dopack ios\n");
                        String flag1 = new MyThread(listsvninfo_ios.get(i).getPackname(),svnnumberInSvn_ios).output(listsvninfo_ios.get(i).getPackname(),svnnumberInSvn_ios);
                        MyLogTest.getInstance().level("info","flag from autopack android thread is = "+flag1 +" i = "+ i );

//                        httpRequest.sendGet("http://localhost:8080/pack", "");
                    }else{
                        MyLogTest.getInstance().level("info","donot pack ios\n");
                    }
                }


            }
        },date,1000*60*60*12);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        doautopack();
    }
}
