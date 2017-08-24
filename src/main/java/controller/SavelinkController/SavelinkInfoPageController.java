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
@RequestMapping("/savelinkinfopage")
public class SavelinkInfoPageController {

    DealSaveLink dealSaveLink = new DealSaveLink();

    @RequestMapping( method = RequestMethod.GET)
    public String selectSaveLinks(HttpServletRequest request, ModelMap model) {
        System.out.println(request.getRemoteUser() + "|" + request.getRemoteAddr() + "|" + request.getRemoteHost());
        List<SaveLink> list = dealSaveLink.getSaveLinks();
        model.addAttribute("savelinkinfopage", list);
        return "savelinkinfopage/savelinkinfopage";
    }

    //方法1：添加数据到savelink表中  返回boolean？添加成功:添加失败
    @RequestMapping(value="savelinkinfo", method = RequestMethod.POST)
    @ResponseBody
    //public String addSaveLinks(HttpServletRequest request, SaveLink saveLink) {
    public String addSaveLinks(SaveLink saveLink) {
        return dealSaveLink.addlinks(saveLink) ? "1" : "0";
    }

    //方法2:通过link进行查询
    @RequestMapping(value = "searchbylink", method = RequestMethod.POST)
    @ResponseBody
    public String selectByLink(@RequestParam String link) {
        String link_use = link.trim();
        SaveLink savelink = DealSaveLink.getInstance().selectByLink(link_use);
        System.out.println("打印查询出来的结果1："+savelink);
        String page_info = null;
        int nums = DealSaveLink.getInstance().selectByLinkNums(link_use);
        System.out.println("打印查询出来的结果2："+nums);
        if (nums == 0) {
            page_info = nums + "";
        } else {
            page_info = savelink.getId() + "|" + savelink.getProjectname() + "|" + savelink.getLinkname() + "|" + savelink.getLink() + "|" + savelink.getUsername() + "|" + savelink.getPasswd();
        }
        return page_info;
    }

    //方法4：根据link删除记录
    @RequestMapping(value = "deletesavelink", method = RequestMethod.POST)
    @ResponseBody
    public String deleteSaveLink(@RequestParam String link) {
        String delete_savelink = null;
        delete_savelink = DealSaveLink.getInstance().deleteSaveLink(link);
        if(delete_savelink.contains("0")){
            delete_savelink = DealSaveLink.getInstance().deleteSaveLink(link);
        }
        return delete_savelink;
    }

}
