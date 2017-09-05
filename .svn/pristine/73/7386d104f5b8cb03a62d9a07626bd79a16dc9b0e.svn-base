package controller.databasecontrol;

import bean.GlobalSet;
import helper.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DealGlobalset;

/**
 * Created by apple on 2016/12/7.
 */

@Controller
@RequestMapping("/globalset")
public class GlobalSetController {
    @RequestMapping(method = RequestMethod.GET)
    public String showAndroid(ModelMap model) {

        GlobalSet globalSet = DealGlobalset.getInstance().getGlobalset();

        model.addAttribute("globalset",globalSet);
        return "/database/globalset";
    }

    @RequestMapping(value = "saveglobalset", method = RequestMethod.POST)
    @ResponseBody
    public String saveglobalset(@RequestParam String js_sdkinfo, String js_code_path,String js_android_pack_path,String js_ios_pack_path,String js_tomcat_path,String js_downipa_filename,String js_svnusername,String js_svnpassword,String js_hostip,String js_autopackstarthour,String js_autopackstartminute,String js_autopackperiod,String js_httprequest) {
        GlobalSet globalSet = new GlobalSet();
        globalSet.setSdkinfo(js_sdkinfo);
        globalSet.setCodepath(js_code_path);
        globalSet.setAndroid_packpath(js_android_pack_path);
        globalSet.setIos_packpath(js_ios_pack_path);
        globalSet.setTomcat_path(js_tomcat_path);
        globalSet.setDownipa_filename(js_downipa_filename);
        globalSet.setSvnusername(js_svnusername);
        globalSet.setSvnpassword(js_svnpassword);
        globalSet.setHostip(js_hostip);
        globalSet.setAutopackstarthour(js_autopackstarthour);
        globalSet.setAutopackstartminute(js_autopackstartminute);
        globalSet.setAutopackperiod(js_autopackperiod);
        globalSet.setHttprequest(js_httprequest);
        globalSet.setSetid(1);

        return DealGlobalset.getInstance().updateGlobalset(globalSet)?"1":"0";
    }
    @RequestMapping(value = "resettimer", method = RequestMethod.POST)
    @ResponseBody
    public String resettimer(@RequestParam String js_autopackstarthour,String js_autopackstartminute,String js_autopackperiod ){
        try{
            AutoPack.getInstance().resettimer(Integer.valueOf(js_autopackstarthour),Integer.valueOf(js_autopackstartminute),Integer.valueOf(js_autopackperiod));
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }


    @RequestMapping(value = "sendhttprequest", method = RequestMethod.POST)
    @ResponseBody
    public String resettimer(@RequestParam String js_httprequest){
        try{
            if(HttpRequest.sendPost("") != null){
                return "1";
            }else{
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }

}
