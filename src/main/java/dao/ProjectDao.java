package dao;

import bean.Project;

import java.util.List;

/**
 * Created by apple on 2016/12/20.
 */
public interface ProjectDao {
    public Project getProjectByname(String projectname);
    public List<Project> getProjectByProductnameAndPlatform(String productname,String paltform);
    public Project isprojectexistWithoutId(Project project);
    public int addproject(Project project);
    public int updateProject(Project project);
    public int deleteproject(int projectid);
    public List<Project> getallproject();
    public List<String> getallproductname();
    public List<String> getallproductnameByPlatform(String platform);

}
