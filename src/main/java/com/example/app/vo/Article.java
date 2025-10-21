package com.example.app.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Article {

    int no;
    String writerId;
    String topic;
    String title;
    String content;
    LocalDateTime wroteAt;
    int viewCnt;
    int likeCnt;
    int commentCnt;


    public Article() {
    }

    public Article(String writerId, String topic, String title, String content) {
        this.writerId = writerId;
        this.topic = topic;
        this.title = title;
        this.content = content;
    }

    public Article(int no, String writerId, String topic, String title, String content, LocalDateTime wroteAt, int viewCnt, int likeCnt, int commentCnt) {
        this.no = no;
        this.writerId = writerId;
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.wroteAt = wroteAt;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
    }
}
