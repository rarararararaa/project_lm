<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
	.radio label{
		float:none;
	}
</style>
<div class="page-main">
	<h2>상품 정보 수정</h2>
	<form:form modelAttribute="productVO" action="adminProductModify.do" id="product-register">
		<form:hidden path="store_product_num"/>
		<ul>
			<li>
				제목 : ${productVO.store_product_title}
			</li>
			<li>
				글쓴이 : ${productVO.store_product_author}
			</li>
			<li>
				출판사 : ${productVO.store_product_publisher}
			</li>
			<li>
				출간일 : ${productVO.store_product_pubdate}
			</li>
		</ul>
		<br>
		<ul>
			<li>
				<form:label path="store_product_description">설명</form:label>
				<form:textarea path="store_product_description"/>
			</li>
			<li>
				정가 : ${productVO.store_product_pricestandard}
			</li>
			<li>
				<form:label path="store_product_pricesales">할인가</form:label>
				<form:input path="store_product_pricesales"/>
			</li>
			<li>
				<form:label path="store_product_stock">재고</form:label>
				<form:input path="store_product_stock"/>
			</li>
			<li >
				<form:label path="store_product_stockstatus" class="title-label">판매상태</form:label>
				<div class="radio">
				<form:radiobutton path="store_product_stockstatus" value="1" label="표시"/> 
				<form:radiobutton path="store_product_stockstatus" value="0" label="품절"/> 
				<form:radiobutton path="store_product_stockstatus" value="2" label="미표시"/>
				</div>
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
			<input type="button" value="목록" onclick="location.href='adminProductList.do'">
		</div>
	</form:form>
</div>