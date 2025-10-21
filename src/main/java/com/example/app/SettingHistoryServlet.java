package com.example.app;

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
        SqlSession session = MyBatisUtil.build().openSession(true);
        Member m = (Member)req.getSession().getAttribute("logonUser");
        List<LoginUser> list = session.selectList("mappers.LoginHistoryMapper.selectByLoginId", m.getId());
        req.setAttribute("list", list);
        req.setAttribute("auth", true);

        req.getRequestDispatcher("/setting/history.jsp").forward(req, resp);
    }
}
