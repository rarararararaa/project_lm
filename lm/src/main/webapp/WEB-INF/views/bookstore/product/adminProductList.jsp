<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>상품 관리</h2>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 상품이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<ul class="search">
			<li><select name="keyfield" id="keyfield">
					<option value="1"
						<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2"
						<c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					<option value="3"
						<c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
			</select></li>
			<li><input type="search" name="keyword" id="keyword"
				value="${param.keyword}"></li>
			<li><input type="submit" value="찾기"> <input
				type="button" value="목록"
				onclick="location.href='adminProductList.do.do'"></li>
		</ul>
		<table class="striped-table">
		<tr>
			<th>NO.</th>
			<th>제목</th>
			<th>가격</th>
			<th>재고</th>
			<th>출간일</th>
			<th>상태</th>
		</tr>
		<c:forEach var="product" items="${list}">
		<tr>
			<td class="align-center">${product.store_product_num}</td>
			<td width="400">
				<a href="adminProductModify.do?product.store_product_num=${product.store_product_num}">${product.store_product_title}</a>
			</td>
			<td class="align-center">${product.store_product_pricesales}</td>
			<td class="align-center">${product.store_product_stock}</td>
			<td class="align-center">${product.store_product_pubdate}</td>
			<td class="align-center">
			<c:if test="${product.store_product_stockstatus==0}">표시</c:if>
			<c:if test="${product.store_product_stockstatus==1}">미표시</c:if>
			<c:if test="${product.store_product_stockstatus==2}">품절</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="글쓰기" onclick="location.href='announceWrite.do'">
	</c:if>
</div>