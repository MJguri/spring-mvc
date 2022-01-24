<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 조회</title>
</head>
<body>
<h2>가입일 기준으로 회원 정보 조회</h2>
<form:form commandName="cmd">
	<p>
		<label>from : <form:input path="from" /></label>
		<form:errors path="from" />
		~
		<label>to : <form:input path="to" /></label>
		<form:errors path="to" />
		<input type="submit" value="조회">
	</p>
</form:form>

<c:if test="${!empty members}">  <!--  게시판 -->
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이메일</th>
			<th>이름</th>
			<th>가입일</th>
		</tr>
		<c:forEach var="m" items="${members}">
			<tr>
				<td>${m.id}</td>
				<td> <a href='<c:url value="/member/detail/${m.id}"/>'>${m.email}</a></td>
				<td>${m.name}</td>
				<td> <fmt:formatDate value="${m.registerDate}" pattern="yyyy-MM-dd"/>  </td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>