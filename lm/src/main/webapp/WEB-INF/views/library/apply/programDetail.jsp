<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-main">
	<h2>${program.program_title}</h2>
	<div class="content-main">
		작성일:${program.program_reg_date} 조회수:${program.program_hit}
		<br>
		${program.program_content}
	</div>
	<form:form modelAttribute="programTimesVO" action="programDetail.do">
	<select name="program_times_num">
	<c:forEach var="time" items="${times}">
		<option value="${time.program_times_num}">${time.program_start} ${time.start}:00 ~ ${time.end}:00 신청 가능 인원 : ${time.program_admit}</option>
	</c:forEach>
	</select>
	<form:button id="submit">신청</form:button>
	</form:form>
</div>