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
@RequestMapping("/logfiledown")
public class LogFileDownController {

    @RequestMapping(method = RequestMethod.GET)
    public String showLogFile(ModelMap model) {


        Map<String, String> mp = new TreeMap<String, String>();
        String log_path = DealGlobalset.getInstance().getGlobalset().getTomcat_path()+"/webapps/logs";
        MyFile.getInstance().addDirs(log_path);

        mp = MyFile.getInstance().back_filelist(log_path, ".log",20);

        model.addAttribute("logfile_path", log_path);
        model.addAttribute("filenames", mp);

        return "download/logfiledown";
    }

}
