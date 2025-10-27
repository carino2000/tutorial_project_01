package com.example.app.member.Profile;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.LikedArticle;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-article-like")
public class MyArticleLikeLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member m = (req.getSession().getAttribute("logonUser") == null ? null : (Member) req.getSession().getAttribute("logonUser"));
        if (m == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<LikedArticle> list = DatabaseUtil.selectArticleLikeAndArticleById(m.getId());
        req.setAttribute("likedArticle", list);
        req.setAttribute("member", m);
        req.getRequestDispatcher("/member/my-article-like.jsp").forward(req, resp);
    }
}
