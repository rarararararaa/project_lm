<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libPageNav.js"></script>
<!-- Admin 사이드바 시작 -->
<div class="sb-page-nav">
	<h2 class="sb-title">관리자 페이지</h2>
	<ul class="sb-menu">
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/member/admin_list.do" 
				class="sub-items">회원 관리</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/bookproductadmin/admin_booklist.do" 
				class="sub-items">도서 관리</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/donationadmin/admin_donationlist.do" 
				class="sub-items">기증 승인</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/boardannounce/list.do" 
				class="sub-items">공지사항</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/liblostitem/list.do" 
				class="sub-items">분실물</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/service/admin_boardAskList.do" 
				class="sub-items">1:1문의</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/basic/lib_ScheduleAdminList.do" 
				class="sub-items">일정 관리</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/apply/programAdminList.do" 
				class="sub-items">프로그램 관리</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/apply/facilityAdminList.do" 
				class="sub-items">시설 관리</a>
		</li>
	</ul>
</div>
<!-- Admin 사이드바 끝 -->