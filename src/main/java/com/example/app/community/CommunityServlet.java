package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Article;
import com.example.app.vo.Member;
import com.example.app.vo.PostById;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/community")
public class CommunityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        //List<Article> articles = DatabaseUtil.selectAllArticle(); //전부
        String keyword = req.getParameter("keyword") == null ? "%%" : "%" + req.getParameter("keyword") + "%";
        List<Article> articles = DatabaseUtil.selectByKeyword(keyword, page);
        int maxPage = 0;

        if (req.getParameter("keyword") != null) {
            maxPage = DatabaseUtil.selectByLike(keyword).size() % 10 == 0 ? DatabaseUtil.selectByLike(keyword).size() / 10 : (DatabaseUtil.selectByLike(keyword).size() / 10) + 1;
        } else {
            maxPage = DatabaseUtil.selectAllArticle().size() % 10 == 0 ? DatabaseUtil.selectAllArticle().size() / 10 : (DatabaseUtil.selectAllArticle().size() / 10) + 1;
        }

        //글 많이 쓴 사람 코드
        List<PostById> topPost = DatabaseUtil.countPostTop5ById(); //수정하기

        //에디터 초이스 코드
        List<Article> topLikes = DatabaseUtil.selectTop5Likes(7);

        req.setAttribute("topPost", topPost);
        req.setAttribute("topLikes", topLikes);
        req.setAttribute("maxPage", maxPage);
        req.setAttribute("keyword", keyword.replace("%", ""));
        req.setAttribute("articles", articles);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/community/main.jsp").forward(req, resp);
    }
}
