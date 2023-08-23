<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="bsTitle"/></title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bookstoreStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/up_button.js"></script>
</head>
<body>
<div class="wrapper" id="mainDiv">
	<tiles:insertAttribute name="bsHeader"/>
	<tiles:insertAttribute name="bsBody"/>
	<tiles:insertAttribute name="bsFooter"/>
</div>
</body>
</html>