package com.example.app;

import com.example.app.util.DatabaseUtil;
import com.example.app.util.MyBatisUtil;
import com.example.app.vo.LoginUser;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/setting/history")
public class SettingHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member m = (Member)req.getSession().getAttribute("logonUser");
        List<LoginUser> list = DatabaseUtil.selectHistoryById(m.getId());
        req.setAttribute("list", list);

        req.getRequestDispatcher("/setting/history.jsp").forward(req, resp);
    }
}
