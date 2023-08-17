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
	<div>1개월동안 신청한 희망도서수 : ${month}</div>
	<div>1년동안 신청한 희망도서수 : ${year}</div>
	<table class="striped-table">
		<tr>
			<th>신청번호</th>
			<th>제목</th>
			<th>신청일</th>
			<th>신청현황</th>
		</tr>
		<c:forEach var="apply" items="${list}">
		<tr>
			<td>${apply.book_apply_num}</td>
			<td>${apply.book_apply_title}</td>
			<td>${apply.book_apply_reg_date}</td>
			<td>
				<c:if test="${apply.book_apply_status==0}">대기중</c:if>
				<c:if test="${apply.book_apply_status==1}">확인완료</c:if>
				<c:if test="${apply.book_apply_status==2}">등록완료</c:if>
				<c:if test="${apply.book_apply_status==3}">반려</c:if>
			</td>
		</tr>
		</c:forEach>	
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="추가 신청하기" onclick="location.href='bookApplyWrite.do'">
	</c:if>
</div>