<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
body {
	margin: 0;
}

.lib-header {
	margin: 0 auto;
	width: 1200px;
	border-bottom: 1px solid #eaeaea
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
	border: 1px solid red;
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
	font-size:18px;
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
<div class="lib-header">
	<div class="lib-header-topbox">
		<div class="lib-header-topmenu">
			<ul>
				<li><a href="#">회원가입</a></li>
				<li><a href="#">로그인</a></li>
				<li><a href="#">고객센터</a></li>
				<li><a href="#">서재</a></li>
			</ul>
		</div>
	</div>
	<div class="lib-header-midbox">
		<div class="lib-main-logo"><a href="#"><img src="../img/lm_lib_logo.png"></a></div>
		<div class="lib-search">
			<div class="lib-search-title">통합검색</div>
			<div class="lib-text-box"><input type="text" class="search-text"></div>
			<div class="lib-text-img"><a href="#"><img src="../img/search.png"></a></div>
		</div>
		<div class="lib-move-main">
			<div><a href="${pageContext.request.contextPath}/main/main.do">LM 문고</a></div>
			<div><a href="${pageContext.request.contextPath}/library/libMain.do">LM 도서관</a></div>
		</div>
		<div class="lib-main-icons">
			<div class="lib-cart"><a href="#"><img src="../img/cart_alt.png"></a></div>
			<div class="lib-person"><a href="#"><img src="../img/person_outline.png"></a></div>
		</div>
	</div>
	<div class="library-header-botbox">
		<div class="lib-header-menu">
			<ul>
				<li><a href="#">자료검색</a></li>
				<li><a href="#">신청 참여</a></li>
				<li><a href="#">시설이용</a></li>
				<li><a href="#">도서관소개</a></li>
			</ul>
		</div>
	</div>
</div>