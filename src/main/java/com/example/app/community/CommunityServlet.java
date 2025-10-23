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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        //List<Article> articles = DatabaseUtil.selectAllArticle(); //전부
        List<Article> articles = DatabaseUtil.selectArticlesByPage(page); //10개씩

        req.setAttribute("articles", articles);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/community/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        //List<Article> articles = DatabaseUtil.selectAllArticle(); //전부
        List<Article> articles = DatabaseUtil.selectArticlesByPage(page); //10개씩
        int maxPage = DatabaseUtil.selectAllArticle().size() % 10 == 0 ?  DatabaseUtil.selectAllArticle().size() / 10 : (DatabaseUtil.selectAllArticle().size() / 10) + 1;

        req.setAttribute("maxPage", maxPage);
        req.setAttribute("articles", articles);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/community/main.jsp").forward(req, resp);
    }
}
