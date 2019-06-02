package com.sj.login.ser.api;

import com.sj.common.utils.CodeUtils;
import com.sj.common.web.AjaxResult;
import com.sj.login.ser.domain.Ser;
import com.sj.login.ser.server.ISerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ser")
public class SerApi {
    @Autowired
    private ISerServer serServer;
    /**
     * 服务器密码验证
     * @param ser
     * @return
     */
    @RequestMapping("/v")
    public AjaxResult verificationSer(@RequestBody Ser ser){
        try {
            serServer.verificationSer(ser);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error(CodeUtils.CODE_SYS_PWD,CodeUtils.CODE_SYS_PWD_MSG);
    }
}
