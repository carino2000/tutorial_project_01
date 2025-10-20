<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 17.
  Time: 오전 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가제</title>
    <style>
        body {
            margin: 0px;
        }

        .header {
            height: 64px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #fefefe;
            border-bottom: 1px solid #cdcdcd;
        }
    </style>
</head>
<body>
<div class="header"> <%--헤더--%>
    <div>
        로고
    </div>

    <div>
        중앙
    </div>

    <c:choose>
        <c:when test="${auth}">
            <div>
                <button>스크랩</button>
                <button>알림</button>
                <button>설정</button>
                <br>
                <button onclick="location.href='/logout'">로그아웃</button>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <button onclick="location.href='/login'">로그인</button>
                <button onclick="location.href='/signup'">회원가입</button>
            </div>
        </c:otherwise>
    </c:choose>


</div>

<div> <%--메인--%>
    메인 페이지
</div>

</body>
</html>
