<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libPageNav_style.css">
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libPageNav.js"></script>
<!-- 도서관 이용 메뉴 시작 ㅇㄴㅇ-->
<div class="sb-page-nav">
	<h2 class="sb-title">신청·참여</h2>
	<ul class="sb-menu">
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/bookApplyMain.do" class="sub-items">희망도서신청</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/donationMain.do" class="sub-items">도서 기증</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/facilityList.do" class="sub-items">시설 예약</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/programList.do" class="sub-items">프로그램</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/lostReport/bookLostInfo.do" class="sub-items">분실도서신고</a>
		</li>
	</ul>
</div>
<!-- 도서관 이용 메뉴 끝 -->