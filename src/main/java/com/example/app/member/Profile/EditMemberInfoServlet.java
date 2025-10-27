package com.example.app.member.Profile;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.LoginUser;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-page")
public class EditMemberInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member m = (req.getSession().getAttribute("logonUser") == null ? null : (Member) req.getSession().getAttribute("logonUser"));
        if (m == null) {
            resp.sendRedirect("/login");
            return;
        }

        List<LoginUser> loginHistory = DatabaseUtil.selectHistoryById(m.getId());


        if(req.getParameter("msg") != null){
            req.setAttribute("msg",  req.getParameter("msg"));
        }
        req.setAttribute("member", m);
        req.setAttribute("loginHistory", loginHistory);

        req.getRequestDispatcher("/member/my-page.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("id");
        String name = req.getParameter("name").isEmpty() ?  null : req.getParameter("name");
        String nickname = req.getParameter("nickname").isEmpty() ?  null : req.getParameter("nickname");
        String email = req.getParameter("email").isEmpty() ?  null : req.getParameter("email");
        String ageStr = req.getParameter("age").isEmpty() ?  null : req.getParameter("age");
        int age = 0;
        String interest = req.getParameter("interest").isEmpty() ?  "없음" : req.getParameter("interest");
        String msg = null;

        if(name != null && nickname != null && email != null && ageStr != null) {
            age = Integer.parseInt(ageStr);
            Member m = new Member(id, email, name, nickname, age, interest);
            int r = DatabaseUtil.updateMemberInfo(m);
            System.out.println("mypage post updateMemberInfo = " + r);
            resp.sendRedirect("/logout?msg=profileEdit");
        }else{
            System.out.println("error in memberinfo edit");
            resp.sendRedirect("/my-page");
        }


    }
}
