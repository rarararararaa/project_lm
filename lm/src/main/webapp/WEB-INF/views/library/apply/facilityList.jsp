<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/service.css">
<div class="page-main">
	<h2>시설 목록</h2>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 시설이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	
	<c:forEach var="facility" items="${list}">
	<ul class="facility-list">
		<li><img src="imageView.do?facility_num=${facility.facility_num}" width="200" height="200"></li>
		<li class="fac-name"><a href="facApplyWrite.do?facility_num=${facility.facility_num}">${facility.facility_name}</a></li>
		<li>예약가능</li>
	</ul>
	</c:forEach>
		
	<div class="align-center">${page}</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="글쓰기" onclick="location.href='insertFacility.do'" class="submit-btn">
	</c:if>
</div>