package com.example.app.member.Profile;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.LoginUser;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-loginHistory")
public class MyLoginHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute("logonUser");
        if (m == null) {
            resp.sendRedirect("/login");
            return;
        }

        List<LoginUser> history = DatabaseUtil.selectHistoryById(m.getId());
        int maxPage = history.size() % 20 == 0 ? history.size() / 20 : history.size() / 20 + 1;

        int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));


        req.setAttribute("currentPage", page);
        req.setAttribute("maxPage", maxPage);
        req.setAttribute("member", m);
        req.setAttribute("history", DatabaseUtil.selectHistoryByIdAndPage(m.getId(), page));
        req.getRequestDispatcher("/member/my-loginHistory.jsp").forward(req, resp);
    }
}
