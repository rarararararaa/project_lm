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
		width: 150px;
    	height: 54px;
    	font-size: 15px;
   		border: 1px solid black;
    	padding: 0px 11px 1px 15px;
    	border-radius: 4px;
    	background-color: #E19B50;
    	color: black;
    	cursor: pointer;
		display:flex;
		justify-content: center;
		align-items:center;
		text-decoration: none;
	}

	.search-pop {
		width: 340px;
    	height: 54px;
    	font-size: 14px;
    	border: 1px solid rgb(0, 0, 0);
    	padding: 0px 11px 1px 15px;
    	border-radius: 4px;
	}

	
	
	.form-align {
		margin:0 auto;
		display:flex;
		justify-contents:center;
		align-items:center;
		margin-top:20px;
		margin-bottom:20px;
	}
	
	.form-align input {
		margin-left:20px;
		margin-right:20px;
	}
	
	
	table, td, th {
		border-collapse:collapse;
	}
	.search-box-byUsed th {
		background-color:white;
		font-size:15px;
	}
	
	.search-box-byUsed td {
		border:solid 1px black;
		text-align:center;
		min-width: 100px;
	}
	
	.search-result-count {
		margin:0 auto;
		margin-top:20px;
		margin-bottom:20px;
		text-align:center;
	}
	
	.search-title-byUsed-result:hover {
		cursor: pointer;
		color:#E19B50;
	}
</style>
</head>
<body>
	<!-- 검색창을 만들고, 찾기를 클릭 했을 때 List로 목록들을 보여주고, 그걸 클릭했을 때 그 값으로 고정되게 해야함-->
	<form action="/bookstore/used/selectProductNameByUsed.do" name="usedPopup" method="get" class="form-align" >
		<input type="text" name="keyword" class="search-pop">
		<input type="submit" class="used-default-btn" value="검색">
	</form>
	
	<c:if test="${ success == 1 }">
		
		<table class="search-box-byUsed">
			<tr class="search-result-count">
				<th>검색결과 총 : ${count}건</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			<tr class="search-box-width">
				<td class="general-title">책이름</td>
				<td class="general-title">분류</td>
				<td class="general-title">저자</td>
				<td class="general-title">출판사</td>
			</tr>
			<c:forEach var="list" items="${list}">
				<tr class="search-box-width">
					<td class="search-title-byUsed-result" data-product-num="${list.store_product_num}">${list.store_product_title}</td>
					<td class="general-box" >${list.store_product_categoryname }</td>
					<td class="general-box">${list.store_product_author}</td>
					<td class="general-box">${list.store_product_publisher}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<div class="paging">${page}</div>
</body>
</html>