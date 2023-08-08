<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="libTitle" /></title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bookstoreStyle.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/libMainContents.css">
</head>
<body>
	<div class="wrapper" id="mainDiv">
		<tiles:insertAttribute name="libHeader" />
		<div id="used_div_box">
			<div id="used_nav_box">
				<tiles:insertAttribute name="libSearchNav" />
			</div>
			<div id="used_contents_box">
				<tiles:insertAttribute name="libSearch" />
			</div>
		</div>
		<tiles:insertAttribute name="libFooter" />
	</div>
</body>
</html>