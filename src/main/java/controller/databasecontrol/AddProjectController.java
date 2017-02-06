package controller.databasecontrol;

import bean.Config;
import bean.Product;
import bean.SvnInfo;
import dao.ProjectDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.*;
import bean.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2016/12/19.
 */
@Controller
@RequestMapping("/addproject")
public class AddProjectController {



    @RequestMapping(method = RequestMethod.GET)
    public String addproject(ModelMap model) {
        model.addAttribute("systemname",System.getProperties().getProperty("user.name"));
        return "/database/addproject";
    }


    @RequestMapping(value = "isprojectexist", method = RequestMethod.POST)
    @ResponseBody
    public String isprojectexist(@RequestParam String projectname) {
        return DealProject.getInstance().isprojectexist(projectname)?"1":"0";
    }

    @RequestMapping(value = "saveproject", method = RequestMethod.POST)
    @ResponseBody
    public String saveproject(@RequestParam String js_proid,String js_projectname,String js_productname,String js_iosbundleid,String js_onoff,String js_platform,
                              String js_before_context,String js_on_context,String js_after_context,
                              String js_iosbuildtype,String js_proversion_profile,String js_iosapp_path) {
        Map page_map = new HashMap();
        Project project = new Project();
        String  flag = "-1";//0新增失败,1 新增成功,2编辑失败,3编辑成功,4修改后的名字已经存在

        project.setProjectname(js_projectname);
        project.setProductname(js_productname);
        project.setIosbundleid(js_iosbundleid);
        project.setOnoff(js_onoff);
        project.setPlatform(js_platform);
        project.setExe_before_context(js_before_context.replace("<br>","\n"));
        project.setExe_on_context(js_on_context.replace("<br>","\n"));
        project.setExe_after_context(js_after_context.replace("<br>","\n"));
        project.setIosbuildtype(js_iosbuildtype);
        project.setProversionprofile(js_proversion_profile);
        project.setIosapppath(js_iosapp_path);

        Product product = new Product();
        product.setProductname(js_productname);
        product.setExone("");
        product.setExthree("");
        product.setExtwo("");
        if(!DealProduct.getInstance().isProductExist(js_productname)){
            DealProduct.getInstance().addproduct(product);
        }

        if (js_proid.contains("do not need to write")){
            //新增

            flag = DealProject.getInstance().addproject(project)?"1":"0";
        }else {
            //编辑
            project.setProjectid(Integer.valueOf(js_proid));
            if(DealProject.getInstance().isprojectexistWithoutId(project)){
                flag = "4";
            }else{
                //并更新对应svninfo config的pack相关信息
                List<SvnInfo> list_svninfo = DealSvnInfo.getInstance().getAllSvninfoByProjectname(js_projectname);
                Config tempconfig = null;
                String packpath="",versions_path_deal="";
                if(list_svninfo.size() > 0 ){
                    for(SvnInfo tempsvninfo:list_svninfo){
                        tempsvninfo.setProductname(project.getProductname());
                        tempsvninfo.setOn_off(project.getOnoff());
                        tempsvninfo.setPlatform(project.getPlatform());
                        tempsvninfo.setProjectname(project.getProjectname());

                        tempconfig = DealConfig.getInstance().getConfigByName(tempsvninfo.getPackname());
                        if(project.getPlatform().equals("android")){
                            packpath = DealGlobalset.getInstance().getGlobalset().getAndroid_packpath();
                        }else if(project.getPlatform().equals("ios")){
                            packpath = DealGlobalset.getInstance().getGlobalset().getIos_packpath();
                            versions_path_deal = "versions";
                        }
                        tempconfig.setStore_path(packpath.substring(packpath.lastIndexOf("/")+1));
                        tempconfig.setStore_root_path(packpath.substring(0,packpath.lastIndexOf("/")));
                        tempconfig.setVersions_path(versions_path_deal);

                        DealConfig.getInstance().updateConfig(tempconfig);
                        DealSvnInfo.getInstance().updateSvnInfo(tempsvninfo);
                    }
                }

                flag = DealProject.getInstance().updateproject(project)>=0?"3":"2";
            }


        }
        return flag;
    }

    @RequestMapping(value = "searchproject", method = RequestMethod.POST)
    @ResponseBody
    public String searchproject(@RequestParam String projectname) {
        Project project = DealProject.getInstance().getProjectByname(projectname);
        if(project == null)
            return "0";
        String projectinfo = project.getProjectid()+ "|" +project.getProjectname()+ "|" +project.getProductname() + "|" + project.getIosbundleid()+"|" + project.getOnoff()+ "|" + project.getPlatform()+ "|" +
                project.getExe_before_context().replace("\n","<br>")+ "|" + project.getExe_on_context().replace("\n","<br>")+ "|" +project.getExe_after_context().replace("\n","<br>") + "|" +
                project.getIosbuildtype()+ "|" + project.getProversionprofile()+ "|"+ project.getIosapppath();
        return projectinfo;
    }

    @RequestMapping(value = "deleteproject", method = RequestMethod.POST)
    @ResponseBody
    public String deleteproject(@RequestParam String projectid) {
        String flag = "0";
        if(projectid.contains("do not need to write") || projectid==null || projectid == ""){

        }else{
            flag = DealProject.getInstance().deleteproject(Integer.valueOf(projectid))>=0?"1":"0";
        }

        return flag;
    }
}
