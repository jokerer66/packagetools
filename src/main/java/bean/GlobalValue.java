package bean;

public class GlobalValue {

    private int id;
    private int keyid;
    private String keyname;
    private String keyvalue;
    private String keycomment;
    private String keyex1;
    private String keyex2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKeyid() {
        return keyid;
    }

    public void setKeyid(int keyid) {
        this.keyid = keyid;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public String getKeycomment() {
        return keycomment;
    }

    public void setKeycomment(String keycomment) {
        this.keycomment = keycomment;
    }

    public String getKeyex1() {
        return keyex1;
    }

    public void setKeyex1(String keyex1) {
        this.keyex1 = keyex1;
    }

    public String getKeyex2() {
        return keyex2;
    }

    public void setKeyex2(String keyex2) {
        this.keyex2 = keyex2;
    }

    @Override
    public String toString() {
        return "GlobalValue{" +
                "id=" + id +
                ", keyid=" + keyid +
                ", keyname='" + keyname + '\'' +
                ", keyvalue='" + keyvalue + '\'' +
                ", keycomment='" + keycomment + '\'' +
                ", keyex1='" + keyex1 + '\'' +
                ", keyex2='" + keyex2 + '\'' +
                '}';
    }
}
