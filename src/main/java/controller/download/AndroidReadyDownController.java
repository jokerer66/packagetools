package controller.download;

import bean.AndroidLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DealAndroidLog;

import java.util.List;


@Controller
@RequestMapping("/androidreadydown")
public class AndroidReadyDownController {
    DealAndroidLog dealAndroidLog = new DealAndroidLog();

    @RequestMapping(method = RequestMethod.GET)
    public String showandroidready(ModelMap model){
        List<AndroidLog> androidreadylist = dealAndroidLog.getandroidreadyinfo();
        model.addAttribute("androidreadylist",androidreadylist);
        return "download/androidreadydown";
    }
}
