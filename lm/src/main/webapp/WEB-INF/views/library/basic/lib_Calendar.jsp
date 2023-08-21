<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
  <!-- fullcalendar CDN -->
  <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
  <!-- fullcalendar 언어 CDN -->
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib.Calendar.js"></script>
</head>
<style>
#add_schedule{
	margin-top: 20px; 
	margin-right: 20px; 
	background-color: #A9A9A9;
	color: #fff;
	border: none;
	border-radius: 5px;
	width: 200px;
	height: 35px;
	font-weight: bold;
	font-size: 16px;
}
</style>

<body>
	<div id="calendar" style="width: 950px; margin-top: 40px;"></div>
	<div class="align-right">
		<c:if test="${mem_auth == 9}">
			<input type="button" id="add_schedule" value="일정 추가">
		</c:if>
	</div>
</body>
</html>