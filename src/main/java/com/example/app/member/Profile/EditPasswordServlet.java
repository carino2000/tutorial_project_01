package com.example.app.member.Profile;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/password-changes")
public class EditPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute("logonUser");
        if (m == null) {
            resp.sendRedirect("/login");
            return;
        }

        if (req.getParameter("fail") != null) {
            int errorCode = Integer.parseInt(req.getParameter("fail"));
            String mainError = DatabaseUtil.setErrMsg(errorCode);
            req.setAttribute("mainError", mainError);
        }

        req.setAttribute("member", m);
        req.getRequestDispatcher("/member/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pw = req.getParameter("pw");
        String newPw = req.getParameter("newPw");
        Member m = (Member) req.getSession().getAttribute("logonUser");

        if (!pw.equals(m.getPw())) {
            resp.sendRedirect("/password-changes?fail=509");
        } else if (DatabaseUtil.isNotValidPw(newPw)) {
            resp.sendRedirect("/password-changes?fail=503");
        } else if (pw.equals(newPw)) {
            resp.sendRedirect("/password-changes?fail=510");
        } else {
            Map map = Map.of("newPw", newPw, "id", m.getId());
            int result = DatabaseUtil.updateMemberPw(map);
            resp.sendRedirect("/logout?msg=msg");
            return;
        }


    }
}
