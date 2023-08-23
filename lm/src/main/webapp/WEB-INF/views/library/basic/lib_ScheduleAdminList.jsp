<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 일정등록 목록 시작 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib.Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		//검색 유효성 체크
		$('#search_form').submit(function() {
			if ($('#keyword').val().trim() == '') {
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<div class="box">
		<div class="title">전체 도서관 일정</div>
		<div class="content-box">
			<form action="lib_ScheduleAdminList.do" id="search_form" method="get">
				<div class="button-box">
					<ul class="main-content">
						<li><select name="keyfield" id="keyfield">
								<option value="1"
									<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
								<option value="2"
									<c:if test="${param.keyfield == 2}">selected</c:if>>시작일</option>
								<option value="3"
									<c:if test="${param.keyfield == 3}">selected</c:if>>종료일</option>
						</select></li>
						<li><input type="search" name="keyword" id="keyword"
							value="${param.keyword}"></li>
						<li><input class="small-button" type="submit" value="조회">
							<input class="small-button" type="button" value="목록"
							onclick="location.href='lib_ScheduleAdminList.do'"> <input
							class="small-button-B" type="button" value="일정등록" id="add_schedule">
						</li>
					</ul>
				</div>
			</form>
			<c:if test="${count == 0}">
				<div class="row-content">표시할 게시물이 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>No</b></th>
						<th class="row-title"><b>제목</b></th>
						<th class="row-title"><b>시작일</b></th>
						<th class="row-title"><b>종료일</b></th>
					</tr>
					<c:forEach var="board" items="${list}">
						<tr class="align-center">
							<td class="row-title">${board.calendar_num}</td>
							<td width="400"><a href='javascript:void(0);'
								onclick="clickSchedule('${board.calendar_num}')">${board.title}</a>
							</td>
							<td class="row-title">${board.start_date}</td>
							<td class="row-title"><c:if test="${!empty board.end_date}">${board.end_date}</c:if>
								<c:if test="${empty board.end_date}">X</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					<b>${page}</b>
				</div>
			</c:if>
		</div>
	</div>
</div>
<!-- 일정등록 목록 끝 -->