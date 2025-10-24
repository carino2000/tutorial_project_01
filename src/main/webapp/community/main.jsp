<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 21.
  Time: 오후 3:50
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta charset="UTF-8">

    <title>가제 - 커뮤니티</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"/>
</head>
<body>
<%@include file="/template/header.jspf" %>


<div class="main">
    <div style="flex: 1">
        <h3>에디터 초이스</h3>
        <ul>
            <c:forEach var="one" items="${topLikes}">
                <li style="margin-top: 1rem">
                    <a href="/article?no=${one.no}">${one.title}</a>
                </li>

            </c:forEach>
        </ul>
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
            <div style="padding: 1.5rem 0; margin-top: 2rem; text-align: center">
                <form action="/community">
                    <input type="hidden" name="keyword" value="${keyword}">
                    <c:forEach var="i" begin="1" end="${maxPage}">
                        <button
                                type="submit"
                                name="page"
                                value="${i}"
                                class=${i == currentPage ? 'active-page-link' : ''}>${i}</button>
                    </c:forEach>
                </form>
            </div>
            <div>
                <form action="/community">
                    <input type="hidden" value="${keyword}"/>
                    <input type="text" name="keyword" class="input" style="width: 200px" placeholder="커뮤니티 내에서 검색">
                </form>
            </div>
        </div>
    </div>
    <div style="flex: 1">
        <h3>Top Writers</h3>
        <ul>
            <c:forEach var="one" items="${topPost}" varStatus="s">
                <li style="margin-top: 1rem">
                        ${s.count}등 : <a href="">${one.writerId}</a> - <i class="fa-solid fa-medal"></i>${one.cnt}
<%--                    <c:choose>--%>
<%--                        <c:when test="${s.count == 1}"><i class="fa-solid fa-medal"></i></c:when>--%>
<%--                        <c:when test="${s.count == 2}"><i class="fa-solid fa-medal"></i></c:when>--%>
<%--                        <c:when test="${s.count == 3}"><i class="fa-solid fa-medal"></i></c:when>--%>
<%--                    </c:choose>--%>
                </li>
            </c:forEach>
        </ul>
    </div>


</div>
</body>
</html>
