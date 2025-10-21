package com.example.app.vo;
//Value Object

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    String id;
    String pw;
    String email;
    boolean agree;
    String name;
    String nickname;
    int age;
    String interest;
    LocalDateTime joinAt;

    public Member() {}

    public Member(String id, String pw, String email, boolean agree, String name, String nickname, int age) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.agree = agree;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }

    public Member(String id, String pw, String email, boolean agree, String name, String nickname, int age, String interest, LocalDateTime joinAt) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.agree = agree;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.interest = interest;
        this.joinAt = joinAt;
    }

    public Member(String id, String pw, String email, boolean agree, String name, String nickname, int age, String interest) {
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.agree = agree;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.interest = interest;
    }

}
