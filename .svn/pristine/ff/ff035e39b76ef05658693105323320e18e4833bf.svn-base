package controller.SavelinkController;

import bean.SaveLink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DealSaveLink;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SavelinkController {

    DealSaveLink dealSaveLink = new DealSaveLink();

    //1：进入到savelink.jsp页面
    @RequestMapping(value="/savelink", method = RequestMethod.GET)
    public String selectSaveLinks(HttpServletRequest request, ModelMap model) {
        System.out.println(request.getRemoteUser() + "|" + request.getRemoteAddr() + "|" + request.getRemoteHost());
        List<SaveLink> list = dealSaveLink.getSaveLinks();
        model.addAttribute("savelink", list);
        return "savelinkpage/savelink";
    }

    //2：进入到savelinkinfopage.jsp页面，命名为addsavelinkinfo
    @RequestMapping(value="addsavelinkinfo", method = RequestMethod.GET)
    public String showSaveLink(HttpServletRequest request, ModelMap model) {
        System.out.println(request.getRemoteUser() + "|" + request.getRemoteAddr() + "|" + request.getRemoteHost());
        List<SaveLink> list = dealSaveLink.getSaveLinks();
        model.addAttribute("addsavelinkinfo", list);
        return "savelinkpage/savelinkinfopage";
    }

}
