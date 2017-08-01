package controller.databasecontrol;

import bean.Config;
import bean.Project;
import bean.SvnInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import process.pack.PackInfo;
import service.DealConfig;
import service.DealProject;
import service.DealSvnInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2016/12/7.
 */

@Controller
@RequestMapping("/addinfo")
public class AddInfoController {
    @RequestMapping(method = RequestMethod.GET)
    public String showAndroid(ModelMap model) {

        List<Project> projectlist = null;
        projectlist = DealProject.getInstance().getallproject();
        model.addAttribute("projectlist", projectlist);

        return "/database/addinfo";
    }


    @RequestMapping(value = "check_repeat", method = RequestMethod.POST)
    @ResponseBody
    public String check_repeat(@RequestParam String packname) {

        String nums_str = DealSvnInfo.getInstance().selectSvnInfoNums(packname) + "";
        System.out.println("Nums got from database is : " + nums_str);
        return nums_str;
    }

    @RequestMapping(value = "search_svninfo", method = RequestMethod.POST)
    @ResponseBody
    public String search_svninfo(@RequestParam String packname) {

        String packname_use = packname.trim();

        SvnInfo svnInfo = DealSvnInfo.getInstance().getSvnInfoByName(packname_use);
        Config config = DealConfig.getInstance().getConfigByName(packname_use);

        String page_info = null;
        int nums = DealSvnInfo.getInstance().selectSvnInfoNums(packname_use);
        int config_nums = DealConfig.getInstance().getConfigNums(packname_use);

        if (nums == 0 || config_nums == 0) {

            page_info = nums + "";

        } else {

            page_info = svnInfo.getPid() + "|" + svnInfo.getPackname() + "|" + svnInfo.getSvn_url() + "|" + svnInfo.getMain_version() + "|" + svnInfo.getProjectname() + "|" + svnInfo.getIsautopack();

        }

        return page_info;

    }

    @RequestMapping(value = "saveInfoquick", method = RequestMethod.POST)
    @ResponseBody
    public String saveInfoquick(@RequestParam String ctr_pid, String ctr_projectname, String ctr_packname, String ctr_svnurl, String ctr_main_version, String ctr_isautopack) {

        String flag_saveinfoquick = "0";
        Map page_map = new HashMap();

        //判断用户名是否在数据库中重复，如果重复则更新，如果不重复则插入
        String nums_str = DealSvnInfo.getInstance().selectSvnInfoNums(ctr_packname.trim()) + "";

        //如果插入的数据packname在数据库中已经存在，则返回更新失败
        /*
         * pid = do not,nums_cts = 1   插入数据重复，返回失败
         * pid = 1xxx , nums_cts = 1   更新数据,这里有三种情况需要处理，名字没有变更，名字变更后数据库中不存在重复记录，名字更新后数据库已经存在重复记录
         * pid = 1xxx , nums_cts = 0   不存在这种情况
         * pid = do not,nums_cts = 0   插入数据
         */
        Config config1 = DealConfig.getInstance().getConfigByName(ctr_packname.trim());
        if (ctr_pid.contains("do not") && nums_str.contains("1")) {

            flag_saveinfoquick = "4";
            //pid = 1xxx , nums_cts = 1   更新数据,名字更新后数据库已经存在重复记录的情况的处理
        } else if (!ctr_pid.contains("do not") && nums_str.contains("1") && !(config1.getPid() == Integer.parseInt(ctr_pid))) {

            flag_saveinfoquick = "7";

        } else {

            page_map.put("ctr_pid", ctr_pid.trim());
            page_map.put("ctr_projectname", ctr_projectname.trim());
            page_map.put("ctr_packname", ctr_packname.trim());
            page_map.put("ctr_svnurl", ctr_svnurl.trim());
            page_map.put("ctr_main_version", ctr_main_version.trim());
            page_map.put("ctr_isautopack", ctr_isautopack.trim());
            flag_saveinfoquick = PackInfo.getInstance().savepackinfo(page_map);

        }
        return flag_saveinfoquick;

    }

    @RequestMapping(value = "delete_svninfo_config", method = RequestMethod.POST)
    @ResponseBody
    public String delete_svninfo_config(@RequestParam String packname) {

        String flag_delete_svninfo_config = null;


        flag_delete_svninfo_config = DealSvnInfo.getInstance().deleteSvnInfo(packname);

        if (flag_delete_svninfo_config.contains("0")) {

            flag_delete_svninfo_config = DealConfig.getInstance().deleteConfig(packname);

        }


        return flag_delete_svninfo_config;
    }

    @RequestMapping(value = "delete_dsym", method = RequestMethod.POST)
    @ResponseBody
    public String delete_dsym_page() {

        String flag_delete_dsym = "0";

        //myDeal.delete_dsym_deal();

        return flag_delete_dsym;
    }


}