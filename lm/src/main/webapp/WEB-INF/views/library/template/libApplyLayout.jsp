<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bookstoreStyle.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/libMainContents.css">
</head>
<body>
	<div class="wrapper" id="mainDiv">
		<tiles:insertAttribute name="header" />
		<div id="used_div_box">
			<div id="used_nav_box">
				<tiles:insertAttribute name="nav" />
			</div>
			<div id="page_body">
			<tiles:insertAttribute name="body"/>
		</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>