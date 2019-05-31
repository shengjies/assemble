package com.sj.fileview.common.result;

import java.util.HashMap;

public class AjaxResult extends HashMap<String,Object> {

    public AjaxResult(){}

    public static AjaxResult success(String msg){
        AjaxResult json = new AjaxResult();
        json.put("msg",msg);
        json.put("code",0);
        return json;
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
