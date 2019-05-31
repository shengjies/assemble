package com.sj.fileview.controller;

import com.sj.fileview.common.poi.ExcelUtils;
import com.sj.fileview.common.result.AjaxResult;
import com.sj.fileview.domain.Role;
import com.sj.fileview.domain.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {

    @RequestMapping("/excel")
    public void excel(HttpServletResponse response){
        List<User> userList = new ArrayList<User>();
        User u1 = new User(1,"阿杰");
        User u2 = new User(2,"阿杰1");
        User u3 = new User(3,"阿杰2");
        User u4 = new User(4,"阿杰3");
        Role role = new Role();
        role.setId(1);
        role.setName("用户角色");
        role.setUsers(userList);
        Workbook wb = ExcelUtils.createWorkbook();
        Sheet sheet = ExcelUtils.createSheet(wb);
        ExcelUtils.createHeader(sheet,Role.class);
        String fileName = ExcelUtils.encodingFilename("ceshi");
        OutputStream out =null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            out = response.getOutputStream();
            wb.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(wb != null){
                    wb.close();
                }
                if(out != null){
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
