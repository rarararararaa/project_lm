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
	
	<c:forEach var="program" items="${list}">
	<ul class="facility-list">
		<li style="width:100px;">${program.program_num}</li>
		<li style="width:500px;">
		<a href="programDetail.do?program_num=${program.program_num}">${program.program_title}</a>
		</li>
		<li style="font-size:16px;">${program.program_start}~${program.program_end}</li>
	</ul>
	</c:forEach>
		
	<div class="align-center">${page}</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="글쓰기" onclick="location.href='insertProgram.do'" class="submit-btn">
	</c:if>
</div>