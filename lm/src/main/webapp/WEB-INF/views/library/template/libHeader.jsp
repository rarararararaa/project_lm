<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
body {
	margin: 0;
}

.lib-header {
	margin: 0 auto;
	width: 1200px;
}

a {
	text-decoration: none; /* 언더바 제거 */
	color: black;
}

a:hover {
	text-decoration: none; /* 호버링 시 언더바 제거 */
	color: #E19B50;
}

.lib-header-topbox {
	height: 40px;
}

.lib-header-topmenu ul {
	margin-top: 0;
	margin-bottom: 0;
	font-size: 15px;
	height: 40px;
	display: flex;
	justify-content: flex-end;
}

.lib-header-topmenu ul li {
	line-height: 40px;
	margin-right: 30px;
	list-style-type: none;
}

.lib-header-midbox {
	height: 120px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.lib-search {
	width: 560px;
	height: 48px;
	border-radius: 25px;
	border: 2px solid green;
	display: flex;
	align-items: center;
	margin-left: 50px;
	margin-right: 30px;
}

.lib-search-title {
	width: 135px;
	height: 48px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.lib-text-img {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 54px;
	width: 54px;
}

.lib-text-img a {
	width: 30px;
	height: 30px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.lib-search img {
	height: 18px;
	width: 18px;
}

.search-text {
	height: 48px;
	width: 370px;
	background-color: inherit;
	border: none;
	outline: none;
}

.lib-move-main {
	width: 200px;
	height: 120px;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 18px;
}

.lib-move-main div {
	width: 100px;
	text-align: center;
}

.lib-move-main ul {
	margin: 0;
	font-size: 25px;
	height: 120px;
	display: flex;
	justify-content: center;
	list-style-type: none;
}

.library-header-botbox {
	width: 1200px;
}

.library-header-botbox ul {
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	list-style-type: none;
	font-size: 25px;
}

.library-header-botbox ul li {
	margin-left: 50px;
	margin-right: 50px;
}

.lib-main-icons {
	width: 150px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.lib-main-icons div {
	width: 50px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.lib-main-icons div a {
	width: 32px;
	height: 32px;
	margin: 0;
}

.lib-cart img {
	width: 32px;
	height: 32px;
}

.lib-person img {
	width: 32px;
	height: 32px;
}
</style>
<script type="text/javascript">
	$(function() {

		function performSearch() {
			let keyword = $('.search-text').val();
			let url = "${pageContext.request.contextPath}";
			// 검색 페이지로 이동
			window.location.href = url
					+ '/library/template/libSearchMain.do?categoryNum=10&orderByNum=1&keyword='
					+ keyword;
		}

		$('.lib-text-img').click(function() {
			performSearch();
		});

		$('.search-text').keypress(function(event) {
			if (event.which === 13) { // Enter 키의 keyCode는 13입니다.
				performSearch();
			}
		});
	});
</script>
<div class="lib-header">
	<div class="lib-header-topbox">
		<div class="lib-header-topmenu">
			<input type="hidden" id="id_check_tag" value="${mem_id}">
			<ul>
				<li>
					<c:if test="${mem_id == null }">
						<li class="customer_service_item">
							<a href="${pageContext.request.contextPath}/lm/login/template/loginMain.do?lo=2">로그인</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/lm/register/template/registerMain.do?lo=2">회원가입</a>
						</li>
					</c:if>
				</li>
				<c:if test="${mem_id != null }">
					<li>
						" [ <strong>${mem_id}</strong> ]님 환영합니다. "
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/lm/logout/template/logoutMain.do?lo=2">로그아웃</a>
					</li>
					<li>
						<c:if test="${mem_auth < 9}">
							<a href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=2">마이페이지</a>
						</c:if> 
						<c:if test="${mem_auth == 9}">
							<a href="${pageContext.request.contextPath}/library/template/libAdmin.do">관리자페이지</a>
						</c:if>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
	<div class="lib-header-midbox">
		<div class="lib-main-logo">
			<a
				href="${pageContext.request.contextPath}/library/template/libMain.do"><img
				src="${pageContext.request.contextPath}/images/lm_lib_logo.png"></a>
		</div>
		<div class="lib-search">
			<div class="lib-search-title">통합검색</div>
			<div class="lib-text-box">
				<input type="text" class="search-text">
			</div>
			<div class="lib-text-img">
				<a href="#"><img src="${pageContext.request.contextPath}/images/search.png"></a>
			</div>
		</div>
		<div class="lib-move-main">
			<div>
				<a href="${pageContext.request.contextPath}/bookstore/template/bsMain.do">LM문고</a>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/library/template/libMain.do">LM도서관</a>
			</div>
		</div>
		<div class="lib-main-icons">
			<div class="lib-cart">
				<a href="#"><img src="${pageContext.request.contextPath}/images/cart_alt.png"></a>
			</div>
			<div class="lib-person">
				<a href="#"><img src="${pageContext.request.contextPath}/images/person_outline.png"></a>
			</div>
		</div>
	</div>
	<div class="library-header-botbox">
		<div class="lib-header-menu">
			<ul>
				<li>
					<a class="gnb_link" href="${pageContext.request.contextPath}/library/template/libSearchMain.do?
						categoryNum=10&orderByNum=1&keyword="><em>자료검색</em></a>
				</li>
				<li>
					<a class="gnb_link" href="${pageContext.request.contextPath}/library/bookApplyMain.do">신청참여</a>
				</li>
				<li>
					<a class="gnb_link" href="${pageContext.request.contextPath}/library/facilityList.do">시설이용</a>
				</li>
				<li>
					<a class="gnb_link" href="${pageContext.request.contextPath}/library/basic/informationUse1.do">도서관이용</a>
				</li>
			</ul>
		</div>
	</div>
</div>