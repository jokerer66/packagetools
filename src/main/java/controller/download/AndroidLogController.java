package controller.download;

import bean.AndroidLog;
import helper.unit.MyUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import process.webpage.Webpage;
import service.DealAndroidLog;
import service.DealGlobalset;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/androidLog")
public class AndroidLogController {

    DealAndroidLog dealAndroidLog = new DealAndroidLog();
    Webpage webpage ;

    @RequestMapping(method = RequestMethod.GET)
    //jump_type表示跳转类型，0表示跳转首页，1表示跳转尾页，2表示向前跳一页，3表示向后跳一页.cur_page 表示当前页码
    public String showAndroidLogs(@RequestParam(value = "jump_type",required = false,defaultValue = "0") int jump_type,
                                  @RequestParam(value = "cur_page",required = false,defaultValue = "1") int cur_page,
                                  @RequestParam(value = "change_page",required = false,defaultValue = "1") int change_page,
                                  @RequestParam(value = "file_name_like",required = false,defaultValue = "") String file_name_like,ModelMap model){


        int all_nums = 0;
        int nums_one_page = 20;
        List<AndroidLog> androidLogs = null;
        Map<String,Integer> page_datas = null;

        //获取数据库片段
        if(file_name_like.equals("")) {
            //获取查询到的数据总数量
            all_nums = dealAndroidLog.getAndroidLogsNum();
            //获取处理后的页面各种数量值以及跳页数据
            page_datas = Webpage.getInstance().outputPageNums(jump_type,cur_page,nums_one_page,change_page,all_nums);
            //查询数据库数据
            androidLogs = dealAndroidLog.getAndroidLogSection(page_datas.get("page_limit"), page_datas.get("page_offset"));
        }else {
            all_nums = dealAndroidLog.getAndroidLogFilenameNums(file_name_like);
            page_datas = Webpage.getInstance().outputPageNums(jump_type,cur_page,nums_one_page,change_page,all_nums);
            androidLogs = dealAndroidLog.getAndroidLogFilenames(page_datas.get("page_limit"), page_datas.get("page_offset"),file_name_like);
        }
        model.addAttribute("context_search",file_name_like);
        model.addAttribute("all_nums",page_datas.get("page_all_nums"));
        model.addAttribute("page_num",page_datas.get("page_page_num"));
        model.addAttribute("cur_page_num",page_datas.get("page_cur_page"));
        model.addAttribute("androidLogs",androidLogs);
        model.addAttribute("systemip", DealGlobalset.getInstance().getGlobalset().getHostip());
        return "download/androidLog";
    }

    @RequestMapping(value = "showAndroidLogsSection")
    @ResponseBody
    public void showAndroidLogsSection(@RequestParam int start_flag,@RequestParam int nums,ModelMap modelMap,HttpServletResponse response){

        System.out.println("showAndroidLogsSection run");

        List<AndroidLog>  androidLogs = dealAndroidLog.getAndroidLogSection(start_flag, nums);

        modelMap.addAttribute("androidLogs",androidLogs);

        try {
            response.sendRedirect("");

        }catch (IOException e){
            e.printStackTrace();
        }

    }



}
