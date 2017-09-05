package controller.download;

import bean.IosLog;
import helper.unit.MyUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DealGlobalset;
import service.DealIosLog;

import java.util.List;


@Controller
@RequestMapping("/iosreadydown")
public class IosReadyDownController {
    DealIosLog dealiosLog = new DealIosLog();

    @RequestMapping(method = RequestMethod.GET)
    public String showiosready(ModelMap model){
        List<IosLog> iosreadylist = dealiosLog.getiosreadyinfo();
        model.addAttribute("systemip", DealGlobalset.getInstance().getGlobalset().getHostip());
        model.addAttribute("iosreadylist",iosreadylist);
        model.addAttribute("downipa_filename",DealGlobalset.getInstance().getGlobalset().getDownipa_filename());
        return "download/iosreadydown";
    }



}
