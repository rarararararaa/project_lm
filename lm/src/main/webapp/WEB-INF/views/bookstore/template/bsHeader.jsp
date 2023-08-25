<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/header.js"></script>
<div class="bs-header">
	<div class="bs-header-topbox">
		<div class="bs-header-topmenu">
			<input type="hidden" id="id_check_tag" value="${mem_id}">
			<ul>
				<c:if test="${mem_id == null }">
					<li><a
						href="${pageContext.request.contextPath}/lm/login/template/loginMain.do?lo=1">로그인</a>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/lm/register/template/registerMain.do?lo=1">회원가입</a>
					</li>
				</c:if>
				<c:if test="${mem_id != null }">
					<li>" [ <strong>${mem_id}</strong> ]님 환영합니다. "
					</li>
					<li><a
						href="${pageContext.request.contextPath}/lm/logout/template/logoutMain.do?lo=1">로그아웃</a>
					</li>
					<li><c:if test="${mem_auth < 9}">
							<a
								href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=1">마이페이지</a>
						</c:if> <c:if test="${mem_auth == 9 }">
							<a
								href="${pageContext.request.contextPath}/bookstore/adminMain.do">관리자페이지</a>
						</c:if></li>
				</c:if>
				<li><a
					href="${pageContext.request.contextPath}/bookstore/service/main.do">고객센터</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="bs-header-midbox">
		<div class="bs-main-logo">
				<a
					href="${pageContext.request.contextPath}/bookstore/template/bsMain.do">
					<img src="${pageContext.request.contextPath}/images/logo.png"></a>
			</div>
			<div class="bs-search">
				<div class="search_select">
					<select id="categoryNum" class="select_box_button" style="border-left-width:30px;">
						<option value="1">통합검색</option>
						<option value="2">책제목</option>
						<option value="3">작가</option>
						<option value="4">출판사</option>
					</select>
				</div>
				<div class="search_input">
					<input type="search" class="ip_search" autocomplete="off"
						title="통합검색어 입력">
				</div>
				<div class="bs-text-img">
					<a class="btn_search"></a>
				</div>
			</div>
			<div class="bs-move-main">
				<div>
					<a
						href="${pageContext.request.contextPath}/bookstore/template/bsMain.do">LM문고</a>
				</div>
				<div>
					<a
						href="${pageContext.request.contextPath}/library/template/libMain.do">LM도서관</a>
				</div>
			</div>
			<div class="bs-main-icons">
				<div class="bs-cart">
					<a href="${pageContext.request.contextPath}/bookstore/payment/cart.do"><img src="${pageContext.request.contextPath}/images/cart_alt.png"></a>
				</div>
				<div class="bs-person">
					<a href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=1"><img src="${pageContext.request.contextPath}/images/person_outline.png"></a>
				</div>
			</div>
	</div>
	<div class="bookstore-header-botbox">
		<div class="gnb_wrap">
			<!-- 메뉴 시작-->
			<ul>
				<li class="gnb_item"><a class="gnb_link"
					href="/bookstore/product/productCeteList.do"><em>전체도서</em></a></li>
				<li class="gnb_item"><a class="gnb_link"
					href="/bookstore/product/productBestList.do">베스트</a></li>
				<li class="gnb_item"><a class="gnb_link"
					href="/bookstore/product/productNewList.do">신상품</a></li>
				<li class="gnb_item"><a class="gnb_link"
					href="${pageContext.request.contextPath}/bookstore/event/list.do">이벤트</a>
				</li>
				<li class="gnb_item"><a class="gnb_link"
					href="${pageContext.request.contextPath}/bookstore/used/usedMain.do">중고</a>
				</li>
			</ul>
			<!-- 메뉴 끝-->
			<!-- 할인혜택 / 출석 체크 시작-->
			<ul class="gnb_sub_list">
				<li class="gnb_sub_item"><a class="gnb_sub_link">할인혜택</a></li>
				<li class="gnb_sub_item"><a class="gnb_sub_link">출석체크</a></li>
				<li class="gnb_sub_item more_service">
					<button type="button" class="btn_more_service"></button>
				</li>

			</ul>
			<!-- 할인혜택 / 출석 체크 끝-->
		</div>
	</div>
</div>


