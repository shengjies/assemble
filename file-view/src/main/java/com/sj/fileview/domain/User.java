package com.sj.fileview.domain;

import com.sj.fileview.annotation.Excel;

public class User {
    @Excel(name = "编号")
    private int id;
    @Excel(name = "名称")
    private String name;

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

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
