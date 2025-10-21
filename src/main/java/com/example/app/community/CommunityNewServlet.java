package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Article;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/community/new")
public class CommunityNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("logonUser") == null){
            resp.sendRedirect("/login");
            return;
        }
        req.setAttribute("auth", true);
        req.setAttribute("member", req.getSession().getAttribute("logonUser"));
        req.getRequestDispatcher("/community/new.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("logonUser") == null){
            resp.sendRedirect("/login");
            return;
        }

        Member m = (Member)req.getSession().getAttribute("logonUser");
        String topic = req.getParameter("topic");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Article article = new Article(m.getId(), topic, title, content);

        int result = DatabaseUtil.insertArticle(article);
        if(result == 1){
            System.out.println("Article has been inserted");
            resp.sendRedirect("/community");
        }else{
            System.out.println("Article Post Error");
            resp.sendRedirect("/community/new");
        }

    }
}
