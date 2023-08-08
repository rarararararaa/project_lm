<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EESAMSAOH.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/productDetail.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/productZzim.js"></script>
<div class="product-main">
	<div>
	
	</div>
	<div class="content-wrap">
		<div class="content-header">
			<div>${product.store_product_title}</div>
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="content">
			<div class="prod-detail-content">
				<div class="prod-author">${product.store_product_author}</div>
				<div class="prod-pub">${product.store_product_publisher} · ${product.store_product_pubdate}</div>
				<div >
					<c:if test="${product.store_product_customerReviewRank!=0}">
						${product.store_product_searchcategoryName} ${product.store_product_customerReviewRank}위
					</c:if>
				</div>
				<div class="review-box">
					<div class="star-ratings">
						<div class="star-ratings-fill" style="--rating: ${product.store_product_ratingScore};">
							<span>⭐</span>
							<span>⭐</span>
							<span>⭐</span>
							<span>⭐</span>
							<span>⭐</span>
						</div>
					</div>
					<div>
						<span class="review-score">
							${product.store_product_ratingScore} 
						</span>
						<span class="review-count">
							(${product.store_product_ratingCount}개의 리뷰)
						</span>
					</div>
				</div>
			</div>
			<div class="prod-detail-content">
				<img src="${product.store_product_cover}">
			</div>
			<div class="prod-detail-content">
				<div class="prod-price">
					<span>
						<c:if test="${product.store_product_discount>0 }">
							${product.store_product_discount} %
						</c:if>
					</span>
					<span class="price">
						<span class="val">
							<fmt:formatNumber value="${product.store_product_pricestandard}"/>
						</span>
						<span class="unit"> 원</span>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="purchase-wrap">
		<div class="purchase-inner">
		<form id="product_cart">
			<div class="left-area">
				<span class="prod-info-title"></span>
				<span class="prod-info-price">
					<span style="display:none" id="fixedprice">${product.store_product_pricestandard}</span>
					<span id="total">
						${product.store_product_pricestandard} 원
					</span>

				</span>
			</div>
			<div class="right-area">
				<div class="count-box">
					<span>
						<button class="decrease"></button>
						<input name="cart_quantity" type="number" value="1" autocomplete="off" readonly="readonly">
						<button class="increase"></button>
					</span>
				</div>
				<div class="prod-button-box">
						<input type="hidden" name="store_product_num" value="${product.store_product_num}">
						<input type="hidden" name="store_product_pricestandard" value="${product.store_product_pricestandard}">
						<input type="hidden" name="store_product_stock" value="${product.store_product_stock}">
						<input type="hidden" name="store_product_title" value="${product.store_product_title}">
						<button class="btn-line-gray" id="output_zzim"  data-num="${product.store_product_num}"><span class="wish-ico"></span></button>
						<button class="btn-lg cart-btn" id="cart_btn" onclick="submitCart()">장바구니</button>
						<button class="btn-lg orderRightAway" id="orderRightAway">바로구매</button>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
