<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
<style>
.search_wrap {
	height: 120px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.search_box {
	width: 560px;
	height: 48px;
	border-radius: 25px;
	display: flex;
	align-items: center;
	margin-left: 50px;
	margin-right: 30px;
}

.search_box-title {
	width: 135px;
	height: 48px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.select-icon-img {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 54px;
	width: 54px;
}

.search_box img {
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

.bs-move-main {
	width: 200px;
	height: 120px;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size:18px;
}

.bs-move-main div {
	width: 100px;
	text-align: center;
}

.bs-move-main ul {
	margin: 0;
	font-size: 25px;
	height: 120px;
	display: flex;
	justify-content: center;
	list-style-type: none;
}

.user_menu_list {
	width: 150px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.user_menu_list ul {
	width: 50px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.search_input {
	display:flex;
	justify-content:center;
	align-items:center;
}


</style>
	<div class="header_wrapper">
		<div class="service_mall">
			<div class="service_mall_inner">
				<div class="service_mall_box">
					<ul class="service_mall_list">
						<li class="service_mall_item"><a></a></li>
					</ul>
				</div>
				<div class="customer_service_box">
				<input type="hidden" id="id_check_tag" value="${mem_id}">
					<ul class="customer_service_list">
						<li class="customer_service_item"><a href="${pageContext.request.contextPath}/lm/register/template/registerMain.do?lo=1">회원가입</a></li>
						<c:if test="${mem_id == null }">
							<li class="customer_service_item"><a href="${pageContext.request.contextPath}/lm/login/template/loginMain.do?lo=1">로그인</a></li>
						</c:if>
						<c:if test="${mem_id != null }">
							<li>
								임시 / mem_num = <strong>${mem_num}</strong>, mem_id = <strong>${mem_id}</strong>, mem_auth = <strong>${mem_auth}</strong>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=1">마이페이지</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/lm/logout/template/logoutMain.do?lo=1">로그아웃</a>
							</li>
						</c:if>
						<c:if test="${mem_auth == 9 }">
							<li>
								<a href="${pageContext.request.contextPath}/bookstore/adminMain.do">관리자페이지</a>
							</li>
						</c:if>
						<li class="customer_service_item"><a href="${pageContext.request.contextPath}/bookstore/service/main.do">고객센터</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="header_inner">
			<div class="search_wrap">
				<div class="logo_box"><a href="${pageContext.request.contextPath}/bookstore/template/bsMain.do" class="logo_link"></a></div>
				<div class="search_box">
					<div class="search_select">
						<select id="categoryNum" class="select_box_button">
							<option value="1">통합검색</option>
							<option value="2">책제목</option>
							<option value="3">작가</option>
							<option value="4">출판사</option>
						</select>
						<!-- <span class="select_box_button"> 
							<span class="text">통합검색</span>
							<span class="select-icon"></span>
							<ul class="optionList">
								<li class="optionItem">통합검색</li>
								<li class="optionItem">책제목</li>
								<li class="optionItem">작가</li>
								<li class="optionItem">출판사</li>
							</ul>
						</span> -->
					</div>
					<div class="search_input">
						<input type="search" class="ip_search" autocomplete="off" title="통합검색어 입력">
					</div>
					<a class="btn_search"></a>
				</div>
				<div class="bs-move-main">
					<div class="bs-move-mainlink"><a href="${pageContext.request.contextPath}/bookstore/template/bsMain.do">LM문고</a></div>
					<div class="bs-move-mainlink"><a href="${pageContext.request.contextPath}/library/template/libMain.do">LM도서관</a></div>
				</div>
				<ul class="user_menu_list">
					<li class="user_menu_item"><a class="cart_icon"  href="${pageContext.request.contextPath}/bookstore/payment/cart.do"></a></li>
					<li class="user_menu_item"><a class="mypage_icon"></a></li>
				</ul>
			</div>
		<nav class="gnb_wrap">
		<div class="anb_wrap" id="test">
			<button class="btn_anb" onclick="btn_toggle();" value="1">
				<!-- 메뉴 더보기 -->
			</button><!-- 전체 버튼 -->
			<!-- 전체 메뉴 펼치기 -->
			<div class="anb_area">
				<div class="tap_wrap ui-tabs ui-corner-all ui-widget-content">
					<div class="tab_list_wrap">
						<ul class="tabs">
							<li class="tab_item ui-state-active"><a class="tab_link text-black-bold font-big"><span class="tab_text">카테고리 전체보기</span></a></li>
							<li class="tab_item ui-state-active"><a class="tab_link text-black-bold font-big"><span class="tab_text">서비스 전체보기</span></a></li>
						</ul>
					</div>
					<div id="tabAnbCategory" class="tab_content">
						<div class="tab_wrap">
							<div class="tab_list_wrap" id="sub_title">
								<ul class="tabs">
									<li class="tab_item"><a class="tab_link"><span class="tab_text text-black-bold" id="sub_text">교보문고</span></a></li>
								</ul>
							</div>
							<!-- 카테고리 상세 -->
							<div id="tabAnbCategoryKyobo" class="tab_content anb_category">
								<div class="anb_category_inner">
									<div class="tab_wrap category">
									<!-- 도서 분류 -->
										<div class="tab_list_wrap">
											<ul>
												<li class="category-item"><a><span>국내도서</span></a></li>
												<li class="category-item"><a><span>서양도서</span></a></li>
												<li class="category-item"><a><span>일본도서</span></a></li>
											</ul>
										</div>
									<!-- 도서 분류 상세 -->
									<jsp:include page="bsMenu.jsp">
										<jsp:param value="국내도서" name="title"/>
									</jsp:include>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			<!-- 메뉴 -->
			<ul class="gnb_list">
				<li class="gnb_item">
					<a class="gnb_link" href="/bookstore/product/productCeteList.do"><em>전체도서</em></a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link" href="/bookstore/product/productBestList.do">베스트</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link" href="/bookstore/product/productNewList.do">신상품</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link"  href="${pageContext.request.contextPath}/bookstore/event/list.do">이벤트</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link" href="${pageContext.request.contextPath}/bookstore/used/usedMain.do">중고</a>
				</li>
			</ul>
			<!-- 메뉴 -->
			<!-- 할인혜택 / 출석 체크 -->
			<ul class="gnb_sub_list">
				<li class="gnb_sub_item">
					<a class="gnb_sub_link">할인혜택</a>	
				</li>
				<li class="gnb_sub_item">
					<a class="gnb_sub_link">출석체크</a>					
				</li>
				<li class="gnb_sub_item more_service">
					<button type="button" class="btn_more_service">
					</button>
				</li>
				
			</ul>
			<!-- 할인혜택 / 출석 체크 -->
		</nav>
		</div>
	</div>

