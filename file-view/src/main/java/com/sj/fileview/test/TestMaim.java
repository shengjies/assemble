package com.sj.fileview.test;

import com.sj.fileview.annotation.Excel;
import com.sj.fileview.annotation.ExcelList;
import com.sj.fileview.domain.Role;
import com.sj.fileview.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class TestMaim {

    public static void main(String[] args)  throws Exception{
        List<User> userList = new ArrayList<User>();
        User u1 = new User(1,"阿杰");
        User u2 = new User(2,"阿杰1");
        User u3 = new User(3,"阿杰2");
        User u4 = new User(4,"阿杰3");
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        Role role = new Role();
        role.setId(1);
        role.setName("用户角色");
        role.setUsers(userList);
        Class clazz = role.getClass();
        Field[] f = clazz.getDeclaredFields();
        for (Field field : f) {
            ExcelList excelList = field.getAnnotation(ExcelList.class);
            if(excelList != null){
                ParameterizedType pt = (ParameterizedType)field.getGenericType();
                Class u = Class.forName(pt.getActualTypeArguments()[0].toString().replace("class","").trim());
                Field[] fields = u.getDeclaredFields();
                for (Field d : fields) {
                    Excel e = d.getAnnotation(Excel.class);
                    d.setAccessible(true);
                    if(e != null){
                        System.out.println(e.name());
                    }
                }
            }
        }
    }
}
