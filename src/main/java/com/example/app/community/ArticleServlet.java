package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.util.MyBatisUtil;
import com.example.app.vo.Article;
import com.example.app.vo.ArticleLike;
import com.example.app.vo.Comment;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
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

            //글 상세보기 코드 시작
            Member logonUser = (Member) (req.getSession().getAttribute("logonUser") == null ? null : req.getSession().getAttribute("logonUser"));
            //Map map = Map.of("memberId", logonUser.getId(), "articleNo", no);

            if (logonUser != null) { //로그인 되어있으면
                ArticleLike articleLike = new ArticleLike(logonUser.getId(), Integer.parseInt(no));
                int r = DatabaseUtil.countByArticleLike(articleLike);
                req.setAttribute("alreadyLike", r == 1);


                if(logonUser.getId().equals(found.getWriterId())){ //자신이 쓴 글이면
                    req.setAttribute("owner", true);
                }else{
                    req.setAttribute("owner", false);
                }
            } else {//로그인 안되어있으면
                req.setAttribute("alreadyLike", false);
            }

            List<Comment> comments = DatabaseUtil.selectAllCommentsByArticleNo(Integer.parseInt(no));

            req.setAttribute("logonUser", logonUser);
            req.setAttribute("comments", comments);
            req.setAttribute("article", found);
            DatabaseUtil.increaseViewCnt(no);
            req.getRequestDispatcher("/community/article.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String articleNo = req.getParameter("articleNo");
        String comments = req.getParameter("comments");
        Member writer = (Member) req.getSession().getAttribute("logonUser");
        if(articleNo == null || comments == null){
            resp.sendRedirect("/community");
            return;
        }else if(writer == null){
            resp.sendRedirect("/login");
            return;
        }

        Comment comment = new Comment(Integer.parseInt(articleNo), writer.getId(), comments);
        int r = DatabaseUtil.insertComment(comment);
        if(r != 1){
            System.out.println("error in comment insert servlet");
            resp.sendRedirect("/article?no=" + articleNo);
            return;
        }

        resp.sendRedirect("/article?no=" + articleNo);


    }
}
