package controller.download;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logChoose")
public class LogChooseController {



    @RequestMapping(method = RequestMethod.GET)
    public String showstorechose(ModelMap model){


        model.addAttribute("message","hello");

        return "download/logChoose";
    }

}
