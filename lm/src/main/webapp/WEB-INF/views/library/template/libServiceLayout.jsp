<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="libTitle"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bookstoreStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/libService.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div id="Main" class="wrapper">
	<div id="main_header">
		<tiles:insertAttribute name="libHeader"/>
	</div>
	<div id="lib_service_box" class="side-height">
		<div id="page_nav">
			<tiles:insertAttribute name="libServiceNav"/>
		</div>
		<div id="page_body">
			<tiles:insertAttribute name="libBody"/>
		</div>
	</div>
	<div id="main_footer" class="page_clear">
		<tiles:insertAttribute name="libFooter"/>
	</div>
</div>

</body>
</html>