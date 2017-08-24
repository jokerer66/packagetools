package controller.packcontrol;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {


    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        System.out.println("hello world");

        return "index";

    }

}