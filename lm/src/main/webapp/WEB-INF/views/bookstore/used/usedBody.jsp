<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="used-contents">
	<span>중고 판매 상품</span>
	<div class="search-box-byUsed">
		<c:forEach var="list" items="${list}">
			<div class="used-all-contents-div-width">
				<div class="used-all-contents-img">
					<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${list.store_product_isbn13}">
						<img src="${list.store_product_cover}">
					</a>
				</div>
				<div class="used-all-contents-column">
					<div class="used-all-contents-box" data-store_product_num="${list.store_product_num}">등록된 중고 : ${list.used_product_match_count}개</div>
					<div class="used-all-contents-box">${list.store_product_title}</div>
					<div class="used-all-contents-box">저자 : ${list.store_product_author} | 등록 날짜 : ${list.store_product_pubdate} | 출판사 : ${list.store_product_publisher} | 판매 가격 : ${list.store_product_pricesales}원</div>
					<div class="used-all-contents-box">내용 : ${list.store_product_description }</div>
					<div class="used-all-contents-box">후기 : ${list.store_product_ratingcount}개 | 리뷰 점수 : ${list.store_product_ratingscore}점</div>
				</div>
			</div>
		</c:forEach>
		<div class="paging">${page}</div>
	</div>
</div>


