<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 기증신청 목록 시작 -->
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>기증품 목록</h2>
	<form action="admin_donationlist.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword"
				                     value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="조회">
				<input type="button" value="기증목록" 
				   onclick="location.href='admin_donationlist.do'">
			</li>
		</ul>
	</form>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 기증정보가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>기증번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록날짜</th>
			<th>기증인</th>
			<th>기증상태</th>
		</tr>
		<c:forEach var="donation" items="${list}">
		<tr class="align-center">
			<td>${donation.donation_num}</td>
			<td>
				<a href="/library/donationadmin/admin_donationupdate.do?donation_num=${donation.donation_num}">${donation.donation_title}</a>
			</td>
			<td>${donation.donation_content}</td>
			<td>${donation.donation_reg_date}</td>
			<td>${donation.donation_name}</td>
			<td>
				<c:if test="${donation.donation_status == 0}"><span style="color: blue;">확인전</span></c:if>
				<c:if test="${donation.donation_status == 1}"><span style="color: blue;">기증수락</span></c:if>
				<c:if test="${donation.donation_status == 2}"><span style="color: red;">기증거부</span></c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>	
</div>
<!-- 기증신청 목록 끝 -->










