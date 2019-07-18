package com.sj.fileview.controller;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 图片上传操作案例
 */
@Controller
public class FileUploadController {

    @Value("${file.page}")
    private String fileUrl;

    //设置APPID/AK/SK
    public static final String APP_ID = "16721729";
    public static final String API_KEY = "7jNV8lG2iA04VdqaqclhE0pg";
    public static final String SECRET_KEY = "tNeogZZ6OEfEDNDBQmBhAegs05KdE0BU";

    @ResponseBody
    @RequestMapping("/file/upload")
    public Map<String,Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID()+".jpg";
        String filePath = fileUrl+"\\"+fileName;
        File f = new File(filePath);
        f.createNewFile();
        file.transferTo(f);
        AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        JSONObject res = client.basicGeneral(filePath,new HashMap<String, String>());
        System.out.println(res.toString());
        Map<String,Object> map = new HashMap<String, Object>();
        if(file.isEmpty()){
            map.put("code",0);
            map.put("path",null);
            return map;
        }
        map.put("code",1);
        map.put("path",file.getOriginalFilename());
        return map;
    }

    @RequestMapping("/copy")
    public String filePage(){
        return "copy";
    }

    @RequestMapping("/ewm")
    public String ewm(){
        return "aaa";
    }
}
