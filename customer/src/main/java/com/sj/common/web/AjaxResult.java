package com.sj.common.web;

import java.util.HashMap;

public class AjaxResult extends HashMap<String,Object> {
    public AjaxResult(){}

    public static AjaxResult error(int code,String msg){
        AjaxResult result = new AjaxResult();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }


    public static AjaxResult error(){
        return error(0,"fail");
    }

    public static AjaxResult success(int code,String msg){
        AjaxResult result = new AjaxResult();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }

    public static AjaxResult success(String msg,Object data)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 1);
        json.put("data", data);
        return json;
    }

    public static AjaxResult success()
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", "success");
        json.put("code", 1);
        return json;
    }

    public static AjaxResult tableData(int total,Object rows){
        AjaxResult table = new AjaxResult();
        table.put("total", total);
        table.put("code", 1);
        table.put("rows", rows);
        return table;
    }

    public static AjaxResult register(String path,String token){
        AjaxResult register = new AjaxResult();
        register.put("path", path);
        register.put("code", 1);
        register.put("token", token);
        return register;
    }
}
