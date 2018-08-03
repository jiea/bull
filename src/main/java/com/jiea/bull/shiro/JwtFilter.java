package com.jiea.bull.shiro;


import com.alibaba.fastjson.JSON;
import com.jiea.bull.common.exception.BullException;
import com.jiea.bull.vo.Resp;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends AuthenticatingFilter {


    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken(WebUtils.toHttp(servletRequest));

        if (StringUtils.isBlank(token)) {
            return null;
        }

        return new JwtToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        if (WebUtils.toHttp(servletRequest).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken(WebUtils.toHttp(servletRequest));

        if (StringUtils.isBlank(token)) {
            HttpServletResponse response = WebUtils.toHttp(servletResponse);
            response.setContentType("application/json;charset=utf-8");
            // 允许跨域请求
            response.setHeader("Access-Control-Allow-Origin", WebUtils.toHttp(servletRequest).getHeader("Origin"));

            response.getWriter().print(JSON.toJSONString(Resp.error("token is null")));

            return false;
        }

        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletResponse response = WebUtils.toHttp(servletResponse);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", WebUtils.toHttp(servletRequest).getHeader("Origin"));
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            response.getWriter().print(JSON.toJSONString(Resp.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage())));
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取 token
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return token;
    }
}
