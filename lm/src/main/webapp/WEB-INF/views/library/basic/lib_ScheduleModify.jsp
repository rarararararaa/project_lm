<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/libCalendar_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/datepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/timepicki.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib.Calendar.Schedule.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/timepicki.js"></script>
</head>
<body>
	<div class="schedule-popup">
		<h2>일정 수정 및 삭제</h2>
		<hr size="1" noshade="noshade">
		<form:form modelAttribute="lib_CalendarVO"
			action="lib_SchedulePopup.do" id="modify_form" class="add-schedule">
			<form:hidden path="calendar_num" />
			<div class="event_form_div2">
				<div class="cal-div1">
					<form:label path="title">제목</form:label>
					<form:input path="title" class="box1-right text-st" />
					<form:errors path="title" cssClass="error-color" />
				</div>
				<div class="cal-div2">
					<div class="div-date">
						<form:label path="start_date">시작일</form:label>
						<form:input type="text" path="start_date" class="text-st" autocomplete="off"/>
					</div>
					<div class="div-time start-time">
						<form:label path="start_time">시작시간</form:label>
						<form:input type="text" path="start_time" class="text-st" autocomplete="off"/>
					</div>
				</div>
				<div class="cal-div4">
					<form:checkbox path="allday" value="1" label="하루종일" />
				</div>
				<div class="cal-div3">
					<div class="div-date">
						<form:label path="end_date">종료일</form:label>
						<form:input type="text" path="end_date" class="text-st" autocomplete="off"/>
					</div>
					<div class="div-time end-time" >
						<form:label path="end_time">종료시간</form:label>
						<form:input type="text" path="end_time" class="text-st" autocomplete="off"/>
					</div>
				</div>
			</div>
			<hr size="1" noshade>

			<div class="event_form_div3 align-center">
				<form:button>수정</form:button>
				<input type="button" id="delete_btn"
					data-num="${lib_CalendarVO.calendar_num}" value="삭제"> 
			</div>
		</form:form>
	</div>
</body>
</html>