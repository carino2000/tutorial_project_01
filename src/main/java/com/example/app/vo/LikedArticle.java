package com.example.app.vo;

import lombok.Getter;
import lombok.Setter;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
public class LikedArticle {

    int idx;
    String memberId;
    int articleNo;
    int no;
    String writerId;
    String topic;
    String title;
    String content;
    LocalDateTime wroteAt;
    int viewCnt;
    int likeCnt;
    int commentCnt;

    public String getPrettyTopic(){
        if(this.topic.equals("life")){
            return "사는 얘기";
        }else if(this.topic.equals("gathering")){
            return "모임/스터디";
        }else if(this.topic.equals("feedback")){
            return "피드백";
        }else{
            return this.topic;
        }
    }

    public String getPrettyWroteAt(){
        PrettyTime p = new PrettyTime(Locale.KOREA);
        return p.format(this.wroteAt);
    }

}
