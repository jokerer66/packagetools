package controller.download;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import bean.AndroidLog;
import bean.IosLog;
import bean.Project;
import helper.file.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bean.UploadedFile;
import service.DealAndroidLog;
import service.DealGlobalset;
import service.DealIosLog;
import service.DealProject;

@Controller
public class FileUploadController {

    AndroidLog androidLog_up = null;
    IosLog iosLog_up = null;
    DealAndroidLog dealAndroidLog_up = new DealAndroidLog();
    DealIosLog dealIosLog_up = new DealIosLog();


//    @Autowired
//    FileUploadValidator fileValidator;

    @RequestMapping("/fileUploadForm")
    public String getUploadForm(ModelMap model) {
        List<String> productnamelist_android = DealProject.getInstance().getallproductnameByPlatform("android");
        List<String> productnamelist_ios = DealProject.getInstance().getallproductnameByPlatform("ios");
        model.addAttribute("productnamelist_android",productnamelist_android);
        model.addAttribute("productnamelist_ios",productnamelist_ios);
        return "download/uploadForm";
    }

    @RequestMapping("/fileUpload")
    public ModelAndView fileUploaded(@ModelAttribute("uploadedFile") UploadedFile uploadedFile,BindingResult result) {
        System.out.println("@RequestMapping(\"/fileUpload\")");
        MultipartFile file = uploadedFile.getFile();
//        fileValidator.validate(uploadedFile, result);
        if(MyFile.getInstance().uploadstandFile(file,DealGlobalset.getInstance().getGlobalset().getTomcat_path()+"/webapps/temp_down","","0","")){
            return new ModelAndView("download/showFile", "message", file.getOriginalFilename());
        }else{
            return new ModelAndView("download/showFile", "message", "uploadfailed");
        }

    }

    @RequestMapping("/androidfileUpload")
    public ModelAndView androidfileUploaded(@RequestParam("file") CommonsMultipartFile uploadedFile, @RequestParam("version") String version,@RequestParam("select_productname") String productname) {
        System.out.println("@RequestMapping(\"/androidfileUpload\")" +"version = "+version+"   testname = "+uploadedFile.getOriginalFilename()+"   productname = "+productname);
        MyFile.getInstance().uploadandroidFile(uploadedFile,DealGlobalset.getInstance().getGlobalset().getAndroid_packpath(),version,productname);
        return new ModelAndView("download/showFile", "message", uploadedFile.getOriginalFilename());
    }

    @RequestMapping("/iosfileUpload")
    public ModelAndView iosfileUploaded(@RequestParam("file") CommonsMultipartFile uploadedFile, @RequestParam("version") String version,@RequestParam("select_productname") String productname) {
        System.out.println("@RequestMapping(\"/androidfileUpload\")" +"version = "+version+"   testname = "+uploadedFile.getOriginalFilename()+"   productname = "+productname);
        MyFile.getInstance().uploadiosFile(uploadedFile,DealGlobalset.getInstance().getGlobalset().getTomcat_path()+"/webapps/downipa/"+DealGlobalset.getInstance().getGlobalset().getDownipa_filename(),version,productname);
        return new ModelAndView("download/showFile", "message", uploadedFile.getOriginalFilename());
    }

}
