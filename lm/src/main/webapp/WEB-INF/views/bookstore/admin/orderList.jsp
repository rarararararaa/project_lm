<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<table class="striped-table">
		<tr>
			<th>주문번호</th>
			<th>제품명</th>
			<th>총결제금액</th>
			<th>주문일</th>
			<th>주문상태</th>
		</tr>
		<c:forEach var="order" items="${list}">
		<tr>
			<td>${order.order_num}</td>
			<td><a href="${pageContext.request.contextPath}/bookstore/adminOrderDetail.do?order_num=${order.order_num}">${order.product_name}</a></td>
			<td>${order.order_total_price}</td>
			<td>${order.order_date}</td>
			<td>
			<c:if test="${order.order_status==0}">주문완료</c:if>
			<c:if test="${order.order_status==1}">배송중</c:if>
			<c:if test="${order.order_status==2}">배송완료</c:if>
			<c:if test="${order.order_status==3}">주문확정</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>