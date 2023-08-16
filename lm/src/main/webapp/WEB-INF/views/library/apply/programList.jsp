<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/service.css">
<div class="page-main">
	<h2>프로그램 목록</h2>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 시설이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	
	<ul class="facility-list">
		<li>NO.</li>
		<li>프로그램명</li>
		<li>등록일</li>
		<li>진행상황</li>
	</ul>
	<c:forEach var="program" items="${list}">
	<ul class="facility-list">
		<li>${program.program_num}</li>
		<li>${program.program_title}</li>
		<li>${program.program_reg_date}</li>
		<c:if test="${program.status==0}"><li>진행예정</li></c:if>
		<c:if test="${program.status==1}"><li>종료</li></c:if>
		<c:if test="${program.status==2}"><li>진행중</li></c:if>
	</ul>
	</c:forEach>
		
	<div class="align-center">${page}</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="글쓰기" onclick="location.href='insertProgram.do'">
	</c:if>
</div>