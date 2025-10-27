<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 27.
  Time: 오후 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>my article like</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@include file="/template/header.jspf" %>
<div class="main">
    <div style="flex: 1">
    </div>
    <div style="flex: 4"> <!-- 중앙 -->
        <div><!-- 이미지 베너 -->
            이미지 베너
        </div>

        <h2>
            ${member.id}님의 좋아요 기록
        </h2>
        <div>
            <c:forEach items="${likedArticle}" var="one">
                <div class="article-item">
                    <div style="display: flex; justify-content: space-between">
                        <div>
                            <span class="article-topic text-gray">${one.prettyTopic}</span>
                            <span>${one.writerId}</span>
                            <span>&middot; ${one.prettyWroteAt}</span>
                        </div>
                        <div>
                            <span>👁:${one.viewCnt}</span>
                            <span>🤍:${one.likeCnt}</span>
                        </div>
                    </div>
                    <div>
                        <a href="/article?no=${one.no}" class="article-link">
                            <span style="font-size: 1.1rem; font-weight: 500"><c:out value="${one.title}"/> </span>
                        </a>
                        <small>💬(${one.commentCnt})</small>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div style="padding: 1.5rem 0; margin-top: 2rem; text-align: center">
            <c:forEach var="i" begin="1" end="${maxPage}">
                <button
                        onclick="location.href='/my-loginHistory?page=${i}'"
                        type="button"
                        name="page"
                        value="${i}"
                        class=${i == currentPage ? 'active-page-link' : ''}>${i}</button>
            </c:forEach>
        </div>
    </div>
    <div style="flex: 1">

    </div>


</div>

</body>
</html>
