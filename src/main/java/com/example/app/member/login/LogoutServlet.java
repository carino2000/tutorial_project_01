package com.example.app.member.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals("keepLogin")){
                cookie = c;
            }
        }
        cookie.setMaxAge(0);

        resp.addCookie(cookie);
        req.getSession().removeAttribute("logonUser");
        resp.sendRedirect("/index");

    }
}
