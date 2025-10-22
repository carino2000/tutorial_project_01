<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 25. 10. 22.
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${article.title}"/> - 가제</title>
    <link rel="stylesheet" href="/css/style.css"/>

</head>
<body>
<%@include file="/template/header.jspf" %>
<div class="main">
    <div style="flex:1">
        -- 인기 유저
    </div>
    <div style="flex:4"><!-- 중앙 -->
        <div><!-- 게시글 영역 -->
            <div><!-- 토픽, 작성자, 글 정보 -->
                <div>
                    커뮤니티 / ${article.prettyTopic}
                </div>
                <div style="display: flex; justify-content: space-between">
                    <div><!-- 작성자 정보 -->
                        <span>${article.writerId}</span>
                        <span>${article.prettyWroteAt}</span> &middot;
                        <span>👁${article.viewCnt}</span>
                    </div>
                    <div><!-- 스크랩, 즐찾 등 이미지 -->
                        <button>📌</button>
                    </div>
                </div>
            </div>
            <div><!-- 타이틀 -->
                <h2><c:out value="${article.title}"/></h2>
            </div>
            <div style="white-space: pre-line"><!-- 본문 -->
                <c:out value="${article.content}"/>
            </div>
            <div>
                <button>👍</button>
            </div>
        </div>
        <div><!-- 댓글 영역 -->
            <span style=""></span>
        </div>

    </div>
    <div style="flex:1"><!-- 인기글 -->

    </div>


</div>

</body>
</html>
