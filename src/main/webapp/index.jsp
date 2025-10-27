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
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<c:choose>
    <c:when test="${msg == 1}">
        <script>
            window.alert("비밀번호 변경 완료!\n다시 로그인해주세요.");
        </script>
    </c:when>
    <c:when test="${msg == 2}">
        <script>
            window.alert("회원 탈퇴 완료\n지금까지 함께해주셔서 감사합니다");
        </script>
    </c:when>
    <c:when test="${msg == 3}">
        <script>
            window.alert("회원 정보 수정 완료\n다시 로그인해주세요.");
        </script>
    </c:when>
</c:choose>
<%@include file="/template/header.jspf"%>


<div> <%--메인--%>
    메인 페이지
</div>

</body>
</html>
