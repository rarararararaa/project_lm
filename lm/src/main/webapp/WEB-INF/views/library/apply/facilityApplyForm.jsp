<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkedTime.js"></script>
<div class="page-main">
	<form id="date" action="facApplyWrite.do" method="post">
		날짜
		<input type="hidden" id="facility_num" name="facility_num" value="${facility_num}">
		<select name="year" id="year">
			<c:forEach var="i" begin="2023" end="2024">
			<option value="${i}">${i}년</option>
			</c:forEach>
		</select>
		<select name="month" id="month">
			<c:forEach var="i" begin="1" end="12">
			<option value="${i}">${i}월</option>
			</c:forEach>
		</select>
		<select name="date" id="date">
			<c:forEach var="i" begin="1" end="31">
			<option value="${i}">${i}일</option>
			</c:forEach>
		</select>
		시간
		<br>
		<c:forEach var="i" begin="9" end="18">
		<label for="${i}">${i}:00</label>
		<input type="checkbox" value="${i}" name="time" id="${i}">
		<br>
		</c:forEach>
		<input type="text" value="0" name="start" id="start" readonly maxlength="5" size="5">시~
		<input type="text" value="0" name="end" id="end" readonly maxlength="5" size="5">시
		<input type="submit" value="신청">
		<input type="button" value="목록">
	</form>
</div>