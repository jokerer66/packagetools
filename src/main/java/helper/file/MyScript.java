package helper.file;

import helper.log.MyLogTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by apple on 2016/12/16.
 */
public class MyScript {
    MyFile myFile = MyFile.getInstance();
    MyLogTest log = MyLogTest.getInstance();

    private static MyScript myscript;
    public static MyScript getInstance() {
        if (myscript == null) {
            synchronized (MyScript.class) {
                if (myscript == null) {
                    myscript = new MyScript();
                }
            }
        }
        return myscript;
    }

    public MyScript() {
    }

    /**
     *
     * @param file_path  脚本文件的路径
     * @param file_context 脚本内容
     * @param log_level 日志输出级别
     * @return
     */
    public boolean create_exe_file(String file_path, String file_context, String log_level) {
        boolean flag = true;
        if (new File(file_path).exists()) {
            new File(file_path).delete();
        }
        String touch_file_command = "touch " + file_path;//创建脚本
        String chmod_file_command = "chmod 755 " + file_path;//赋予执行权限
        myFile.writerFile(file_path, file_context);
        run_command(touch_file_command, log_level);
        run_command(chmod_file_command, log_level);
        return flag;
    }

    //调用shell命令的方法
    public String run_command(String command_str, String log_level) {
        String flag_run_command = "0";
        try {
            Process process = Runtime.getRuntime().exec(command_str);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = null;
            while ((line = input.readLine()) != null) {
                log.level(log_level, line);
            }
            input.close();
            ir.close();
        } catch (IOException e) {
            log.level("error", e.getMessage());
            flag_run_command = "401";
        }
        return flag_run_command;
    }

    //处理复杂的shell脚本
    public String run_command_file(String file_context, String file_path, String log_level) {
        String iospack_flag = "400";
        myFile.writerFile(file_path, "#!/bin/sh\n" + file_context);
        String command_str = "sh " + file_path;
        Process process = null;
        InputStreamReader ir = null;
        LineNumberReader input = null;
        LineNumberReader input_error = null;
        try {
            process = Runtime.getRuntime().exec(command_str);
            input = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            input_error = new LineNumberReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            //当line=null的时候退出整个循环，导致Android包编译不下去。
            while ((line = input.readLine()) != null) {
                log.level(log_level, line);
               /* if(log_level.contains("info") && (line.contains("errors generated") || line.contains("** BUILD FAILED **") || line.contains("Code Sign error"))) {

                    iospack_flag = "401";
                    break;
                }*/
            }
            //输出错误日志
            String line1 = "";
            while ((line1 = input_error.readLine())!=null){

                log.level(log_level,line1);
            }
            input.close();
            ir.close();
        } catch (Exception e) {
            log.level("error", e.getMessage());
        }
        return iospack_flag;
    }

    //处理复杂的shell脚本
    public String run_command_file_env(String file_context, String file_path, String log_level) {

        String iospack_flag = "400";
        myFile.writerFile(file_path, "#!/bin/sh\n" + file_context);
        String command_str = "sh " + file_path;
        Process process = null;
        InputStreamReader ir = null;
        LineNumberReader input = null;
        LineNumberReader input1 = null;
        try {
            String system_PATH = System.getenv().get("PATH");
            log.level(log_level, "PATH : " + system_PATH);
            //System.getenv();//获取环境变量
            process = Runtime.getRuntime().exec(command_str,new String[]{"PATH="+system_PATH});//重新加载环境变量，解决子线程与父线程的环境变量中端口冲突的问题
            //LineNumberReader errorInputReader = new LineNumberReader(new InputStreamReader(process.getErrorStream()));//错误日志的获取调试使用
            input = new LineNumberReader(new InputStreamReader(process.getInputStream()));
            input1 = new LineNumberReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            String line1 = "";
            while ((line = input.readLine()) != null) {
                log.level(log_level, "debug = "+line);
                if(line.contains("debug") && (line.contains("BUILD FAILED") )) {
                    iospack_flag = "401";
                    break;
                }
            }
            while((line1 = input1.readLine())!=null){
                log.level(log_level,"error = "+line1);
            }
            input.close();
            ir.close();
        } catch (Exception e) {
            log.level("error", e.getMessage());
        }
        return iospack_flag;
    }

}
