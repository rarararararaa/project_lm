<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 기증신청 목록 시작 -->
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
		<div class="title">전체 기증 목록</div>
		<div class="content-box">
			<form action="admin_donationlist.do" id="search_form" method="get">
				<div class="button-box">
					<ul class="main-content">
						<li><select name="keyfield">
								<option value="1"
									<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
								<option value="2"
									<c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
								<option value="3"
									<c:if test="${param.keyfield == 3}">selected</c:if>>제목+내용</option>
						</select></li>
						<li><input type="search" name="keyword" id="keyword"
							value="${param.keyword}"></li>
						<li><input class="small-button" type="submit" value="조회">
							<input class="small-button" type="button" value="목록"
							onclick="location.href='admin_donationlist.do'"></li>
					</ul>
				</div>
			</form>
			<c:if test="${count == 0}">
				<div class="row-content">표시할 기증정보가 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>No</b></th>
						<th class="row-title"><b>제목</b></th>
						<th class="row-title"><b>내용</b></th>
						<th class="row-title"><b>등록일</b></th>
						<th class="row-title"><b>기증자</b></th>
						<th class="row-title"><b>승인 여부</b></th>
					</tr>
					<c:forEach var="donation" items="${list}">
						<tr class="align-center">
							<td class="row-title">${donation.donation_num}</td>
							<td class="row-title"><a
								href="/library/donationadmin/admin_donationupdate.do?donation_num=${donation.donation_num}">${donation.donation_title}</a>
							</td>
							<td class="row-title">${donation.donation_content}</td>
							<td class="row-title">${donation.donation_reg_date}</td>
							<td class="row-title">${donation.donation_name}</td>
							<td class="row-title"><c:if
									test="${donation.donation_status == 0}">
									<span style="color: orange;"><b>미확인</b></span>
								</c:if> <c:if test="${donation.donation_status == 1}">
									<span style="color: blue;"><b>기증 수락</b></span>
								</c:if> <c:if test="${donation.donation_status == 2}">
									<span style="color: red;"><b>기증 거부</b></span>
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
<!-- 기증신청 목록 끝 -->










