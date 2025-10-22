<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 21.
  Time: 오후 3:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가제 - 커뮤니티</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@include file="/template/header.jspf" %>
>

<div class="main">
    <div style="flex: 1">
        -- 인기 유저
    </div>
    <div style="flex: 4"> <!-- 중앙 -->
        <div><!-- 이미지 베너 -->
            이미지 베너
        </div>
        <div><!-- 네비게이터 (카테고리별 영역) -->
            <button onclick="location.href='/community/new'">작성하기</button>
        </div>

        <div>
            <c:forEach items="${articles}" var="one">
                <div class="article-item">
                    <div style="display: flex; justify-content: space-between">
                        <div>
                            <span class="article-topic text-gray">${one.topic}</span>
                            <span>${one.writerId}</span>
                            <span>&middot; ${one.prettyWroteAt}</span>
                        </div>
                        <div>
                            <span>조회:${one.viewCnt}</span>
                            <span>추천:${one.likeCnt}</span>
                        </div>
                    </div>
                    <div>
                           <span style="font-size: 1.1rem; font-weight: 500">${one.title} <small>(${one.commentCnt})</small></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div style="flex: 1">
        -- 인기글
    </div>


</div>
</body>
</html>
