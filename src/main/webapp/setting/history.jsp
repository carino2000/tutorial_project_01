
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 21.
  Time: 오전 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>가제</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<%@ include file="/template/header.jspf"%>
<div>
    <h3>로그인 이력</h3>

    <ul>
        <c:forEach items="${list}" var="one">
            <li>${one.loginIp} - ${one.loginAt}</li>
        </c:forEach>
    </ul>

</div>

</body>
</html>
