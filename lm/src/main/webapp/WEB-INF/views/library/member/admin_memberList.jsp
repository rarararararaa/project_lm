<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 회원목록 - 관리자 시작 -->
<script type="text/javascript">
	$(function() {
		//검색 유효성 체크
		$('#search_form').submit(function() {
			if ($('#keyword').val().trim() == '') {
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<div class="box">
		<div class="title">전체 회원 목록</div>
		<div class="content-box">
			<!-- 검색창 시작 -->
			<form action="admin_list.do" id="search_form" method="get">
				<div class="button-box">
					<ul class="main-content">
						<li><select name="keyfield">
								<option value="1"
									<c:if test="${param.keyfield == 1}">selected</c:if>>ID</option>
								<option value="2"
									<c:if test="${param.keyfield == 2}">selected</c:if>>이름</option>
								<option value="3"
									<c:if test="${param.keyfield == 3}">selected</c:if>>이메일</option>
								<option value="4"
									<c:if test="${param.keyfield == 4}">selected</c:if>>전체</option>
						</select></li>
						<li><input type="search" name="keyword" id="keyword"
							value="${param.keyword}"></li>
						<li><input class="small-button" type="submit" value="조회">
							<input class="small-button" type="button" value="목록"
							onclick="location.href='admin_list.do'"></li>
					</ul>
				</div>
			</form>
			<!-- 검색창 끝 -->
			<c:if test="${count == 0}">
				<div class="row-content">표시할 회원정보가 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>ID</b></th>
						<th class="row-title"><b>이름</b></th>
						<th class="row-title"><b>전화번호</b></th>
						<th class="row-title"><b>이메일</b></th>
						<th class="row-title"><b>가입일</b></th>
						<th class="row-title"><b>권한</b></th>
					</tr>
					<c:forEach var="member" items="${list}">
						<tr class="align-center">
							<td class="row-title"><c:if test="${member.mem_auth==0}">${member.mem_id}</c:if>
								<c:if test="${member.mem_auth > 0}">
									<a href="admin_update.do?mem_num=${member.mem_num}">${member.mem_id}</a>
								</c:if></td>
							<td class="row-title">${member.mem_name}</td>
							<td class="row-title">${member.mem_cell}</td>
							<td class="row-title">${member.mem_email}</td>
							<td class="row-title">${member.mem_reg_date}</td>
							<td class="row-title">
								<c:if test="${member.mem_auth == 0}">
									<div class="expire">
										<b>탈퇴</b>
									</div>
								</c:if> 
								<c:if test="${member.mem_auth == 1}">
									<div class="stop">
										<b>정지</b>
									</div>
								</c:if> 
								<c:if test="${member.mem_auth == 2}">
									<div class="sleep">
										<b>휴면</b>
									</div>
								</c:if> 
								<c:if test="${member.mem_auth == 3}">
									<b>일반</b>
								</c:if> 
								<c:if test="${member.mem_auth == 4}">
									<div class="notcheck">
										<b>미인증</b>
									</div>
								</c:if> 
								<c:if test="${member.mem_auth == 9}">
									<div class="answer">
										<b>관리자</b>
									</div>
								</c:if>
							</td>
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
<!-- 회원목록 - 관리자 끝 -->










