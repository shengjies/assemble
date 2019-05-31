package com.sj.fileview.domain;

import com.sj.fileview.annotation.ExcelList;

import java.util.List;

public class Role {
    private int id;
    private String name;

    @ExcelList(name = "用户集合")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
