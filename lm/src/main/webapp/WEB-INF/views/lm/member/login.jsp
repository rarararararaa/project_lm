<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lm/member/login.css">
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
						<form:input path="mem_id" placeholder="아이디를 입력하세요." cssClass="form-input" />
						<form:errors path="mem_id" element="div" cssClass="error-color"/>
					</div>
					<div class="page-input-box">
						<form:password path="mem_passwd" placeholder="비밀번호를 입력하세요." cssClass="form-input" autocomplete="off"/>
						<form:errors path="mem_passwd" element="div" cssClass="error-color"/>
					</div>
				</div>

				<div>
				
				</div>
				<div class="page-button">
					<label for="auto"><input type="checkbox" name="auto" id="auto">로그인상태유지</label>
					<form:button class="button1">아이디 찾기</form:button>
					<form:button class="button2">비밀번호 찾기</form:button>
				</div>
				<div class="page-button2">
					<form:button class="button3">로그인</form:button>
					<form:button class="button3">홈으로</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>
<!-- 로그인 폼 끝 -->
</body>
</html>



