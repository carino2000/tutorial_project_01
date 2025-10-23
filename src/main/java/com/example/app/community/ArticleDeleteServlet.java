package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Article;
import com.example.app.vo.Member;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String articleNo = req.getParameter("no");
        Article article = DatabaseUtil.selectArticleByNo(articleNo);
        Member m = (Member)req.getSession().getAttribute("logonUser");

        System.out.println("article id : " + article.getWriterId());
        System.out.println("logonUser id : " + m.getId());
        int r1 = 0;
        int r2 = 0;
        if(m.getId().equals(article.getWriterId())){
            r1 = DatabaseUtil.deleteArticleLikeByArticleNo(article.getNo());
            r2 = DatabaseUtil.deleteArticleByNo(article.getNo());
            System.out.println("ArticleDeleteServlet : r = " + r1);
            System.out.println("ArticleDeleteServlet : r = " + r2);
        }else{
            System.out.println("id doesn't match in ArticleDeleteServlet");
            r2 = -1;
        }


        resp.sendRedirect("/community");
    }
}
