<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/productList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<div class="main-page">
	<div class="side-bar float-left">
		<div class="search-main">
			${cate}
			<img alt="" src="${pageContext.request.contextPath}/images/dropDown.png" id="drop_down">
		</div>
		<div class="hidden-place search-cate" data-cate="${cate}">
			<p><a href="productCeteList.do?cate=국내도서">국내도서</a></p>
			<p><a href="productCeteList.do?cate=외국도서">외국도서</a></p>
		</div>
		<b>${cate}</b>
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
		<p style="color: #828181" data-category="${categoryTitle}" id="cate_title">${cate} > ${categoryTitle}</p>
		<div class="product-event">
			<select name="order" id="order">
				<option value="4"<c:if test="${order == 4}">selected="selected"</c:if>>최신순</option>
				<option value="2"<c:if test="${order == 2}">selected="selected"</c:if>>가격높은순</option>
				<option value="3"<c:if test="${order == 3}">selected="selected"</c:if>>가격낮은순</option>
			</select>
			<select name="count_num" id="count_num">
				<option value="10"<c:if test="${count_num == 10}">selected="selected"</c:if>>10개씩 보기</option>
				<option value="20"<c:if test="${count_num == 20}">selected="selected"</c:if>>20개씩 보기</option>
			</select>
		</div>
		<div class="book-list">
		<c:if test="${!empty list}">
		<div class="page" style="margin-top: 10px;">
			${page}
		</div>
		</c:if>
		<div class="book-table">
		<table>
		<!-- 반복문 -->
			<c:forEach var="book" items="${list}">
			<tr id="tregsd">
				<td>
					<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${book.store_product_isbn13}"><img src="${book.store_product_cover}"></a>
					<div class="book-detail">
					<form action="cart.do" method="post" class="book-cart">
						<ul id="test">
							<li data-num="${book.store_product_num}"><a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${book.store_product_isbn13}">${book.store_product_title}</a>
							<c:if test="${now < book.store_product_pubdate}"><span style="color: blue;">[예약판매]</span></c:if>
							</li>
							<li>${book.store_product_discount}%</li>
							<li>
							<fmt:formatNumber value="${book.store_product_pricestandard}"/>원
							</li>
							<li>
							(<fmt:formatNumber value="${book.store_product_pricestandard*0.005}"/>P)
							<input type="hidden" name="store_product_num" value="${book.store_product_num}" class="product">
							<input type="hidden" name="cart_quantity" value="1"> 
							<input type="hidden" class="price" value="${book.store_product_pricestandard}">
							</li>
						</ul>
					</form>
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
					<button class="product-btn" id="cart" onclick="submitUsedProduct()">장바구니</button>
					<button class="product-btn payGo" id="pay">바로구매</button>
				</td>
			</tr>				
			</c:forEach>
		<!-- 반복문 -->
		</table>
		<c:if test="${empty list}">
			<div class="non-list">
				<span style="color: blue;">${cate} > ${categoryTitle}</span> 카테고리에는 현재 등록된 도서가 없습니다.
			</div>
		</c:if>
		</div>
		<c:if test="${!empty list}">
		<div class="page">
			${page}
		</div>
		</c:if>
		</div>
	</div>  
</div>
