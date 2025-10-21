package com.example.app.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LoginUser {
    int idx;
    String loginId;
    String loginIp;
    LocalDateTime loginAt;

    public LoginUser() {
    }

    public LoginUser(String loginId, String loginIp) {
        this.loginId = loginId;
        this.loginIp = loginIp;
    }

    public LoginUser(String loginId, String loginIp, LocalDateTime loginAt) {
        this.loginId = loginId;
        this.loginIp = loginIp;
        this.loginAt = loginAt;
    }

}
