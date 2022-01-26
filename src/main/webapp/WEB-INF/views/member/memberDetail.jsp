<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보 표기</title>
</head>
<body>
<p>아이디 : ${list.id}</p>
<p>이메일 : ${list.email}</p>
<p>이   름 : ${list.name}</p>
<p>가입일 : <fmt:formatDate value="${list.registerDate}" pattern="yyyy-MM-dd HH:mm"/></p>
<hr>
<p>
	<a href='<c:url value="/member/list"/>'>[날짜별 회원 정보 보기]</a>
</p>
</body>
</html>