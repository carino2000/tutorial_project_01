package com.example.app.listener;

import com.example.app.util.DatabaseUtil;
import com.example.app.vo.LoginUser;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@WebListener
public class LoginRestoreListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String ip = req.getRemoteAddr();

        Cookie found = null;
        Cookie[] cookies = req.getCookies() == null ? new Cookie[0] : req.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("keepLogin")) {
                found = c;
            }
        }
        if (found != null) {
            String id = found.getValue();
            req.getSession().setAttribute("logonUser", DatabaseUtil.selectMemberById(id));

            LoginUser user = new LoginUser(id, ip);
            int r = DatabaseUtil.insertLoginHistory(user);
            req.getSession().setAttribute("logonUser", DatabaseUtil.selectMemberById(id));
        }


        //-------------------------------------------

        if(req.getSession().getAttribute("logonUser") == null){
            req.setAttribute("auth", false);
        }else{
            req.setAttribute("auth", true);
        }


    }
}
