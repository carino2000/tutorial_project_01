package com.example.app.member.Profile;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-membership")
public class DeleteMembershipServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Member m = (req.getSession().getAttribute("logonUser") == null ? null : (Member) req.getSession().getAttribute("logonUser"));
        if (m == null) {
            resp.sendRedirect("/login");
            return;
        }

        DatabaseUtil.deleteAllMemberInfoById(m.getId());
        resp.sendRedirect("/logout?msg=deleteMember");


    }
}
