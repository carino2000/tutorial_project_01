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

@WebServlet("/article/edit")
public class ArticleEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String articleNo = req.getParameter("no");
        Article article = DatabaseUtil.selectArticleByNo(articleNo);
        Member m = (Member) req.getSession().getAttribute("logonUser");

        if (article != null && m.getId().equals(article.getWriterId())) {
            req.setAttribute("article", article);
            req.getRequestDispatcher("/community/edit.jsp").forward(req, resp);
        } else {
            System.out.println("Error int ArticleEditServlet");
            resp.sendRedirect("/community");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int no = Integer.parseInt(req.getParameter("no"));
        String id = req.getParameter("id");
        String topic = req.getParameter("topic");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Member m = (Member) req.getSession().getAttribute("logonUser");
        Article article = new Article(no, id, topic, title, content);

        int r = 0;

        if (m.getId().equals(article.getWriterId())) {
            r = DatabaseUtil.updateArticle(article);
            resp.sendRedirect("/article?no=" + no);
            return;
        } else {
            System.out.println("id doesn't match in ArticleDeleteServlet");
            r = -1;
            resp.sendRedirect("/article?no=" + no);
            return;
        }


    }
}
