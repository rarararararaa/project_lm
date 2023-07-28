<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
	<div class="header_wrapper">
		<div class="service_mall">
			<div class="service_mall_inner">
				<div class="service_mall_box">
					<ul class="service_mall_list">
						<li class="service_mall_item"><a></a></li>
					</ul>
				</div>
				<div class="customer_service_box">
					<ul class="customer_service_list">
						<li class="customer_service_item">회원가입</li>
						<li class="customer_service_item"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
						<li class="customer_service_item">고객센터</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="header_inner">
			<div class="search_wrap">
				<div class="logo_box"><a class="logo_link"></a></div>
				<div class="search_box">
					<div class="search_select">
						<span class="select_box_button">
							<span class="text">통합검색</span>
							<span class="select-icon"></span>
							<ul class="optionList">
								<li class="optionItem">통합검색</li>
								<li class="optionItem">책제목</li>
								<li class="optionItem">작가</li>
								<li class="optionItem">출판사</li>
							</ul>
						</span>
					</div>
					<div class="search_input">
						<input type="search" class="ip_search" autocomplete="off" title="통합검색어 입력">
					</div>
					<a class="btn_search"></a>
				</div>
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/bookstore/template/bsMain.do">LM 문고</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/library/template/libMain.do">LM 도서관</a>
					</li>
				</ul>
				<ul class="user_menu_list">
					<li class="user_menu_item"><a class="cart_icon"></a></li>
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
												<li class="category-item"><a><span>교보Only</span></a></li>
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
					<a class="gnb_link"><em>책함순</em></a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link"><em>나나나</em></a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">베스트</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">신상품</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">이벤트</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">사은품</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">PICKS</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">CASTing</a>
				</li>
				<li class="gnb_item">
					<a class="gnb_link">교보ONLY</a>
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

