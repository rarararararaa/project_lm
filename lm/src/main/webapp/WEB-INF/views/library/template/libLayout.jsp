<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="libTitle" /></title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bookstoreStyle.css">
</head>
<body>
	<div class="wrapper" id="mainDiv">
		<tiles:insertAttribute name="libHeader" />
		<tiles:insertAttribute name="libBody" />
		<tiles:insertAttribute name="libFooter" />
	</div>
</body>
</html>