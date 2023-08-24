<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libPageNav_style.css">
<!-- jQuery 1-->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libPageNav.js"></script>
<!-- 도서관 이용 메뉴 시작 -->
<div class="sb-page-nav">
	<h2 class="sb-title">도서 상세</h2>
	<ul class="sb-menu">
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?
						categoryNum=10&orderByNum=1&keyword=" class="sub-items">도서 검색</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/basic/informationUse1.do" class="sub-items">이용안내</a>
			<ul class="sb-menu-sub">
				<li class="sb-menu-sub-item">
					<a href="${pageContext.request.contextPath}/library/basic/informationUse1.do">도서관 이용 시간</a>
				</li>
				<li class="sb-menu-sub-item">
					<a href="${pageContext.request.contextPath}/library/basic/informationUse2.do">대출·반납·연장·예약</a>
				</li>
			</ul>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/basic/informationUse3.do" class="sub-items">자료실 안내</a>
			<ul class="sb-menu-sub">
				<li class="sb-menu-sub-item">
					<a href="${pageContext.request.contextPath}/library/basic/informationUse3.do">일반 자료실 안내</a>
				</li>
				<li class="sb-menu-sub-item">
					준수 사항
				</li>
			</ul>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/liblostitem/listUser.do" class="sub-items">분실물 찾기</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/basic/" class="sub-items">문의하기</a>
			<ul class="sb-menu-sub">
				<li class="sb-menu-sub-item">
					<a href="${pageContext.request.contextPath}/library/boardannounce/Userlist.do">공지사항</a>
				</li>
				<li class="sb-menu-sub-item">
					<a href="${pageContext.request.contextPath}/library/service/user_boardAskList.do">1:1문의</a>
				</li>
			</ul>
		</li>
	</ul>
</div>
<!-- 도서관 이용 메뉴 끝 -->