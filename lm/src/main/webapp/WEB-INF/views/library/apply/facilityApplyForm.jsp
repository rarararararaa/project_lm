<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<select name="start" id="start">
			<c:forEach var="i" begin="9" end="18">
			<option value="${i}">${i}:00</option>
			</c:forEach>
		</select>
		<select name="end" id="end" >
			<c:forEach var="i" begin="9" end="18">
			<option value="${i}">${i}:00</option>
			</c:forEach>
		</select>
		<input type="submit" value="신청">
		<input type="button" value="목록">
	</form>
</div>