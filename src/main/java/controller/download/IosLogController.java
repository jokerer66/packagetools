package controller.download;

import bean.IosLog;
import helper.unit.MyUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import process.webpage.Webpage;
import service.DealGlobalValue;
import service.DealGlobalset;
import service.DealIosLog;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/iosLog")
public class IosLogController {

    DealIosLog dealIosLog = new DealIosLog();

    //跳页，模糊查询功能
    @RequestMapping(method = RequestMethod.GET)
    public String showIosLog(@RequestParam(value = "jump_type",required = false,defaultValue = "0") int jump_type,
                             @RequestParam(value = "cur_page",required = false,defaultValue = "1") int cur_page,
                             @RequestParam(value = "change_page",required = false,defaultValue = "1") int change_page,
                             @RequestParam(value = "file_name_like",required = false,defaultValue = "") String file_name_like,ModelMap model){


        int all_nums = 0;
        int nums_one_page = 20;
        List<IosLog> iosLogs = null;
        Map<String,Integer> page_datas = new HashMap<String, Integer>();

        //获取数据库片段
        if(file_name_like.equals("")) {
            //获取查询到的数据总数量
            all_nums = dealIosLog.getIosLogNums();
            //获取处理后的页面各种数量值以及跳页数据
            page_datas = Webpage.getInstance().outputPageNums(jump_type,cur_page,nums_one_page,change_page,all_nums);
            //查询数据库数据
            iosLogs = dealIosLog.getFilenamesSection(page_datas.get("page_limit"), page_datas.get("page_offset"));
        }else {
            all_nums = dealIosLog.getIosLogFilenameNums(file_name_like);
            page_datas = Webpage.getInstance().outputPageNums(jump_type,cur_page,nums_one_page,change_page,all_nums);
            iosLogs = dealIosLog.getIosLogFilenamesSection(page_datas.get("page_limit"), page_datas.get("page_offset"), file_name_like);
        }
        model.addAttribute("context_search",file_name_like);
        model.addAttribute("all_nums",page_datas.get("page_all_nums"));
        model.addAttribute("page_num",page_datas.get("page_page_num"));
        model.addAttribute("cur_page_num",page_datas.get("page_cur_page"));
        model.addAttribute("iosLogs",iosLogs);
        model.addAttribute("systemip", DealGlobalset.getInstance().getGlobalset().getHostip());
        model.addAttribute("downipa_filename",DealGlobalset.getInstance().getGlobalset().getDownipa_filename());

        return "download/iosLog";
    }

    //废弃的跳页方法
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public void showIosLogSection(@RequestParam int start_flag,@RequestParam int nums,ModelMap model){

        List<IosLog> iosLogs;
        iosLogs = dealIosLog.getIosLogSection(start_flag, nums);

        model.addAttribute("iosLogs",iosLogs);

    }

}
