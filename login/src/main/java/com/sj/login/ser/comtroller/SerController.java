package com.sj.login.ser.comtroller;

import com.sj.common.web.AjaxResult;
import com.sj.login.ser.domain.Ser;
import com.sj.login.ser.server.ISerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;

/**
 * 服务器管理
 */
@Controller
@RequestMapping("/ser")
public class SerController {

    @Autowired
    private ISerServer serServer;

    @GetMapping
    public String page(HttpServletRequest request)
    {
        request.setAttribute("token",request.getParameter("token"));
        return "ser";
    }

    /**
     * 分页查询数据
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public AjaxResult serList(int page,int rows){
        return serServer.list(page,rows);
    }

    /**
     * 添加服务器信息
     * @param ser 服务器信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public AjaxResult add(Ser ser){
        try {
            serServer.add(ser);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    /**
     * 修改服务器信息
     * @param ser 服务器信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public AjaxResult edit(Ser ser){
        try {
            serServer.edit(ser);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    /**
     * 删除服务器信息
     * @param id 编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/del")
    public AjaxResult del(int id){
        try {
            serServer.del(id);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
