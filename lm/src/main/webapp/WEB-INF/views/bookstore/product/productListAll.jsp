<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/productList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<div class="main-page">
	<div class="side-bar float-left">
		<p>국내도서</p>
		<b>국내도서</b>
		<div class="category">
			<ul>
			<c:forEach var="cate" items="${category}">
				<li data-category="${cate}" class="select-cate">
					${cate}
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="content">
		<p style="color: #828181">국내도서 > ${categoryTitle}</p>
		<div class="product-event">
			<select>
				<option value="1">최신순</option>
				<option value="2">조회순</option>
				<option value="3">댓글순</option>
			</select>
			<select>
				<option value="1">10개씩 보기</option>
				<option value="2">20개씩 보기</option>
			</select>
		</div>
		<div class="book-list">
		<table>
			<c:forEach var="book" items="${list}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${book.store_product_isbn13}"><img src="${book.store_product_cover}"></a>
					<div class="book-detail">
						<ul id="test">
							<li data-num="${book.store_product_num}"><a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${book.store_product_isbn13}">${book.store_product_title}</a></li>
							<li>${book.store_product_discount}%</li>
							<li>${book.store_product_pricestandard}원</li>
							<li>(${book.store_product_pricestandard*point}P)</li>
						</ul>
					</div>
				</td>
				<td colspan="2">
					<div>
						${book.store_product_description}
						<c:if test="${book.store_product_description == ' '}">
							도서 상세 정보가 없습니다.
						</c:if>
					</div>
				</td>
				<td>
					<button class="product-btn" id="cart">장바구니</button>
					<button class="product-btn" id="pay">바로구매</button>
				</td>
			</tr>				
			</c:forEach>
		</table>
		<div class="page">
			${page}
		</div>
		</div>
	</div>  
</div>
