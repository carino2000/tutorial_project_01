package com.example.app.community;

import com.example.app.util.DatabaseUtil;
import com.example.app.util.MyBatisUtil;
import com.example.app.vo.Article;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        if(no == null || !no.matches("\\d+")){
            resp.sendRedirect("/community");
            return;
        }else{
            Article found = DatabaseUtil.selectArticleByNo(no);
            if(found == null){
                resp.sendRedirect("/community");
                return;
            }
            req.setAttribute("auth", req.getSession().getAttribute("logonUser") != null);
            req.setAttribute("article", found);
            req.getRequestDispatcher("/community/article.jsp").forward(req,resp);
        }

    }
}
