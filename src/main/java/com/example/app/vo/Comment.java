package com.example.app.vo;

import lombok.Getter;
import lombok.Setter;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.util.Locale;

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

    public String getPrettyWritingTime(){
        PrettyTime p = new PrettyTime(Locale.KOREA);
        return p.format(this.writingTime);
    }

}
