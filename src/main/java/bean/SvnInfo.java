package bean;

public class SvnInfo {

    private int id;
    private int pid;
    private String packname;
    private String main_version;
    private String svn_url;
    private String local_path;
    private String productname;
    private String on_off;
    private String platform;
    private String busy_status;
    private String in_time;
    private String sort;
    private String isautopack;
    private String exone;
    private String extwo;
    private String exthree;
    private String projectname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public String getSvn_url() {
        return svn_url;
    }

    public void setSvn_url(String svn_url) {
        this.svn_url = svn_url;
    }

    public String getLocal_path() {
        return local_path;
    }

    public void setLocal_path(String local_path) {
        this.local_path = local_path;
    }

    public String getOn_off() {
        return on_off;
    }

    public void setOn_off(String on_off) {
        this.on_off = on_off;
    }


    public String getBusy_status() {
        return busy_status;
    }

    public void setBusy_status(String busy_status) {
        this.busy_status = busy_status;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getMain_version() {
        return main_version;
    }

    public void setMain_version(String main_version) {
        this.main_version = main_version;
    }

    public String getExone() {
        return exone;
    }

    public void setExone(String exone) {
        this.exone = exone;
    }

    public String getExtwo() {
        return extwo;
    }

    public void setExtwo(String extwo) {
        this.extwo = extwo;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getExthree() {
        return exthree;
    }

    public void setExthree(String exthree) {
        this.exthree = exthree;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getIsautopack() {
        return isautopack;
    }

    public void setIsautopack(String isautopack) {
        this.isautopack = isautopack;
    }
}
