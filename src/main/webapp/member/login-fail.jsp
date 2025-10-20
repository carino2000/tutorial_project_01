<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2025-10-17
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>커뮤니티</title>
    <style>
        body {
            margin: 0px;
        }

        .signup-wrap {
            margin: 6rem 0rem;
            display: flex;
            justify-content: center;
        }

        .signup {
            max-width: 28rem;
        }

        .text-center {
            text-align: center;
        }

        .text-gray {
            color: #8f8f8f
        }

        .mt-1 {
            margin-top: 0.25rem;
        }

        .input {
            width: 100%;
            padding: 0.5rem 0.75rem;
            border-radius: 0.375rem;
            border-color: #8f8f8f;
        }

        .signup-form > div {
            margin-top: 1.5rem;
        }

        .bt-submit {
            width: 100%;
            padding: 0.5rem 1rem;
            border-radius: 0.375rem;
            border: none;
            cursor: pointer;
        }

        .bt-submit:hover {
            background-color: #6f6f6f;
            color: #efefef;
        }

        .error-main {
            background-color: rgba(239, 68, 68, 0.9);
            color: white;
            padding: 1rem;
        }

        .error-detail {
            color: rgba(239, 68, 68, 0.9)
        }
    </style>
</head>
<body>
<div class="signup-wrap">
    <div class="signup">
        <a href="/">로고</a>
        <h2 class="text-center">가제에 오신것을 환영합니다.</h2>
        <p class="text-center text-gray">
            가제는 소프트웨어 개발자를 위한 지식공유 플랫폼입니다.
        </p>
        <form class="signup-form" action="/login" method="post">

            <div>
                <label for="id"><small>아이디</small></label>
                <div class="mt-1">
                    <input type="text" class="input" name="id" id="id" value="${id}" required/>
                </div>
                <c:if test="${idError != null}">
                    <div class="error-detail mt-1">
                            ${idError}
                    </div>
                </c:if>
            </div>
            <div>
                <label for="password"><small>비밀번호</small></label>
                <div class="mt-1">
                    <input type="password" class="input" name="pw" id="password" value="${pw}" required/>
                </div>
                <c:if test="${passwordError != null}">
                    <div class="error-detail">
                            ${passwordError}
                    </div>
                </c:if>
            </div>
            <div>
                <button class="bt-submit">회원가입</button>
            </div>
        </form>
        <p class="text-center">
            아직 회원이 아니신가요? <a href="/signup">회원가입</a>
        </p>
        <c:if test="${mainError != null}">
            <div class="error-main">
                <b>로그인 실패</b><br/>
                    ${mainError}
            </div>
        </c:if>

    </div>
</div>
`
</body>
</html>
