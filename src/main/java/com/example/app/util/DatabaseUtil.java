package com.example.app.util;

import com.example.app.vo.Article;
import com.example.app.vo.ArticleLike;
import com.example.app.vo.LoginUser;
import com.example.app.vo.Member;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    public static boolean isDuplicateId(String id) {
        if (selectMemberById(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDuplicateNickName(String nickname) {
        if (selectMemberByNickName(nickname) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotValidId(String id) {
        if (id.matches("[a-zA-Z0-9!@#$%^&*]{4,15}")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotValidPw(String pw) {

        if (pw.matches("(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,15}")) {
            return false;
        } else {
            return true;
        }

        /*
        if (pw == null || pw.length() < 6) {
            return true;
        }

        boolean hasAlpha = false;
        boolean hasDigit = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                hasAlpha = true;
            } else if (c >= '0' && c <= '9') {
                hasDigit = true;
            } else {
                return true;
            }
        }

        return !(hasAlpha && hasDigit);
         */
    }

    public static String setErrMsg(int result) {
        String mainError = null;
        switch (result) {
            case 500:
                System.out.println("loginId duplicated");
                mainError = "중복되는 아이디가 존재합니다.";
                break;
            case 501:
                System.out.println("nickname duplicated");
                mainError = "중복되는 별명이 존재합니다.";
                break;
            case 502:
                System.out.println("loginId is Not ValidId");
                mainError = "아이디를 조건에 맞게 입력해주세요.";
                break;
            case 503:
                System.out.println("pw is Not ValidId");
                mainError = "비밀번호를 조건에 맞게 입력해주세요.\n(영문, 숫자, 특수문자를 포함한 6~15자)";
                break;
            case 504:
                System.out.println("insertMember method is null");
                mainError = "Member 객체가 null입니다.";
                break;
            case 505:
                System.out.println("ID does not exist");
                mainError = "존재하지 않는 아이디입니다";
                break;
            case 506:
                System.out.println("PW does not match");
                mainError = "잘못된 비밀번호입니다.";
                break;
            case 507:
                System.out.println("Article insert error");
                mainError = "오류! Article 등록 실패";
                break;
            case 508:
                System.out.println("ArticleLike insert error");
                mainError = "오류! ArticleLike 정보 추가 실패";
                break;
        }
        return mainError;
    }


    //insert into memberinfo(loginId, pw, email, agree, name, nickname, age, interest) values(?,?,?,?,?,?,?,?);
    public static int insertMember(Member member) {
        int result = 0;
        if (member == null) {
            return 504;
        }
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);

            if (isDuplicateId(member.getId())) {
                result = 500; //중복 아이디
            } else if (isDuplicateNickName(member.getNickname())) {
                result = 501; //중복 닉네임
            } else if (isNotValidId(member.getId())) {
                result = 502; //아이디 조건 부적합
            } else if (isNotValidPw(member.getPw())) {
                result = 503; //비밀번호 조건 부적합
            } else {
                result = sqlSession.insert("mappers.MemberinfoMapper.insertOne", member);
                sqlSession.close();
            }
            return result;

        } catch (Exception e) {
            System.out.println("Error in inserting member : " + e);
            return result;
        }
    }

    //select * from memberinfo;
    public static List<Member> selectAllMemberInfo() {
        List<Member> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://database-2.clwoya2662ja.ap-northeast-2.rds.amazonaws.com:3306/community", "admin", "01023509231");
            String sql = "select * from memberinfo";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(); //읽기전용

            while (rs.next()) {

                String memberId = rs.getObject("id", String.class);
                String memberPw = rs.getObject("pw", String.class);
                String memberEmail = rs.getObject("email", String.class);
                boolean memberAgree = rs.getObject("agree", Boolean.class);
                String memberName = rs.getObject("name", String.class);
                String memberNickname = rs.getObject("nickname", String.class);
                int memberAge = rs.getObject("age", Integer.class);
                String memberInterest = rs.getObject("interest", String.class);
                LocalDateTime memberJoinAt = rs.getObject("joinAt", LocalDateTime.class);

                Member m = new Member(memberId, memberPw, memberEmail, memberAgree, memberName, memberNickname, memberAge, memberInterest, memberJoinAt);

                list.add(m);
            }
            rs.close();
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println("Error in select all member : " + e);
            return new ArrayList<Member>();
        }
    }

    //select * from memberinfo where loginId=?;
    public static Member selectMemberById(String id) {
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);
            Member member = sqlSession.selectOne("mappers.MemberinfoMapper.selectById", id);
            sqlSession.close();
            return member;
        } catch (Exception e) {
            System.out.println("Error in select member by loginId : " + e);
            return null;
        }
    }

    //select * from memberinfo where nickname=?;
    public static Member selectMemberByNickName(String nickname) {
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);
            Member member = sqlSession.selectOne("mappers.MemberinfoMapper.selectByNickname", nickname);
            sqlSession.close();
            return member;
        } catch (Exception e) {
            System.out.println("Error in select member by nickname : " + e);
            return null;
        }
    }

    //select * from memberinfo where loginId=? 사용해서 비밀번호 일치 여부 확인
    public static int login(String id, String pw) {
        Member member = selectMemberById(id);
        if (member == null) {
            return 505;
        } else if (!(member.getPw().equals(pw))) {
            return 506;
        } else {
            return 1;
        }
    }

    //insert into loginHistory(loginId,loginIp) values(?,?)사용해서 로그인 시간과 함께 insert ("constraint foreign key(loginId) references memberinfo(loginId)" - 연습)
    public static int insertLoginHistory(LoginUser user) {
        int result = 0;
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);

            result = sqlSession.insert("mappers.LoginHistoryMapper.insertOne", user);
            sqlSession.close();
            return result;

        } catch (Exception e) {
            System.out.println("Error in inserting login history : " + e);
            return result;
        }
    }

    //select * from loginHistory where loginId=?;
    public static List<LoginUser> selectHistoryById(String id) {
        List<LoginUser> list = null;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            list = session.selectList("mappers.LoginHistoryMapper.selectByLoginId", id);
            session.close();
            return list;

        } catch (IOException e) {
            System.out.println("Error in select login history : " + e);
            return list;

        }
    }

    //insert into article(writerId, topic, title, content) values(?,?,?,?);
    public static int insertArticle(Article article) {
        int result = 507;
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);

            result = sqlSession.insert("mappers.ArticleMapper.insertOne", article);
            sqlSession.close();
            return result;

        } catch (Exception e) {
            System.out.println("Error in insert Article : " + e);
            return result;
        }
    }


    public static List<Article> selectAllArticle() {
        List<Article> list = null;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            list = session.selectList("mappers.ArticleMapper.selectAll");
            session.close();
            return list;

        } catch (IOException e) {
            System.out.println("Error in select all Articles : " + e);
            return list;
        }
    }

    public static Article selectArticleByNo(String no) {
        Article article = null;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            article = session.selectOne("mappers.ArticleMapper.selectByNo", Integer.parseInt(no));
            session.close();
            return article;
        } catch (Exception e) {
            System.out.println("Error in select Article by no: " + e);
            return null;
        }

    }

    public static int updateArticle(Article article) {
        int result = -1;
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);

            result = sqlSession.update("mappers.ArticleMapper.updateArticle", article);
            sqlSession.close();
            return result;

        } catch (Exception e) {
            System.out.println("Error in update Article : " + e);
            return result;
        }
    }


    public static void increaseViewCnt(String no) {
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            session.update("mappers.ArticleMapper.increaseViewCnt", Integer.parseInt(no));
            session.close();
        } catch (Exception e) {
            System.out.println("Error in increaseViewCnt : " + e);
        }
    }


    public static void increaseLikeCnt(String no) {

        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            session.update("mappers.ArticleMapper.increaseLikeCnt", Integer.parseInt(no));
            session.close();
        } catch (Exception e) {
            System.out.println("Error in increaseLikeCnt : " + e);
        }
    }

    public static void decreaseLikeCnt(String no) {

        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            session.update("mappers.ArticleMapper.decreaseLikeCnt", Integer.parseInt(no));
            session.close();
        } catch (Exception e) {
            System.out.println("Error in increaseLikeCnt : " + e);
        }
    }

    public static int insertArticleLike(ArticleLike articleLike) {
        int result = 508;
        try {
            SqlSession sqlSession = MyBatisUtil.build().openSession(true);

            result = sqlSession.insert("mappers.ArticleLikeMapper.insertOne", articleLike);
            sqlSession.close();
            return result;

        } catch (Exception e) {
            System.out.println("Error in insert ArticleLike : " + e);
            return result;
        }
    }

    public static int countByArticleLike(ArticleLike articleLike) {
        int result = 0;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            result = session.selectOne("mappers.ArticleLikeMapper.countByArticleLike", articleLike);
            session.close();
            return result;
        } catch (Exception e) {
            System.out.println("Error in countByArticleLike: " + e);
            return -1;
        }
    }

