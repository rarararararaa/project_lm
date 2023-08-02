<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="used-contents">
	<span>중고 판매 상품</span>
		<div class="search-box-byUsed">
			<div class="search-box-width">
				<div class="search-title-byUsed">중고 등록 번호</div>
				<div class="general-box">등록자</div>
				<div class="general-box">책상태</div>
				<div class="general-box">등록날짜</div>
				<div class="general-box">조회 수</div>
				<div class="general-box">가격</div>
				<div class="general-box">조회수</div>
			</div>
			<c:forEach var="list" items="${list}">
				<div class="search-box-width">
					<div class="search-title-byUsed-result">${list.used_product_num}</div>
					<div class="general-box" >${list.mem_num}</div>
					<div class="general-box">${list.used_product_condition }</div>
					<div class="general-box">${list.used_product_reg_date}</div>
					<div class="general-box">${list.used_product_hit}</div>
					<div class="general-box">${list.used_product_price }</div>
					<div class="general-box">${list.used_product_info}</div>
					
				</div>
			</c:forEach>
		</div>
</div>


