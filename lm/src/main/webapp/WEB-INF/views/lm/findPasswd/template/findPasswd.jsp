<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/findPasswdControl.js"></script>
</head>
<body>
	<div class="page">
		<div class="page-outer">
			<!-- 전체 페이지 크기 지정 -->
			<div class="page-inner">
				<h2 id="title">비밀번호 찾기</h2>
				<form:form modelAttribute="memberVO" action="findPasswdMain.do"
					id="findPasswd-form" class="findPasswd-form">
					<!-- 파라미터 세팅 -->
					<%
					pageContext.setAttribute("lo", request.getParameter("lo"));
					%>
					<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
					<input type="hidden" name="lo" value="${lo}" />
					<div class="page-input" id="page-input-passwd">
						<div class="page-input-box">
							<form:input path="mem_name" maxlength="30"
								placeholder="이름을 입력하세요." cssClass="form-input" />
							<span id="message_name"></span>
						</div>
						<div class="page-input-box">
							<form:input path="mem_id" maxlength="15"
								placeholder="아이디를 입력하세요." cssClass="form-input" />
							<span id="message_id"></span>
						</div>
						<div class="page-input-box">
							<form:input path="mem_email" maxlength="50"
								placeholder="이메일을 입력하세요." cssClass="form-input" />
							<span id="message_email"></span>
						</div>
					</div>
					<div class="page-button2">
						<input type="button" class="button3" value="확인" onclick="emailCheck()"/>
						<c:if test="${lo == 1}">
							<input type="button" class="button3" value="취소"
								onclick="location.href='${pageContext.request.contextPath}/lm/login/template/loginMain.do'">
						</c:if>
						<c:if test="${lo != 1}">
							<input type="button" class="button3" value="취소"
								onclick="location.href='${pageContext.request.contextPath}/lm/login/template/loginMain.do'">
						</c:if>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>



