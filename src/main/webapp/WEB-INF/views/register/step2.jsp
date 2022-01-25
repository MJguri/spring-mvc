<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register" /></title>
</head>
<body>
<%-- <h2>회원 정보 입력</h2>
<form action="step3" method="post">
	<p>
		<label>이메일 : <br>
			<input type="text" name="email" id="email" value="${formData.email}">
		</label>
	</p>
	<p>
		<label>이름 : <br>
			<input type="text" name="name" id="name" value="${formData.name}">
		</label>
	</p>
	<p>
		<label>비밀번호 : <br>
			<input type="password" name="password" id="password">
		</label>
	</p>
	<p>
		<label>비밀번호 확인 : <br>
			<input type="password" name="confirmPassword" id="confirmPassword">
		</label>
	</p>
	<input type="submit" value="가입 완료">
</form> --%>

<h2><spring:message code="member.info" /></h2>
<form:form action="step3" commandName="formData">
	<p>
		<label><spring:message code="email" /> : <br>
			<form:input path="email"/>
			<%-- <input type="text" name="email" id="email" value="${formData.email}"> --%>
			<form:errors path="email"/>  <!--에러가 없다면 출력이 안됨 / 에러코드가 있다면 에러코드에 해당하는 라벨을 출력-->
		</label>
	</p>
	<p>
		<label><spring:message code="name" /> : <br>
			<form:input path="name"/>
			<form:errors path="name"/> <!-- required -->
		</label>
	</p>
	<p>
		<label><spring:message code="password" /> : <br>
			<form:password path="password"/>
			<form:errors path="password"/>
		</label>
	</p>
	<p>
		<label><spring:message code="password.confirm" /> : <br>
			<form:password path="confirmPassword"/>
			<form:errors path="confirmPassword"/>
		</label>
	</p>
	<input type="submit" value="<spring:message code="register.btn" />">
</form:form>
</body>
</html>

















