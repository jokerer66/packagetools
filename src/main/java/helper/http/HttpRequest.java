package helper.http;


import helper.log.MyLogTest;
import service.DealGlobalset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        MyLogTest.getInstance().level("debug","get get request:"+url+"?"+param);
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
<<<<<<< HEAD
        MyLogTest.getInstance().level("debug","get post request:"+url+"?"+param);
=======
>>>>>>> origin/master
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("str   =  "+ result);
        return result;
    }

    public static String sendPost(String platform){
        String url = "";
        String param = "";
        String tempstr = "";
        String fullurl = DealGlobalset.getInstance().getGlobalset().getHttprequest();
        url = fullurl.substring(0,fullurl.indexOf("?"));
        param = fullurl.substring(fullurl.indexOf("?")+1);

        if(platform.toLowerCase().equals("android")){
            param = "devicename=0815f819b2770102(SM-N9200)&" + param.substring(param.indexOf("&")+1);
            tempstr = sendPost(url,param);
        }else if(platform.toLowerCase().equals("ios")){
            param = "devicename=8dbf13a9fd894edfcc537cfc89399b8f03754182(iPhone%20(10.3.2))&" + param.substring(param.indexOf("&")+1);
            tempstr = sendPost(url,param);
        }else{
//            tempstr = sendPost(url,param);
        }
        return tempstr;
    }

//    public static void main(String[] args) {
//        //发送 GET 请求
////        String s=HttpRequest.sendGet("http://confluence.somaapp.com/pages/viewpage.action", "pageId=13211761");
////        System.out.println("get = "+s);
//        //发送 POST 请求
////        String sr=HttpRequest.sendPost("http://localhost:8080/packagetools/addinfo/saveInfoquick", "ctr_pid=1073&ctr_projectname=soma-ios-online&ctr_packname=soma-ios-1.6.5-debug-testst&ctr_svnurl=https://svn.somaapp.com/soma/trunk/ios/SOMA/trunk/BaBa-iOS-1.6.5&ctr_main_version=1.6.8&ctr_isautopack=0");
////        System.out.println("post = "+sr);
//        sendPost("");
//    }
}