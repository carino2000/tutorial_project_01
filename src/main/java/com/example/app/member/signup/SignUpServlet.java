package com.example.app.member.signup;

import com.example.app.util.DatabaseUtil;
import com.example.app.util.MyBatisUtil;
import com.example.app.vo.Member;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/member/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String email = req.getParameter("email");
        boolean agree = Boolean.parseBoolean(req.getParameter("agree"));
        String name = req.getParameter("name");
        String nickname = req.getParameter("nickname");
        int age = Integer.parseInt(req.getParameter("age"));
        String interest = req.getParameter("interest");


        Member m = new Member(id, pw, email, agree, name, nickname, age, interest);

        int result = DatabaseUtil.insertMember(m); //위 과정으로 치환하면 데이터베이스 유틸 안만들어도 됌

        String mainError = "";

        if (result == 1) {
            System.out.println("member signup information insert success");
            req.setAttribute("nickname", nickname);
            req.getRequestDispatcher("/member/signup-success.jsp").forward(req, resp);
        } else {
            mainError = DatabaseUtil.setErrMsg(result);

            req.setAttribute("id", id);
            req.setAttribute("pw", pw);
            req.setAttribute("email", email);
            req.setAttribute("agree", agree);
            req.setAttribute("name", name);
            req.setAttribute("nickname", nickname);
            req.setAttribute("age", age);
            req.setAttribute("interest", interest);
            req.setAttribute("mainError", mainError);
            req.getRequestDispatcher("/member/signup-fail.jsp").forward(req, resp);
        }


    }
}
