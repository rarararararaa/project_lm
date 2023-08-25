<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<!-- 파라미터 세팅 -->
	<%
	pageContext.setAttribute("lo", request.getParameter("lo"));
	%>
	<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
	<input type="hidden" name="lo" value="${lo}" />
	<div class="container">
		<div class="left-div">
			<div class="page-name">내 정보</div>
			<ul class="menu-box">
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=${lo}">주문
						내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/asklist/askListMain.do?lo=${lo}">문의
						내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do?lo=${lo}">대출/반납
						내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/wantbooklist/wantBookListMain.do?lo=${lo}">희망도서
						신청 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/programapplylist/programApplyListMain.do?lo=${lo}">프로그램
						신청 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/facilityapplylist/facilityApplyListMain.do?lo=${lo}">시설
						이용 신청 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/bookreservationlist/bookReservationListMain.do?lo=${lo}">도서
						예약 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/booklostlist/bookLostListMain.do?lo=${lo}">분실
						도서 신고 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/eventparticipatelist/eventParticipateListMain.do?lo=${lo}">이벤트
						참여 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/usedbookapplylist/usedBookApplyListMain.do?lo=${lo}">중고
						도서 등록 내역</a></li>
			</ul>
		</div>
		<div class="right-div">
			<div class="list-name">
				<h2>찜한 도서 목록</h2>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-zzim-detail">상품 정보</div>
				<div id="t-zzim-price">금액</div>
			</div>
			<hr id="title-list-hr">
			<c:forEach var="list" items="${list}">
				<div id="item-list2">
					<div class="view-photo2">
						<a
							href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${list.store_product_isbn13}">
							<img src="${list.store_product_cover}" class="view-photo2">
						</a>
					</div>
					<div id="item-list-detail5">
						<div id="c-zzim-content">
							제목 : <a
								href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${list.store_product_isbn13}">${list.store_product_title}</a>
						</div>
						<div id="c-zzim-content">글쓴이 :
							${list.store_product_author}</div>
						<div id="c-zzim-content">출판사 :
							${list.store_product_publisher}</div>
					</div>
					<div id="c-zzim-price">${list.store_product_pricesales}</div>
				</div>
			</c:forEach>
			<div class="align-center">${page}</div>
		</div>
	</div>
</body>
</html>