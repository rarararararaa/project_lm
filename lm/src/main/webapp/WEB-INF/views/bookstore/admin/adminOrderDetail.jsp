<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<table class="striped-table">
		<tr>
			<th>주문상세번호</th>
			<th>제품명</th>
			<th>주문수량</th>
			<th>가격</th>
		</tr>
		<c:forEach var="detail" items="${detailList}">
		<tr>
			<td>${detail.order_detail_num}</td>
			<td>${detail.product_name}</td>
			<td>${detail.cart_quantity}</td>
			<td>${detail.order_product_price}</td>
		</tr>
		</c:forEach>
	</table>
	<form action="adminOrderDetail.do" method="post">
		<input type="hidden" value="${param.order_num}" id="order_num" name="order_num">
		<label for="order_status">주문 상태 변경</label>	
		<input type="radio" value="0" name="order_status" <c:if test="${order.order_status==0}">checked</c:if>><span>주문완료</span>
		<input type="radio" value="1" name="order_status" <c:if test="${order.order_status==1}">checked</c:if>><span>배송중</span>
		<input type="radio" value="2" name="order_status" <c:if test="${order.order_status==2}">checked</c:if>><span>배송완료</span>
		<input type="radio" value="3" name="order_status" <c:if test="${order.order_status==3}">checked</c:if>><span>주문확정</span>
	
	<input type="submit" value="변경">
	</form>
</div>