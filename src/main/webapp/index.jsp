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
        body{
            margin: 0px;
        }
        .header{
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

    <div>
        <a href="/login"><button>로그인</button></a>
        <a href="/signup"><button>회원가입</button></a>
    </div>
</div>

<div> <%--메인--%>
    메인 페이지
</div>

</body>
</html>
