<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkedTime.js"></script>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/service.css">
<div class="page-main">
	<form id="date" action="facApplyWrite.do" method="post">
		날짜
		<input type="hidden" id="facility_num" name="facility_num" value="${facility_num}">
		<select name="year" id="yearSelect">
			<c:forEach var="i" begin="2023" end="2033">
			<option value="${i}">${i}년</option>
			</c:forEach>
		</select>
		<select name="month" id="monthSelect">
			<c:forEach var="i" begin="1" end="12">
			<option value="${i}">${i}월</option>
			</c:forEach>
		</select>
		<select name="date" id="dateSelect">
			<c:forEach var="i" begin="1" end="31">
			<option value="${i}">${i}일</option>
			</c:forEach>
		</select>
		<br>
		<c:forEach var="i" begin="9" end="17">
		<input type="checkbox" value="${i}" name="time" id="${i}" class="hidecheck">
		<label for="${i}" class="viewcheck">${i}:00</label>
		<br>
		</c:forEach>
		<input type="text" value="0" name="start" id="start" readonly maxlength="5" size="5">시~
		<input type="text" value="0" name="end" id="end" readonly maxlength="5" size="5">시
		<input type="submit" value="신청">
		<input type="button" value="목록">
	</form>
</div>