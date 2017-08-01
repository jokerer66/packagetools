package controller.download;


import helper.file.MyFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DealGlobalset;

import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/tempdown")
public class TempDownController {

    @RequestMapping(method = RequestMethod.GET)
    public String showAndroid(ModelMap model) {


        Map<String, String> mp = new TreeMap<String, String>();
        String temp_path = DealGlobalset.getInstance().getGlobalset().getTomcat_path()+"/webapps/temp_down";

        mp = MyFile.getInstance().back_filelist(temp_path, "",100);

        model.addAttribute("temp_path", temp_path);
        model.addAttribute("filenames", mp);

        return "download/tempdown";
    }


}