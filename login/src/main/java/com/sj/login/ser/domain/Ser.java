package com.sj.login.ser.domain;

/**
 * 服务器管理
 */
public class Ser {
    private int id;
    private String sname;
    private String  sip;
    private String spath;
    private int suser_num;
    private int max_num;
    private String remark;
    private String create_time;
    private String spwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getSpath() {
        return spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }

    public int getSuser_num() {
        return suser_num;
    }

    public void setSuser_num(int suser_num) {
        this.suser_num = suser_num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }


    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }

    @Override
    public String toString() {
        return "Ser{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", sip='" + sip + '\'' +
                ", spath='" + spath + '\'' +
                ", suser_num=" + suser_num +
                ", max_num=" + max_num +
                ", remark='" + remark + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
