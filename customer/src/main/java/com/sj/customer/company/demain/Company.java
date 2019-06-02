package com.sj.customer.company.demain;

public class Company {
    private int id;
    private String  cname;
    private int sid;
    private String iso_path;
    private String iso;
    private String create_time;
    private String spwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getIso_path() {
        return iso_path;
    }

    public void setIso_path(String iso_path) {
        this.iso_path = iso_path;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", sid=" + sid +
                ", iso_path='" + iso_path + '\'' +
                ", iso='" + iso + '\'' +
                ", create_time='" + create_time + '\'' +
                ", spwd='" + spwd + '\'' +
                '}';
    }
}
