<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 21.
  Time: 오후 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>가제 - 게시글 등록</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@ include file="/template/header.jspf" %>

<div class="main">
    <div style="flex:1"><!-- 인기 유저 목록 -->
        인기 유저 목록
    </div>
    <div style="flex:4"><!-- 글쓰기 폼 -->
        <div>
            <h2>함께 할 때 더 즐거운 순간</h2>
            <div>
                <b>${member.nickname}님! 지식공유 플랫폼 OKKY에서 다양한 사람을 만나고 생각의 폭을 넓혀보세요.</b>
            </div>
        </div>
        <div>
            <form action="/community/new" method="post">
                <div style="margin-top: 5px">
                    <label>토픽</label>
                    <div>
                        <select name="topic" class="input-100">
                            <option value="">토픽을 선택해주세요.</option>
                            <option value="life">사는얘기</option>
                            <option value="gathering">모임&스터디</option>
                            <option value="feedback">피드백</option>
                            <option value="AI">AI</option>
                        </select>
                    </div>
                </div>
                <div style="margin-top: 30px">
                    <label>제목</label>
                    <div>
                        <label>
                            <input name="title" type="text" class="input-100"/>
                        </label>
                    </div>
                </div>
                <div style="margin-top: 10px">
                    <label>본문</label>
                    <div>
                        <label>
                            <textarea name="content" class="input-100" style="height: 300px; resize: none"></textarea>
                        </label>
                    </div>
                </div>
                <div style="display: flex; justify-content: space-between; margin-top: 50px">
                    <button type="reset">지우기</button>
                    <button type="submit">등록하기</button>
                </div>
            </form>
        </div>
    </div>
    <div style="flex:1"><!-- 인기 글 -->
        인기 글
    </div>
</div>

</body>
</html>
