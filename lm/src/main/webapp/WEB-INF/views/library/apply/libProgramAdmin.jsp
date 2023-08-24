<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 관리자페이지 프로그램 목록 시작 -->
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
		<div class="title">전체 도서관 프로그램</div>
		<div class="content-box">
			<form action="list.do" id="search_form" method="get">
				<div class="button-box">
					<ul class="main-content">
						<li><select name="keyfield" id="keyfield">
								<option value="1"
									<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
								<option value="2"
									<c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
								<option value="3"
									<c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
						</select></li>
						<li><input type="search" name="keyword" id="keyword"
							value="${param.keyword}"></li>
						<li><input class="small-button" type="submit" value="조회">
							<input class="small-button" type="button" value="목록"
							onclick="location.href='programAdminList.do'"> <input
							class="small-button-B" type="button" value="신규프로그램등록"
							onclick="location.href='programAdminWrite.do'"></li>
					</ul>
				</div>
			</form>
			<c:if test="${count == 0}">
				<div class="row-content">진행중인 프로그램이 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>No</b></th>
						<th class="row-title"><b>프로그램명</b></th>
						<th class="row-title"><b>등록일</b></th>
						<th class="row-title"><b>진행상황</b></th>
						<th class="row-title"><b>프로그램수정</b></th>
					</tr>
					<c:forEach var="lib_program" items="${list}">
						<tr class="align-center">
							<td class="row-title">${lib_program.program_num}</td>
							<td class="row-title"><a
								href="${pageContext.request.contextPath }/library/apply/admin_programDetail.do?program_num=${lib_program.program_num}">${lib_program.program_title}</a>
							</td>
							<td class="row-title">${lib_program.program_reg_date}</td>
							<td class="row-title"><c:if test="${lib_program.status==0}">진행예정</c:if>
								<c:if test="${lib_program.status==1}">
									<span style="color: red;">종료</span>
								</c:if> <c:if test="${lib_program.status==2}">진행중</c:if></td>
							<td class="row-title" style="text-align: center;"><c:if
									test="${lib_program.status!=1 }">
									<input class="small-button-R" type="button" value="프로그램 수정"
										onclick="#">
								</c:if></td>
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
<!-- 관리자페이지 프로그램 목록 끝 -->
