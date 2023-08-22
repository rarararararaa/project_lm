<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 게시판 목록 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib.Calendar.js"></script>
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>게시판 목록</h2>
	<form action="lib_ScheduleAdminList.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>시작일</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>종료일</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword"
				       id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" 
				    onclick="location.href='lib_ScheduleAdminList.do'">
			</li>
		</ul>
		<div class="align-right">
			<input type="button" value="일정등록" id="add_schedule">
		</div>
	</form>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>시작일</th>
			<th>종료일</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td class="align-center">${board.calendar_num}</td>
			<td width="400">
				<a href='javascript:void(0);' onclick="clickSchedule('${board.calendar_num}')">${board.title}</a>
			</td>
			<td class="align-center">${board.start_date}</td>
			<td class="align-center">
				<c:if test="${!empty board.end_date}">${board.end_date}</c:if>
				<c:if test="${empty board.end_date}">X</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 게시판 목록 끝 -->