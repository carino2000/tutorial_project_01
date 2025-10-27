package com.example.app;

import com.example.app.util.DatabaseUtil;
import com.example.app.util.MyBatisUtil;
import com.example.app.vo.LoginUser;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;
import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("다시 시작 넣기");
        if(req.getParameter("msg") != null){
            int msg = Integer.parseInt(req.getParameter("msg"));
            req.setAttribute("msg", msg);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
