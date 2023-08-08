<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<style>
body {
  font-family: sans-serif;
  font-size: 16px;
  line-height: 1.5;
  color: #333;
  background-color: #fff;
}

h3 {
  font-size: 20px;
  margin-top: 0;
  margin-bottom: 10px;
}

table {
  width: 1200px;
  border-collapse: collapse;
  border: 1px solid;
}

td {
  padding: 10px;
}

img {
  max-width: 100%;
}

.main-body {
  margin: 0 auto;
  width: 1200px;
}

.page {
  text-align: center;
}

.product {
  display: flex;
  flex-direction: column;
}

.product img {
  width: 200px;
  margin-bottom: 10px;
}

.product .info {
  margin-bottom: 10px;
}

.product .info h3 {
  margin-bottom: 0;
}

.product .info .author-publisher {
  font-size: 12px;
}

</style>
<div class="main-body">
	<h3>메인페이지 - 상품</h3>
	<div>
	<c:forEach var="product" items="${product_list}">
		<table>
		<tr>
			<td>
				<a href="/bookstore/product/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">
					<img src="${product.store_product_cover}">
				</a>
			</td>
			<td>
				<a href="/bookstore/product/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">
				제목 : ${product.store_product_title}</a>
			</td>
			<td>
				저자 : ${product.store_product_author}
			</td>
			<td>
				출판사 : ${product.store_product_publisher}
			</td>
			<td>
				책 소개 : ${product.store_product_description}
			</td>
		</tr>
		<tr>
		</tr>
		</table>
	</c:forEach>
	<div>${page}</div>
</div>
</div>
<!-- 내용 끝 -->