<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/payment.css">
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/paymentOrder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
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
				<c:if test="${empty home}">
					기본 배송지를 등록해주세요.	
				</c:if>
				<c:if test="${!empty home}">
					배송지를 추가할 수 있습니다.
				</c:if>
				</li>
				
				<li class="both-clear">
					<button id="deli-btn" onclick="fnShowPop('re_pwd')">배송지 등록</button>
					<jsp:include page="order_modal.jsp"></jsp:include>
				</li>
			</ul>
			<hr size="1" noshade="noshade" class="del-hr">
			<ul>
				<li>기본배송지</li>
				<li>
				<input type="hidden" id="memInfo" data-email="${mem.mem_email}" data-zipcode="${home.home_zipcode}"
				data-address="${home.home_address} ${home.home_address_detail}">
					<p data-cell="${home.home_cell}" id="cell">${home.home_name } / ${home.home_cell}</p>
					<c:if test="${empty home}">
						<span class="empty">설정된 배송지가 없습니다.</span>
					</c:if>
					<c:if test="${!empty home }">
						<span id="default_deli">
							[${home.home_zipcode}] ${home.home_address} ${home.home_address_detail}
						</span>
					</c:if>
					<button id="deli-change" onclick="fnShowPop('re_pwd')">변경</button>
				</li>
			</ul>
			<hr size="1" noshade="noshade" class="del-hr both-clear">
			<!-- 배송 시 요청 사함 -->
			<ul>
				<li class="float-left">배송 요청사항</li>
				<li>
					<select id="deli-request">
						<option value="-">배송 메세지를 선택해 주세요.</option>
						<option value="부재시 경비실에 맡겨주세요.">부재시 경비실에 맡겨주세요.</option>
						<option value="부재시 문앞에 놓아주세요.">부재시 문앞에 놓아주세요.</option>
						<option value="배송전에 연락주세요.">배송전에 연락주세요.</option>
					</select>
				</li>
			</ul>
		</div>
		<!-- 도서 정보 -->
		<div class="bookInfo">
			<ul>
				<li class="float-left">주문 상품</li>
				<li style="display: flex;">
					<p id="count" data-count="${count}">총 ${ count }개</p>
					<img src="${pageContext.request.contextPath}/images/order_book.png" id="book_detail">
				</li>
				<li class="book-list hidden-place">
				<table class="orderBookList">
				<!-- 반복문 -->
				<c:forEach var="cart" items="${list}">
				<c:forEach var="book" items="${book_list}">
				<c:if test="${book.store_product_num == cart.store_product_num && (cart.used_product_num == book.used_product_num) }">
					<tr>
						<td>
						<c:if test="${book.store_product_cover == ' '}">
							<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${book.store_product_isbn13}">
							<img src="${pageContext.request.contextPath}/images/noImage.png" width="85">
							</a>
						</c:if>
						<c:if test="${book.store_product_cover != ' '}">
							<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${book.store_product_isbn13}">
							<img src="${book.store_product_cover}">
							</a>
						</c:if>
						</td>
						<td>
							<ul>
								<li class="title">${book.store_product_title}<c:if test="${cart.used_product_num > 0 }">[중고]</c:if>
								</li>
								<li class="float-left orderListlitwo">${book.store_product_discount}%</li>
								<li><fmt:formatNumber value="${book.store_product_pricestandard}"/>원</li>
							</ul>
						</td>
						<td  class="clear-both">${cart.cart_quantity}개</td>
						<td>
						<c:if test="${cart.used_product_num > 0}">
							<fmt:formatNumber value="${book.used_product_price}"/>원
						</c:if>
						<c:if test="${cart.used_product_num == 0}">
							<fmt:formatNumber value="${book.store_product_pricestandard * cart.cart_quantity}"/>원
						</c:if>
						</td>
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
					data-maxpoint="${mem.mem_point}" min="0">원
					<button id="allPoint">전액사용</button>
				</li>
			</ul>
		</div>
		<!-- 결제 수단 -->
		<div class="bookInfo pay-type">
			<ul>
				<li class="float-left">결제수단</li>
				<li>
					<button id="card" value="1" class="type-payment">신용카드</button>
					<button id="kakaoPay" value="2" class="type-payment">카카오페이</button>
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
					<td id="total" data-total="${total}"><fmt:formatNumber value="${total}"/>원</td>
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
					<th id="due" data-total="${total}">
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
					<td id="due_point"><fmt:formatNumber value="${total * point}" maxFractionDigits="0"/>원</td>
			</table>
			<input type="submit" value="주문하기" id="paySubmit" data-type="" 
			data-dueTotal=" <c:if test="${total < 50000 }">${total+3000}</c:if> <c:if test="${total >= 50000 }">${total}</c:if>">
			</form>
		</div>
			<button onclick="location.href='${pageContext.request.contextPath}/bookstore/template/bsMain.do'" class="cancel-btn">주문취소</button>
	</div>	
</div>

</div>
</body>
</html>