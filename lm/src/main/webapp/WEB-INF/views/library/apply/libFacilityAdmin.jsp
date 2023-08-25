<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 관리자페이지 시설 목록 시작 -->
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
	<div class="box">
		<div class="title">전체 시설 목록</div>
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
							onclick="location.href='facilityAdminList.do'"> <input
							class="small-button-B" type="button" value="신규시설등록"
							onclick="location.href='admin_insertAdminFacility.do'"></li>
					</ul>
				</div>
			</form>
			<c:if test="${count == 0}">
				<div class="row-content">표시할 시설목록이 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>No</b></th>
						<th class="row-title"><b>시설명</b></th>
						<th class="row-title"><b>예약현황</b></th>
						<th class="row-title"><b>예약(비활성화)</b></th>
					</tr>
					<c:forEach var="lib_facility" items="${list}">
						<tr class="align-center">
							<td class="row-title">${lib_facility.facility_num}</td>
							<td class="row-title">${lib_facility.facility_name}</td>
							<td class="row-title">예약가능</td>
							<td class="row-title"><input class="small-button-R"
								type="button" value="비활성화" onclick="#"></td>
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
<!-- 관리자페이지 시설 목록 끝 -->





