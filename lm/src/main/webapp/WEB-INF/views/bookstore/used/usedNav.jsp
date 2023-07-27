<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.used-book-main {
		width:1200px;
		margin:0 auto;
		border: 1px solid red;
	}
	.used-book-nav {
		width:200px;
		border : 1px solid green;
		display:flex;
		flex-direction: column;
	}
	.used-book-nav div {
		height:100px;
		display:flex;
		flex-direction:row;
		justify-contents:center;
		align-items:center;
		border : 1px solid green;
	}
</style>
<div class="used-book-main">
	<div class="used-book-nav">
		<div>
			<a href="#">중고 상품</a>
		</div>
		<div>
			<a href="#">중고 등록</a>
		</div>
		<div>
			<a href="#">등록한 중고 상품</a>
		</div>
		<div>
			<a href="#">중고 판매 상태</a>
		</div>
	</div>
</div>