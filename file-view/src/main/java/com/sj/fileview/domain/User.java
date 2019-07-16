package com.sj.fileview.domain;

import com.sj.fileview.annotation.Excel;
import com.sj.fileview.common.poi.annotation.Pdf;

public class User {
    @Excel(name = "编号")
    @Pdf(name = "用户编号")
    private int id;
    @Excel(name = "名称")
    @Pdf(name = "用户名称",colspan = 2)
    private String name;

    @Pdf(name = "用户邮箱",colspan = 3)
    private String email;

    @Pdf(name = "用户年龄")
    private int age;

    @Pdf(name = "性别")
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
