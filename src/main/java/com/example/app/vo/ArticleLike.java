package com.example.app.vo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleLike {
    int idx;
    String memberId;
    int articleNo;

    public ArticleLike() {}

    public ArticleLike(String memberId, int articleNo) {
        this.memberId = memberId;
        this.articleNo = articleNo;
    }

    public ArticleLike(int articleNo, String memberId, int idx) {
        this.articleNo = articleNo;
        this.memberId = memberId;
        this.idx = idx;
    }


}
