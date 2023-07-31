<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="usedTitle"/></title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bookstoreStyle.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/used.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	
<tiles:insertAttribute name="bsHeader" />
	
	<div id="used_div_box">
		<div id="used_nav_box">
			<tiles:insertAttribute name="usedNav" />
		</div>
		<div id="used_contents_box">
			<tiles:insertAttribute name="usedBody" />
		</div>
	</div>
	
<tiles:insertAttribute name="bsFooter" />
	
</body>
</html>