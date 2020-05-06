package com.example.graduation_3.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆成功后可以调用的处理器，但是此处不做特殊处理没有必要调用
 * @author Cy
 * @data 2020/5/4 - 2:29
 */
@Component
public class SimpleLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        this.redirectStrategy.sendRedirect(request, response, "/login/login");
    }
}
