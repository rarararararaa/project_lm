<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<p>${keyword}월의 매출은 <fmt:formatNumber value="${total}"/>원 입니다.</p>
	<h2>${keyword}월 최다 매출 상품 TOP5</h2>
	<table class="striped-table">
		<tr>
			<th>제목</th>
			<th>매출</th>
			<th>판매량</th>
		</tr>
		<c:forEach var="item" items="${list}">
		<tr>
			<td>${item["STORE_PRODUCT_TITLE"]}</td>
			<td><fmt:formatNumber value="${item['TOTAL']}"/>원</td>
			<td><fmt:formatNumber value="${item['TOTAL_QUANTITY']}"/>권</td>
		</tr>
		</c:forEach>
	</table>
</div>