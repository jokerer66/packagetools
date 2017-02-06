package controller.packcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import helper.thread.MyThread;
import service.DealSvnInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
@RequestMapping("/android")
public class AndroidController {

    DealSvnInfo dealSvnInfo = new DealSvnInfo();

    @RequestMapping(method = RequestMethod.GET)
    public String showAndroid(HttpServletRequest request,ModelMap model){
        System.out.println(request.getRemoteUser()+"|"+request.getRemoteAddr()+"|"+request.getRemoteHost());

        List<String> list = dealSvnInfo.getPackNamesByPlatform("android");

        model.addAttribute("message", list);

        return "package/android";
    }









}
