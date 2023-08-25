<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/service.css">
<div class="page-main">
	<p class="title">시설 예약</p>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 시설이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<div class="facility-main">
		<c:forEach var="facility" items="${list}">
		<div class="facility-item">
			<a href="facApplyWrite.do?facility_num=${facility.facility_num}">
				<img src="imageView.do?facility_num=${facility.facility_num}">
			</a>
			<div class="faci-info">
				<span>${facility.facility_name}</span><br>
				<ul>
					<li>${facility.facility_content }</li>
					<li>예약가능</li>
				</ul>
			</div>
		</div>
		</c:forEach>
			
		<div class="align-center" style="clear: both;">${page}</div>
	</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="글쓰기" onclick="location.href='insertFacility.do'" class="submit-btn">
	</c:if>
</div>