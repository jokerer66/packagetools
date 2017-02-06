package controller.download;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DealConfig;
import helper.file.MyFile;
import service.DealGlobalset;

import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/dsymDown")
public class DsymDownController {

    MyFile mf ;

    @RequestMapping(method = RequestMethod.GET)
    public String showAndroid(ModelMap model) {


        Map<String, String> mp = new TreeMap<String, String>();
        mf = MyFile.getInstance();
        String dsym_path = DealGlobalset.getInstance().getGlobalset().getIos_packpath()+"/store_dsym";
        mf.addDirs(dsym_path);
        mp = mf.back_filelist(dsym_path, ".zip",500);

        model.addAttribute("dsym_path", dsym_path);
        model.addAttribute("filenames", mp);

        return "download/dsymDown";
    }


}