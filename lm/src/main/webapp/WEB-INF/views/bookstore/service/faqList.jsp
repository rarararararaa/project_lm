<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-main">
	
	<ul>
		<li>
		<select id="faq_category" name="faq_category">
				<option value="1"
					<c:if test="${param.faq_category == 1}">selected</c:if>>배송</option>
				<option value="2"
					<c:if test="${param.faq_category == 2}">selected</c:if>>취소/교환</option>
				<option value="3"
					<c:if test="${param.faq_category == 3}">selected</c:if>>포인트/쿠폰</option>
				<option value="4"
					<c:if test="${param.faq_category == 4}">selected</c:if>>중고</option>
				<option value="5"
					<c:if test="${param.faq_category == 5}">selected</c:if>>상품</option>
				<option value="6"
					<c:if test="${param.faq_category == 6}">selected</c:if>>회원관리</option>
				<option value="7"
					<c:if test="${param.faq_category == 7}">selected</c:if>>주문/결제</option>
		</select>
		</li>
		<c:forEach items="list" var="faq">
		<li><a href="#">${faq.faq_title}</a></li>
		</c:forEach>
	</ul>
</div>