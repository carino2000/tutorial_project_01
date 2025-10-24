package com.example.app.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    int articleNo;
    String writerId;
    String comments;
    LocalDateTime writingTime;

    public Comment() {}

    public Comment(int articleNo, String writerId, String comments) {
        this.articleNo = articleNo;
        this.writerId = writerId;
        this.comments = comments;
    }

}
