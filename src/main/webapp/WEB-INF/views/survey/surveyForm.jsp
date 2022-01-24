<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사</title>
</head>
<body>
<h2>설문조사</h2>
<form action="survey" method="post">
    <!-- <p>
        1. 당신의 역할은?<br>
        <label><input type="radio" name="responses[0]" value="백엔드">백엔드 개발자</label>
        <label><input type="radio" name="responses[0]" value="프론트">프론트 개발자</label>
        <label><input type="radio" name="responses[0]" value="풀스택">풀스택 개발자</label>
    </p>
    <p>
        2. 가장 많이 사용하는 개발 도구? <br>
        <label><input type="radio" name="responses[1]" value="NetBeans">NetBeans</label>
        <label><input type="radio" name="responses[1]" value="Eclipse">Eclipse</label>
        <label><input type="radio" name="responses[1]" value="IntelliJ">IntelliJ</label>
    </p>
    <p>
        3. 하고 싶은 말<br>
         <input type="text" name="responses[2]">
    </p> -->
    <!-- 컨트롤러로 부터 전달받은 질문지를 출력-->
    <c:forEach var="q" items="${questions}" varStatus="s"> <!-- 문제 출력을 위한 반복문 -->
    	<p>
    		${s.index+1}. ${q.title}<br>
    		<c:if test="${q.choice}">	<!--  선택사항이 있는 경우 -->
    			<c:forEach var="op" items="${q.option}">
    				<label>
    					<input type="radio" name="responses[${s.index}]" value="${op}">${op}
    				</label>
    			</c:forEach>
    		</c:if>
    		<c:if test="${!q.choice}"> <!--  선택사항이 없는 경우 -->
    			<input type="text" name="responses[${s.index}]">
    		</c:if>
    	</p>
    </c:forEach>


    <p>
        <label>응답자의 위치 : 
            <input type="text" name="res.location">
        </label>
    </p>
    <p>
        <label>응답자의 나이 : 
            <input type="text" name="res.age">
        </label>
    </p>
    <input type="submit" value="전송">
</form>

</body>
</html>