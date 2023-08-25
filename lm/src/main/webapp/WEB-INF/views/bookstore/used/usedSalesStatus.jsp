<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="used-contents">
	<span>판매 상태</span> <!-- 내일 고치자..... -->
	<div class="search-box-byUsed">
		<c:forEach var="list" items="${list}">
			<div class="used-all-contents-div-width">
				<div class="used-all-contents-img">
					<img src="${list.store_product_cover}">
				</div>
				<div class="used-all-contents-column">
					<div class="used-all-contents-box" data-store_product_num="${list.store_product_num}">책 이름 : ${list.store_product_title}</div>
					<c:if test="${list.order_pay_status == 0}">
						<div class="used-all-contents-box">판매중</div>
					</c:if>
					<c:if test="${list.order_pay_status == 1}">
						<div class="used-all-contents-box">판매완료 | 판매날짜 : ${list.order_date}</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
		<%-- <div class="paging">${page}</div> --%>
	</div>
</div>