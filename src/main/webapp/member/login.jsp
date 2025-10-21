<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 17.
  Time: 오후 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>커뮤니티</title>
    <link rel="stylesheet" href="/css/style.css"/>
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
                    <input type="text" class="input" name="id" id="id" onkeyup="updateButtonState()" required
                           autofocus/>
                </div>
            </div>
            <div>
                <label for="pw"><small>비밀번호</small></label>
                <div class="mt-1">
                    <input type="password" class="input" name="pw" id="pw" onkeyup="updateButtonState()" required/>
                </div>
            </div>

            <div>
                <button class="bt-submit" id="loginbt" disabled>로그인</button>
            </div>
        </form>
        <p class="text-center">
            아직 회원이 아니신가요? <a href="/signup">회원가입</a>
        </p>

    </div>

</div>


<script>
    function updateButtonState() {
        const idValue = document.getElementById("id").value;
        const pwValue = document.getElementById("pw").value;
        if (idValue && pwValue) {
            document.getElementById("loginbt").disabled = false;
        }

    }
</script>
</body>
</html>
