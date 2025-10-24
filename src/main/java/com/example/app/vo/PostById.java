package com.example.app.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostById {

    String writerId;
    int cnt;

    public PostById() {
    }

    public PostById(String writerId, int cnt) {
        this.writerId = writerId;
        this.cnt = cnt;
    }
}
