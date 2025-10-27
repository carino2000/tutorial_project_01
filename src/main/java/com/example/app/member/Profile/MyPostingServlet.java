package com.example.app.member.Profile;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Article;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-posting")
public class MyPostingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Member m = (req.getSession().getAttribute("logonUser") == null ? null : (Member) req.getSession().getAttribute("logonUser"));
        if (m == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        List<Article> myArticles = DatabaseUtil.selectArticlesById(m.getId());
        req.setAttribute("myArticles", myArticles);
        req.getRequestDispatcher("/member/my-posting.jsp").forward(req, resp);
    }
}
