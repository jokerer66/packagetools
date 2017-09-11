package controller.packcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DealSvnInfo;

import java.util.List;


@Controller
@RequestMapping("/ios")
public class IphoneController {

    DealSvnInfo dealSvnInfo = new DealSvnInfo();

    @RequestMapping(method = RequestMethod.GET)
    public String showIphone(ModelMap model){


        List<String> list = dealSvnInfo.getPackNamesByPlatform("ios");

        model.addAttribute("message", list);


        return "package/iphone";
    }



}
