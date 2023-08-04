<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div>
	<c:forEach var="product" items="${product}">
		<table>
		<tr>
			<td>
				<a href="/lm/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">
				<img src="${product.store_product_cover}">
				</a>
			</td>
			<td>
				<a href="/lm/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">
				${product.store_product_title}</a>
			</td>
		</tr>
		<tr>
		</tr>
		</table>
	</c:forEach>
	<div>${page}</div>
</div>
