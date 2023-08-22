<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
</head>
<body>
<div class="page-one">
	<!-- 내용 시작 -->
	<h2>안내</h2>
	<div class="result-display">
		<div class="align-center">
			<c:if test="${!empty accessMsg}">
				${accessMsg}
			</c:if>
			<c:if test="${empty accessMsg}">
				관리자 권한이 있어야 접속 가능합니다.
			</c:if>
			<p>
			<c:if test="${!empty accessUrl}">
			<input type="button" value="이동"
			  onclick="location.href='${accessUrl}'">
			</c:if>
			<c:if test="${empty accessUrl}">
			<!-- 파라미터 세팅 -->
			<% pageContext.setAttribute("lo",request.getParameter("lo")); %>
			<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
			<c:if test="${lo == 1}">
				<input type="button" value="이동" onclick="location.href='${pageContext.request.contextPath}/bookstore/template/bsMain.do'">
			</c:if>
			<c:if test="${lo != 1}">
				<input type="button" value="이동" onclick="location.href='${pageContext.request.contextPath}/library/template/libMain.do'">
			</c:if>
			</c:if>
		</div>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>



