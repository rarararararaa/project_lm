<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/payment.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/payment.js"></script>
</head>
<body>
<div class="payment-main">
	<p class="payment-title">장바구니</p>
	<div class="payment-main-itemList"><!-- 장바구니 상품리스트 -->
	<div class="payment-allSelect">
		<div id="all">
			<input type="checkbox" value="1" id="allSelect" checked="checked">전체
		</div>
		<div id="select">
			<button class="book-select active animated" value="0" id="lm">전체</button>
			<button class="book-select" value="1" id="lm">LM문고</button>
			<button class="book-select" value="2" id="used">중고상품</button>
		</div>
	</div>
	<div class="payment-book" id="payBook">
	<!-- LM 문고 리스트 -->
		<div class="lm-all" id="41084">
			<input type="checkbox" value="11" id="LM_select">LM문고 선택
		</div>
		<form id="LM_payForm" class="LM-form">
		<table>
		<!-- 반복문 -->
		<c:forEach var="cart" items="${list}">
			<c:if test="${cart.store_product_status == 0}"><!-- 새 상품 : 0 -->
			<c:forEach var="book" items="${book_list}">
			<c:if test="${book.store_product_num == cart.store_product_num}">
			<tr id="작동">
				<td><input type="checkbox" value="2" class="LM-item" name="check_LM"></td>
				<td colspan="3"><!-- 장바구니 상품 내용 -->
					<c:if test="${empty book.store_product_cover }">
						<img alt="" src="${pageContext.request.contextPath}/images/noImage.png">
					</c:if>
						<img alt="" src="${book.store_product_cover}">
					<div class="pay-book-detail">
						<ul id="test">
							<li data-num="${book.store_product_num}">${book.store_product_title}</li>
							<li>${book.store_product_discount}%</li>
							<li>${book.store_product_pricestandard}원</li>
							<li>(${book.store_product_pricestandard*point}P)</li>
						</ul>
					</div>
				</td>
				<td><!-- 삼품 개수 증감 -->
					<ul>
						<li>${book.store_product_pricestandard * cart.cart_quantity}원</li>
						<li>
						<div class="MP">
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/minus.png" id="minus">
							<span>${cart.cart_quantity}</span><%-- 수정 --%>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/plus.png" id="plus">
						</div>
						</li>
					</ul>
				</td>
				<td colspan="2"><!-- 배송 정보 -->
					<p class="delivery-img">배송정보</p>
					<p class="delivery-text">내일(7/25, 화)도착예정</p>
				</td>
			</tr>
			</c:if>
			</c:forEach>
			</c:if>
		</c:forEach>
			<!-- 반복문 끝-->		
		</table>
		</form>
		<div class="notCart-book" id="LM_NOT">
			장바구니에 도서가 없습니다.
		</div>
	</div>
	<div class="payment-usedBook">
		<div class="payment-book" id="UesdBook">
	<!-- LM 중고도서 리스트 -->
		<div class="lm-all">
			<input type="checkbox" value="11" id="UESD_select">중고 선택
		</div>
		<form id="USED_payForm">
		<table>
				<!-- 반복문 -->
		<c:forEach var="cart" items="${list}">
			<c:if test="${cart.store_product_status > 0}"><!-- 새 상품 : 0 -->
			<c:forEach var="book" items="${book_list}">
			<c:if test="${book.store_product_num == cart.store_product_num}">
			<tr>
				<td><input type="checkbox" value="3" class="LM-item"></td>
				<td colspan="3"><!-- 장바구니 상품 내용 -->
					<c:if test="${book.store_product_cover == null}">
						<img alt="" src="${pageContext.request.contextPath}/images/noImage.png">
					</c:if>
						<img alt="" src="${book.store_product_cover}">
					<div class="pay-book-detail">
						<ul id="test">
							<li data-num="${book.store_product_num}">${book.store_product_title}</li>
							<li>${book.store_product_discount}%</li>
							<li>${book.store_product_pricestandard}원</li>
							<li>(${book.store_product_pricestandard*point}P)</li>
						</ul>
					</div>
				</td>
				<td><!-- 삼품 개수 증감 -->
					<ul>
						<li>${book.store_product_pricestandard * cart.cart_quantity}원</li>
						<li>
						<div class="MP">
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/minus.png" id="minus">
							<span>${cart.cart_quantity}</span><%-- 수정 --%>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/plus.png" id="plus">
						</div>
						</li>
					</ul>
				</td>
				<td colspan="2"><!-- 배송 정보 -->
					<p class="delivery-img">배송정보</p>
					<p class="delivery-text">내일(7/25, 화)도착예정</p>
				</td>
			</tr>
			</c:if>
			</c:forEach>
			</c:if>
		</c:forEach>
			<!-- 반복문 끝-->		
		</table>
	</form>
	<div class="notCart-book" id="USED_NOT">
		장바구니에 도서가 없습니다.
	</div>
	</div>
	</div>
	</div><!-- 상품 리스트 -->
	<div class="payment-main-sidebar">
		<div class="sidebar-main">
		<form id="payForm">
			<table id="payInfo">
				<tr>
					<td>상품 금액</td>
					<td id="total">0원</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td id="delivery">3,000원</td>
				</tr>
			</table>
			<hr size="2" noshade="noshade" color="#c7c7c7">
			<table id="payAll">
				<tr>
					<th>결제 예정 금액</th>
					<th id="due">0원</th>
				</tr>
				<tr>
					<td>적립예정 포인트</td>
					<td id="due_point">0원</td>
				</tr>
			</table>
			<input type="submit" value="주문하기" id="paySubmit">
			</form>
		</div>
			<button onclick="location.href='${pageContext.request.contextPath}/bookstore/template/bsMain.do'" class="cancel-btn">주문취소</button>
	</div>
</div>
</body>
</html>