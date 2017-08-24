package service;

import bean.Project;
import dao.ISvnInfoOperation;
import dao.ProjectDao;
import helper.log.MyLogTest;
import org.hibernate.mapping.Array;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2016/12/20.
 */
public class DealProject {
    ProjectDao projectdao = null;
    ApplicationContext ctx =null;
    MyLogTest log = null;



    private static DealProject dealProject;

    public static DealProject getInstance() {
        if (dealProject == null) {
            synchronized (DealProject.class) {
                if (dealProject == null) {
                    dealProject = new DealProject();
                }
            }
        }
        return dealProject;
    }

    //方法0：构造函数，文件加载，类加载初始化
    public DealProject(){
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        projectdao = (ProjectDao) ctx.getBean("projectDao");
        log = MyLogTest.getInstance();
    }

    public boolean isprojectexist(String projectname){
        Project project = projectdao.getProjectByname(projectname);
        if(project == null){
            return false;
        }
        return  true;
    }

    //WHERE projectname=#{projectname} and projectid != #{projectid}
    public boolean isprojectexistWithoutId(Project project){
        Project tempproject = projectdao.isprojectexistWithoutId(project);
        if(tempproject == null){
            return false;
        }
        return  true;
    }
    public boolean addproject(Project project){
        projectdao.addproject(project);
        log.level("debug","new add project id : "+project.getProjectid());
        if(project.getProjectid()<=0){
            return false;
        }
        return true;
    }
    public Project getProjectByname(String projectname){
        return projectdao.getProjectByname(projectname);
    }
//    public Project getProjectByProductname(String productname){
//        return projectdao.getProjectByProductname(productname);
//    }
    public Project getProjectByProductnameLimited(String productname,String platform){
        List<Project> list = projectdao.getProjectByProductnameAndPlatform(productname,platform);
        if(list.size()>=1){
            return list.get(0);
        }
        return null;
    }
    public int updateproject(Project project){
        return projectdao.updateProject(project);
    }

    public int deleteproject(int projectid){
        return projectdao.deleteproject(projectid);
    }
    public List<Project> getallproject(){
        return projectdao.getallproject();

    }

    public List<String> getallproductnameByPlatform(String platform){
        return projectdao.getallproductnameByPlatform(platform);
    }

}
