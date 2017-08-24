package bean;

/**
 * Created by catty on 2017/5/27.
 */
public class SaveLink {
    private int id; //自增ID
    private String projectname;//工程名称
    private String linkname;//链接的名称
    private String link;//保存的链接
    private String username;//链接的用户名
    private String passwd;//链接的登录密码

    public SaveLink() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
