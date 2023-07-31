<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 이름 찾기</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.search-title-byUsed-result').click(function(){
			
			// productNum과 productTitle 받기
		    let productNum = $(this).data('product-num');
		    let productTitle = $(this).text();

     	    // window.opener는 부모의 스크립트임..
		    let parentWin = window.opener;
		    parentWin.$('#store_product_title').val(productTitle);
		    parentWin.$('#hidden_store_product_num').val(productNum);
		    
		    window.close();
		});
	})
</script>


<style>
	body{
		margin:0;
	}
	form {
		margin:0;
	}

	.used-default-btn {
		width:180px;
		height:20px;
		margin:0;
		font-size:20px;
	}

	.search-pop {
		width:300px;
		height:20px;
		margin:0;
	}

	.search-box-byUsed {
		margin-top:50px;
		width:500px;
		
		display:flex;
		align-items:center;
		justify-contents:center;
		flex-wrap: wrap;
		border:solid 1px black;
	}
	
	.search-box-width {
		width:500px;
		height:30px;
		display:flex;
		align-items:center;
		justify-contents:center;
	}
	
	.general-box {
		width : 100px;
		height:30px;
		display:flex;
		align-items:center;
		justify-contents:center;
		border:solid 1px black;
	}
	
	.search-title-byUsed {
		width:200px;
		height:30px;
		display:flex;
		align-items:center;
		justify-contents:center;
		border:solid 1px black;
		
	}
	
	.search-title-byUsed-result {
		width:200px;
		height:30px;
		display:flex;
		align-items:center;
		justify-contents:center;
		border:solid 1px black;
	}
</style>
</head>
<body>
	<!-- 검색창을 만들고, 찾기를 클릭 했을 때 List로 목록들을 보여주고, 그걸 클릭했을 때 그 값으로 고정되게 해야함-->
	<form action="/bookstore/used/selectProductNameByUsed.do" name="usedPopup" method="get" >
		<input type="text" name="keyword" class="search-pop">
		<button type="submit" class="used-default-btn">찾기</button>
	</form>
	<c:if test="${ success == 1 }">
		<div class="search-box-byUsed">
			<div class="search-box-width">
				<div class="search-title-byUsed">책이름</div>
				<div class="general-box">분류</div>
				<div class="general-box">저자</div>
				<div class="general-box">출판사</div>
			</div>
			<c:forEach var="list" items="${list}">
				<div class="search-box-width">
					<div class="search-title-byUsed-result" data-product-num="${list.store_product_num}">${list.store_product_title}</div>
					<div class="general-box" >${list.store_product_categoryname }</div>
					<div class="general-box">${list.store_product_author}</div>
					<div class="general-box">${list.store_product_publisher}</div>
					
				</div>
			</c:forEach>
		</div>
	</c:if>
</body>
</html>