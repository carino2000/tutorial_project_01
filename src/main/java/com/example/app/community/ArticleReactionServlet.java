package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.ArticleLike;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/article/reaction")
public class ArticleReactionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        Member m = (Member) req.getSession().getAttribute("logonUser");
        ArticleLike articleLike = new ArticleLike(m.getId(), Integer.parseInt(no));

        int found = DatabaseUtil.countByArticleLike(articleLike);

        if (found == 1) { //이미 좋아요 눌렀으면
            DatabaseUtil.deleteByArticleLike(articleLike);
            DatabaseUtil.decreaseLikeCnt(no);
        } else { //좋아요 누르기
            int result = DatabaseUtil.insertArticleLike(articleLike);
            DatabaseUtil.increaseLikeCnt(no);
        }

        String location = "/article?no=" + no;
        resp.sendRedirect(location);


    }
}
