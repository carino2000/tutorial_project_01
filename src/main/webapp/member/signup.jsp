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
            padding: 0.5rem 0.7rem;
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
            background-color: #4f4f4f;
            color: #ffffff;
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
        <form class="signup-form" action="/signup" method="post">
            <div>
                <label for="id"><small>아이디</small></label>
                <div class="mt-1">
                    <input type="text" class="input" name="id" id="id" placeholder="4 ~ 15자 이내로 입력해주세요" required/>
                </div>
            </div>
            <div>
                <label for="id"><small>비밀번호</small></label>
                <div class="mt-1">
                    <input type="password" class="input" name="pw" id="pw" placeholder="최소 6자 이상" required/>
                </div>
            </div>
            <div>
                <label for="id"><small>이메일</small></label>
                <div class="mt-1">
                    <input type="text" class="input" name="email" id="email" placeholder="test@test.com" required/>
                </div>
            </div>
            <div>
                <label for="id"><small>실명</small></label>
                <div class="mt-1">
                    <input type="text" class="input" name="name" id="name" placeholder="홍길동" required/>
                </div>
            </div>
            <div>
                <label for="id"><small>별명</small></label>
                <div class="mt-1">
                    <input type="text" class="input" name="nickname" id="nickname"
                           placeholder="별명을 알파벳, 한글, 숫자를 20자 이내로 입력해주세요." required/>
                </div>
            </div>
            <div>
                <label for="id"><small>나이</small></label>
                <div class="mt-1">
                    <input type="number" class="input" name="age" id="age"
                           placeholder="나이를 입력해주세요." required/>
                </div>
            </div>
            <div>
                <label for="id"><small>관심태그</small></label>
                <div class="mt-1">
                    <input type="text" class="input" name="interest" id="interest"
                           placeholder="백엔드, 프론트엔드, 모바일, 취업..."/>
                </div>
            </div>

            <div style="display: flex; justify-content: space-between">
                <label>이메일 수신동의</label>
                <div>
                    <input type="checkbox" name="agree" value="true"/> 수신동의<br/>
                </div>
            </div>
            <div class="text-gray mt-1">
                [가제]에서 주최하는 다양한 이벤트, 정보성 뉴스레터 및 광고 수신여부를 설정할 수 있습니다
            </div>
            <div>
                <button class="bt-submit">회원가입</button>
            </div>
        </form>

    </div>

</div>
</body>
</html>
