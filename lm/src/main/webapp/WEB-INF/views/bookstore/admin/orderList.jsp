<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<table class="striped-table">
		<tr>
			<th>주문번호</th>
			<th>상세주문번호</th>
			<th>제품번호</th>
			<th>제품가격</th>
			<th>구매수량</th>
			<th>중고제품번호(중고거래시)</th>
		</tr>
		<c:forEach var="order" items="${list}">
		<tr>
			<td>${order.order_num}</td>
			<td>${order.order_detail_num}</td>
			<td>${order.store_product_num}</td>
			<td>${order.order_product_price}</td>
			<td>${order.cart_quantity}</td>
			<td>${order.used_product_num}</td>
		</tr>
		</c:forEach>
	</table>
</div>