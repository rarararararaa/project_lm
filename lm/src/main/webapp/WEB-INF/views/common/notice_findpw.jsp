<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layout.css">
</head>
<body>
	<div class="page-one">
		<!-- 내용 시작 -->
		<h2>안내</h2>
		<div class="result-display">
			<div class="align-center">
				<c:if test="${!empty answer}">
				${answer}
			</c:if>
				<c:if test="${empty answer}">
				잘못된 접속입니다.
			</c:if>
				<p>
					<c:if test="${!empty accessUrl}">
						<input type="button" value="이동"
							onclick="location.href='${accessUrl}'">
					</c:if>
					<c:if test="${empty accessUrl}">
						<c:if test="${lo == 1}">
							<input type="button" value="이동"
								onclick="location.href='${pageContext.request.contextPath}/lm/mypage/myedit/myEditMain.do?lo=1'">
						</c:if>
						<c:if test="${lo != 1}">
							<input type="button" value="이동"
								onclick="location.href='${pageContext.request.contextPath}/lm/mypage/myedit/myEditMain.do?lo=2'">
						</c:if>
					</c:if>
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
</body>
</html>



