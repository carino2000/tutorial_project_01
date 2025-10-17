package com.example.app.vo;
//Value Object

import java.time.LocalDateTime;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public LocalDateTime getJoinAt() {
        return joinAt;
    }

    public void setJoinAt(LocalDateTime joinAt) {
        this.joinAt = joinAt;
    }


}
