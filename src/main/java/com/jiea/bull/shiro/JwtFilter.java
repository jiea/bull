package com.jiea.bull.shiro;


import com.jiea.bull.common.exception.BullException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    protected boolean isLoginAttempt(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        String token = request.getHeader("token");
        return StringUtils.isNotBlank(token);
    }

    @Override
    protected boolean executeLogin(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        JwtToken token = new JwtToken(request.getHeader("token"));
        getSubject(servletRequest, servletResponse).login(token);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                throw new BullException("没有权限", HttpStatus.SC_UNAUTHORIZED);
            }
        }
        return true;
    }

    /**
     * 获取 token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return token;
    }


}
