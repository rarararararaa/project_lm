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
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/paymentOrder.js"></script>
</head>
<body>
<div class="payment-main">
	<p class="payment-title">주문/결제</p>
	<c:if test="${empty cartList }">
		
	</c:if>
	<c:forEach var="test" items="${ cartList }">
		test.store_product_num	
	</c:forEach>
	<!-- 사이드 바 -->
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