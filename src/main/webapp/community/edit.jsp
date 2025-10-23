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
    <title>가제 - 게시글 수정</title>
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
            <h2>게시글 수정하기</h2>
        </div>
        <div>
            <form action="/article/edit" method="post">
                <input type="hidden" name="id" value="${article.writerId}">
                <input type="hidden" name="no" value="${article.no}">
                <div style="margin-top: 5px">
                    <label>토픽</label>
                    <div>
                        <select name="topic" class="input-100">
                            <option value="life" ${article.topic == 'life' ? 'selected' : ''}>사는얘기</option>
                            <option value="gathering" ${article.topic == 'gathering' ? 'selected' : ''}>모임&스터디</option>
                            <option value="feedback" ${article.topic == 'feedback' ? 'selected' : ''}>피드백</option>
                            <option value="AI" ${article.topic == 'AI' ? 'selected' : ''}>AI</option>
                        </select>
                    </div>
                </div>
                <div style="margin-top: 30px">
                    <label>제목</label>
                    <div>
                        <label>
                            <input name="title" type="text" class="input-100" value="${article.title}"/>
                        </label>
                    </div>
                </div>
                <div style="margin-top: 10px">
                    <label>본문</label>
                    <div>
                        <label>
                            <textarea name="content" class="input-100"
                                      style="height: 300px; resize: none">${article.content}</textarea>
                        </label>
                    </div>
                </div>
                <div style="display: flex; justify-content: space-between; margin-top: 50px">
                    <button type="reset">지우기</button>
                    <button type="submit">수정하기</button>
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
