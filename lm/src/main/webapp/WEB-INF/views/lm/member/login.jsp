<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<!-- 로그인 폼 시작 -->
<div class="page-main">
	<div class="page-outer">
		<div class="page-inner">
			<h2 id="title">로그인</h2>
			<form:form modelAttribute="memberVO" action="login.do" id="login" class="login-form">
			<form:errors element="div" cssClass="error-color"/>
				<div class="page-input">
					<div class="page-input-box">
						<form:input path="mem_id" placeholder="아이디" cssClass="form-input" autocomplete="off"/>
						<form:label path="mem_id">아이디</form:label>
						<form:errors path="mem_id" element="div" cssClass="error-color"/>
					</div>
					<div class="page-input-box">
						<form:password path="mem_passwd" placeholder="비밀번호" cssClass="form-input"/>
						<form:label path="mem_passwd">비밀번호</form:label>
						<form:errors path="mem_passwd" element="div" cssClass="error-color"/>
					</div>
				</div>

				<div>
				<label for="auto"><input type="checkbox" name="auto" id="auto">로그인상태유지</label>
				</div>
				<div class="page-button">
					<div class="page-button-box">
						<form:button class="button3">아이디 찾기</form:button>
					</div>
				</div>
				<div class="page-button2">
					<div class="page-button_box">
						<form:button class="button3">로그인</form:button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
<!-- 로그인 폼 끝 -->
</body>
</html>



