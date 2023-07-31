<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="loginTitle"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookstoreStyle.css">
 <!-- lib css 추가 -->
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookstoreStyle.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lm/login.css">
</head>
<body>
    <!-- 로그인 페이지 헤더/푸터 식별 용 1=bs, 2=lib-->
	<% pageContext.setAttribute("lo",request.getParameter("lo")); %>

	<c:if test="${lo == 1}">
		<tiles:insertAttribute name="bsHeader" />
		<tiles:insertAttribute name="login"/>
		<tiles:insertAttribute name="bsFooter" />
	</c:if>		
	<c:if test="${lo != 1}">
		<tiles:insertAttribute name="libHeader" />
		<tiles:insertAttribute name="login"/>
		<tiles:insertAttribute name="libFooter" />
	</c:if>		
</body>
</html>