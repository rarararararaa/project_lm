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
				<h2>주문 내역 상세 정보</h2>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-order-list-detail">상품 정보</div>
				<div id="t-order-list-price">금액</div>
				<div id="t-order-list-amount">구매 수량</div>
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
					<div id="item-list-detail">
						<div id="c-order-list-content">
							제목 : <a
								href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${list.store_product_isbn13}">${list.store_product_title}</a>
						</div>
						<div id="c-order-list-content">글쓴이 :
							${list.store_product_author}</div>
						<div id="c-order-list-content">출판사 :
							${list.store_product_publisher}</div>
					</div>
					<div id="c-order-list-price">${list.order_product_price}</div>
					<div id="c-order-list-amount">${list.cart_quantity}</div>
				</div>
			</c:forEach>
			<div class="bottom-div">
				<div class="order-form">
					<h3>배송지 정보</h3>
					<div class="order-form-info">배송지 이름 : ${mypageVO.home_title}</div>
					<div class="order-form-info">우편번호 : ${mypageVO.home_zipcode}</div>
					<div class="order-form-info">주소 : ${mypageVO.home_address}</div>
					<div class="order-form-info">상세주소 :
						${mypageVO.home_address_detail}</div>
					<div class="order-form-info">전화번호 : ${mypageVO.home_cell}</div>
					<div class="order-form-info">수령인 : ${mypageVO.home_name}</div>
				</div>
				<div class="order-form">
					<h3>최종 결제 정보</h3>
					<div class="order-form-info">총 주문 금액 :
						${mypageVO.order_total_price}</div>
					<c:if test="${mypageVO.order_pay_status==1}">
						<div class="order-form-info">총 결제 금액 :
							${mypageVO.order_total_price}</div>
					</c:if>
					<c:if test="${mypageVO.order_pay_status==0}">
						<div class="order-form-info">결제 취소 금액 :
							${mypageVO.order_total_price}</div>
					</c:if>
					<div class="order-form-info">상품 구매 날짜 :
						${mypageVO.order_date}</div>
					<div class="order-form-info">배송 요청 사항 :
						${mypageVO.order_notice}</div>
					<c:if test="${mypageVO.order_pay_status==0}">
						<div class="order-form-info">결제 상태 : 주문 취소</div>
					</c:if>
					<c:if test="${mypageVO.order_pay_status==1}">
						<div class="order-form-info">결제 상태 : 결제 완료</div>
					</c:if>
					<c:if test="${mypageVO.payment_type==1}">
						<div class="order-form-info">결제 방법 : 신용카드 결제</div>
					</c:if>
					<c:if test="${mypageVO.payment_type==2}">
						<div class="order-form-info">결제 방법 : 카카오페이 결제</div>
					</c:if>
					<c:if test="${mypageVO.order_status==0}">
						<div class="order-form-info">상품 배송 상태 : 주문 완료</div>
					</c:if>
					<c:if test="${mypageVO.order_status==1}">
						<div class="order-form-info">상품 배송 상태 : 배송 중</div>
					</c:if>
					<c:if test="${mypageVO.order_status==2}">
						<div class="order-form-info">상품 배송 상태 : 배송 완료</div>
					</c:if>
				</div>
			</div>
			${error}
			<div class="align-center">${page}</div>
		</div>
	</div>
</body>
</html>