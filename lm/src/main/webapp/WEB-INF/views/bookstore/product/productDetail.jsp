<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EESAMSAOH.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/productDetail.js"></script>
</head>
<body>
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
			<div class="tab-list-wrap">
				
			</div>
			<div class="prod-detail-content">
				<img src="${product.store_product_cover}">
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
					<span>${product.store_product_pricestandard} 원</span>
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
						<button class="btn-line-gray"><span class="wish-ico"></span></button>
						<button class="btn-lg cart-btn" id="cart_btn">장바구니</button>
						<button class="btn-lg orderRightAway" id="orderRigthAway">바로구매</button>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>