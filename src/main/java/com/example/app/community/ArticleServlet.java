package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.util.MyBatisUtil;
import com.example.app.vo.Article;
import com.example.app.vo.ArticleLike;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        if (no == null || !no.matches("\\d+")) {
            resp.sendRedirect("/community");
            return;
        } else {
            Article found = DatabaseUtil.selectArticleByNo(no);
            if (found == null) {
                resp.sendRedirect("/community");
                return;
            }
            Member logonUser = (Member) req.getSession().getAttribute("logonUser");
            //Map map = Map.of("memberId", logonUser.getId(), "articleNo", no);

            if (logonUser != null) {
                ArticleLike articleLike = new ArticleLike(logonUser.getId(), Integer.parseInt(no));
                int r = DatabaseUtil.countByArticleLike(articleLike);
                req.setAttribute("alreadyLike", r == 1);
            } else {
                req.setAttribute("alreadyLike", false);
            }

            req.setAttribute("auth", logonUser != null);
            req.setAttribute("article", found);
            DatabaseUtil.increaseViewCnt(no);
            req.getRequestDispatcher("/community/article.jsp").forward(req, resp);
        }

    }
}
