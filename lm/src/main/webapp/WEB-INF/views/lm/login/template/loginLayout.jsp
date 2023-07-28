<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="loginTitle"/></title>
<link rel="stylesheet" href="${ pageContext.request.contextPath}/css/bookstoreStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/lm/login.css">
</head>
<body>

<tiles:insertAttribute name="bsHeader" />
		<tiles:insertAttribute name="login"/>
<tiles:insertAttribute name="bsFooter" />

</body>
</html>