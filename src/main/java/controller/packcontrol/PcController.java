package controller.packcontrol;

import helper.thread.MyThread;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DealSvnInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/pc")
public class PcController {

        DealSvnInfo dealSvnInfo = DealSvnInfo.getInstance();

        @RequestMapping(method = RequestMethod.GET)
        public String showAndroid(HttpServletRequest request,ModelMap model){

            List<String> list = dealSvnInfo.getAllSvnInfoNames();

            model.addAttribute("message", list);


            return "package/pc";
        }

    //运行打包主方法，通过返回的id序号，获取对应的name，然后获取name在数据库中得各项参数，带入打包方法中进行打包
    @RequestMapping(value = "runPack",method = RequestMethod.POST)
    @ResponseBody
    public static String runPack(@RequestParam String inPara){
        String flag1 = "0";
        System.out.println("Ready to pack "+inPara);
        flag1 = new MyThread(inPara).output(inPara);
        System.out.println("flag1 from thread is = "+flag1);
        return flag1;

    }

    }
