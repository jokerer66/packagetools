package controller.databasecontrol;

import bean.GlobalSet;
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
    public String saveglobalset(@RequestParam String js_sdkinfo, String js_code_path,String js_android_pack_path,String js_ios_pack_path,String js_tomcat_path,String js_downipa_filename,String js_svnusername,String js_svnpassword,String js_hostip) {
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
        globalSet.setSetid(1);

        return DealGlobalset.getInstance().updateGlobalset(globalSet)?"1":"0";
    }



}