//    public static ArticleLike selectByArticleLike(ArticleLike articleLike) {
//        ArticleLike al = null;
//        try {
//            SqlSession session = MyBatisUtil.build().openSession(true);
//            al = session.selectOne("mappers.ArticleLikeMapper.selectByArticleLike", articleLike);
//            session.close();
//            return articleLike;
//        } catch (Exception e) {
//            System.out.println("Error in selectByArticleLike: " + e);
//            return null;
//        }
//    }

    public static int deleteByArticleLike(ArticleLike articleLike) {
        int result = -1;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            result = session.delete("mappers.ArticleLikeMapper.deleteByArticleLike", articleLike);
            session.close();
        } catch (Exception e) {
            System.out.println("Error in selectByArticleLike: " + e);
        }
        return result;
    }

    public static int deleteArticleLikeByArticleNo(int no) {
        int result = -1;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            result = session.delete("mappers.ArticleLikeMapper.deleteByArticleNo", no);
            session.close();
        } catch (Exception e) {
            System.out.println("Error in deleteArticleLikeByArticleNo: " + e);
        }
        return result;
    }


    public static int deleteArticleByNo(int no) {
        int result = -1;
        try {
            SqlSession session = MyBatisUtil.build().openSession(true);
            result = session.delete("mappers.ArticleMapper.deleteByArticleByNo", no);
            session.close();
        } catch (Exception e) {
            System.out.println("Error in deleteByArticleByNo: " + e);
        }
        return result;
    }







}
