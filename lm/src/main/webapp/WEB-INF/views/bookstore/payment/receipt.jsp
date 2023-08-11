<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/receipt.css">
</head>
<body>
<div class="payment-main">
	<p class="receipt-title">결제가 완료되었습니다.</p>
	<div class="receipt-content">
		<div class="content">
			<div class="sub-title">
				<p>LM BookStore</p>
				<p><fmt:formatNumber value="${order.order_total_price}"/>원</p>
				<p>
					<c:if test="${order.payment_type == 1}">
						카드 결제
					</c:if>
					<c:if test="${order.payment_type == 2}">
						카카오 페이
					</c:if>
				</p>
			</div>
			<div class="sub-content">
				<table>
					<tr>
						<td class="font-gray">승인 날짜</td>
						<td class="align-right">${order.order_date}</td>
					</tr>
					<tr>
						<td class="font-gray">승인 번호</td>
						<td class="align-right">${order.IMP_UID}</td>
					</tr>
					<tr>
						<td class="font-gray">거래 유형</td>
						<td class="align-right">승인</td>
					</tr>
					<tr>
						<td class="font-gray">주문 도서</td>
						<td class="align-right">${book_name}</td>
					</tr>
					<tr>
						<td class="font-gray">결제 금액</td>
						<td class="align-right"><fmt:formatNumber value="${order.order_total_price}"/>원</td>
					</tr>
					<tr>
						<td class="font-gray">주문 상태</td>
						<td class="align-right">주문 완료</td>
					</tr>
					<tr>
						<td class="font-gray">배송지</td>
						<td class="align-right">${homeInfo.home_address} ${homeInfo.home_address_detail}</td>
					</tr>
					<tr>
						<td class="font-gray">배송시 요청사항</td>
						<td class="align-right">${order.order_notice}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>