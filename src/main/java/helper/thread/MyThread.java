package helper.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import helper.log.MyLogTest;
import interfaces.pack.PackInterface;
import process.pack.Pack;

public class MyThread implements Callable{


    MyLogTest log = new MyLogTest();
    private String packname;
    private String svnversion;
    ExecutorService pool = null;
    public PackInterface mypack;
    public MyThread(String packname,String svnversion) {
        this.packname = packname;
        this.svnversion = svnversion;
        mypack=new Pack(packname,svnversion);
    }

    //单个线程处理方法，如果要串行处理，在Object前面加上synchronized
    @Override
    public Object call() throws Exception {

        String flag = "0";
        synchronized (this) {
            System.out.println("flag in thread 1= "+flag);
            log.level("debug","Thread starting for "+packname+" svnversion = "+svnversion);
            flag = mypack.dopack(packname);

            System.out.println("flag in thread 2= "+flag);

            notify();

            }

        return flag;
    }


    public String output(String name,String svnversion){

        String back_str="";
        try {

            System.out.println("back_str = "+back_str);
            //创建一个线程池
            pool = Executors.newFixedThreadPool(100);

            back_str = pool.submit( new MyThread(name,svnversion)).get().toString();//得到返回值

            //关闭线程池
            pool.shutdown();

        }catch(Exception e){

            log.level("debug",e.getMessage());
        }

        System.out.println("back_str = "+back_str);
        return back_str;
    }


}