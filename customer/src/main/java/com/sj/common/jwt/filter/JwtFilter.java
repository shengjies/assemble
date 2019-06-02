package com.sj.common.jwt.filter;


import com.sj.common.jwt.JwtToken;
import com.sj.common.jwt.JwtUtil;
import com.sj.common.utils.PathUtils;
import com.sj.customer.user.domain.User;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getParameter("token");
        if(StringUtils.isEmpty(token)){
            token = req.getHeader("token");
            if(StringUtils.isEmpty(token)){
                return false;
            }
        }
        User u = JwtUtil.getUserByToken(token);
        if(u == null){
            return false;
        }
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getParameter("token");
        if(StringUtils.isEmpty(token)){
            token = req.getHeader("token");
        }
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
               return executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                responseindex(request, response);
            }
        }
        responseindex(request, response);
        return false;
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void responseindex(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect(PathUtils.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
