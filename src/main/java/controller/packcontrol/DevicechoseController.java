package controller.packcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/devicechose")
public class DevicechoseController {



    @RequestMapping(method = RequestMethod.GET)
    public String showdevicechose(ModelMap model){


        model.addAttribute("message","hello");

        return "package/devicechose";
    }

}
