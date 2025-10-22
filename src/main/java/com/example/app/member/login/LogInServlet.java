package com.example.app.member.login;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.LoginUser;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String keepLogin = req.getParameter("keepLogin");
        String ip = req.getRemoteAddr();




        int result = DatabaseUtil.login(id, pw);
        if (result == 1) { //로그인 성공!

            if (keepLogin != null) {
                Cookie cookie = new Cookie("keepLogin", id);
                cookie.setMaxAge(60 * 60 * 24 * 30); // 한 달
                resp.addCookie(cookie);
            }

            LoginUser user = new LoginUser(id, ip);
            int r = DatabaseUtil.insertLoginHistory(user);

            req.getSession().setAttribute("logonUser", DatabaseUtil.selectMemberById(id));

            resp.sendRedirect("/index");

        } else { // 로그인 실패
            String mainError = DatabaseUtil.setErrMsg(result);
            req.setAttribute("id", id);
            req.setAttribute("pw", pw);
            req.setAttribute("mainError", mainError);
            req.getRequestDispatcher("/member/login-fail.jsp").forward(req, resp);
        }


    }
}
