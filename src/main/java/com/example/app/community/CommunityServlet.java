package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Article;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/community")
public class CommunityServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Article> articles = DatabaseUtil.selectAllArticle();

        req.setAttribute("articles", articles);
        req.getRequestDispatcher("/community/main.jsp").forward(req,resp);
    }
}
