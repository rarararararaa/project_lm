<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/payment.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/paymentOrder.js"></script>
</head>
<body>
<div class="payment-main">
	<p class="payment-title">주문/결제</p>
<div class="orderContent">
	<div class="myOrderInfo">
	<!-- 배송 정보 -->
		<div class="delInput">
			<ul>
				<li class="float-left">배송지 정보</li>
				<li>
					기본 배송지를 등록해주세요.	
				</li>
				<li class="both-clear">
					<button id="deli-btn">배송지 등록</button>
				</li>
			</ul>
			<hr size="1" noshade="noshade" class="del-hr">
			<ul>
				<li>기본배송지</li>
				<li>
					<p>홍길동 / 010-1234-1234</p>
					<c:if test="${empty mem.home_address}">
						설정된 배송지가 없습니다.
					</c:if>
					<button id="deli-change">변경</button>
				</li>
			</ul>
			<hr size="1" noshade="noshade" class="del-hr both-clear">
			<!-- 배송 시 요청 사함 -->
			<ul>
				<li class="float-left">배송 요청사항</li>
				<li>
					<button id="deli-request">배송 요청사항 메시지</button>
				</li>
			</ul>
		</div>
		<!-- 도서 정보 -->
		<div class="bookInfo">
			<ul>
				<li class="float-left">주문 상품</li>
				<li style="display: flex;">
					<p>총 ${ count }개</p>
					<img src="${pageContext.request.contextPath}/images/order_book.png" id="book_detail">
				</li>
				<li class="book-list hidden-place">
				<table class="orderBookList">
				<c:forEach var="cart" items="${list}">
				<c:forEach var="book" items="${book_list}">
				<c:if test="${book.store_product_num == cart.store_product_num}">
					<tr>
						<td>
						<c:if test="${book.store_product_cover == ' '}">
							<img src="${pageContext.request.contextPath}/images/noImage.png" width="85">
						</c:if>
							<img src="${book.store_product_cover}">
						</td>
						<td>
							<ul>
								<li>${book.store_product_title}</li>
								<li class="float-left">${book.store_product_discount}%</li>
								<li><fmt:formatNumber value="${book.store_product_pricestandard}"/>원</li>
							</ul>
						</td>
						<td  class="clear-both">${cart.cart_quantity}개</td>
						<td><fmt:formatNumber value="${book.store_product_pricestandard * cart.cart_quantity}"/>원</td>
					</tr>
				</c:if>
				</c:forEach>				
				</c:forEach>
				</table>
				</li>
			</ul>
		</div>
		<!-- 할인 쿠폰 -->
		<div class="bookInfo">
			<ul>
				<li class="float-left">할인 쿠폰</li>
				<li style="display: flex;">
					<p>총 1개</p>
				</li>
				<li class="coupon-list">
					<hr size="2" noshade="noshade" class="both-clear">
					적용할 할인 쿠폰이 없습니다.
				</li>
			</ul>
		</div>
		<!-- 포인트 -->
		<div class="bookInfo pointInfo">
			<ul>
				<li class="float-left">포인트</li>
				<li>
					<label for="mem_point">${mem.mem_point}</label>원
					<input type="number" name="mem_point" id="mem_point" value="0" max="${mem.mem_point}"
					data-maxPoint="${mem.mem_point}">원
					<button id="allPoint">전액사용</button>
				</li>
			</ul>
		</div>
		<!-- 결제 수단 -->
		<div class="bookInfo pay-type">
			<ul>
				<li class="float-left">결제수단</li>
				<li>
					<button id="cart">신용카드</button>
					<button id="kakaoPay">카카오페이</button>
				</li>
			</ul>
		</div>
	</div>
<!-- 사이드 바 -->
	<div class="payment-main-sidebar">
		<div class="sidebar-main">
		<form id="payForm">
			<table id="payInfo">
				<tr>
					<td>상품 금액</td>
					<td id="total"><fmt:formatNumber value="${total}"/>원</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td id="delivery">
						<c:if test="${total >= 50000 }">0원</c:if>
						<c:if test="${total < 50000 }"><fmt:formatNumber value="3000"/>원</c:if>
					</td>
				</tr>
				<tr>
					<td>쿠폰/할인</td>
					<td>0원</td>
				</tr>
				<tr>
					<td>포인트</td>
					<td id="side_point">0원</td>
				</tr>
			</table>
			<hr size="2" noshade="noshade" color="#c7c7c7">
			<table id="payAll">
				<tr>
					<th>결제 예정 금액</th>
					<th id="due">
					<c:if test="${total < 50000 }">
						<fmt:formatNumber value="${total + 3000}"/>원
					</c:if>
					<c:if test="${total >= 50000 }">
						<fmt:formatNumber value="${total}"/>원
					</c:if>
					</th>
				</tr>
				<tr>
					<td>적립예정 포인트</td>
					<td id="due_point"><fmt:formatNumber value="${total * point}"/>원</td>
				</tr>
			</table>
			<input type="submit" value="주문하기" id="paySubmit">
			</form>
		</div>
			<button onclick="location.href='${pageContext.request.contextPath}/bookstore/template/bsMain.do'" class="cancel-btn">주문취소</button>
	</div>	
</div>

</div>
</body>
</html>