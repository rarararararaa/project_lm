<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<!-- 로그인 폼 시작 -->
<div class="page-main">
	<div class="page-outer">
		<div class="page-inner">
			<h2 id="title">로그인</h2>
			<form:form modelAttribute="memberVO" action="loginMain.do" id="login" class="login-form">
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
					<input type="button" class="button1" value="아이디 찾기" onclick="location.href='${pageContext.request.contextPath}/lm/findId/template/findIdForm.do'">
					<input type="button" class="button2" value="비밀번호 찾기" onclick="location.href='${pageContext.request.contextPath}/lm/findPw/template/findPwForm.do'">
				</div>
				<div class="page-button2">
					<form:button class="button3">로그인</form:button>
					<input type="button" class="button3" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/lm/login/template/registerUserForm.do'">
					<input type="button" class="button3" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/bookstore/template/bsMain.do'">
				</div>
			</form:form>
		</div>
	</div>
</div>
<!-- 로그인 폼 끝 -->
</body>
</html>



