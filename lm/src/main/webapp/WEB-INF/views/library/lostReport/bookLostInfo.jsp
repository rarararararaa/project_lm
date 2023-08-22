<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.lostpage-main{
	width: 900px;
	margin: 50px auto;
}
.lost-btn{
	display:inline-block;
	width: 200px;
	height: 48px;
	margin: 20px auto;
	background-color: #549f6e;
	color: white;
	border: none;
	border-radius: 10px;
	
}
.align-center{
	text-align: center;
}

</style>
<div class="lostpage-main">
<h2>자료 분실(훼손)</h2>

<ul>
	<li>대출하신 도서를 분실 및 훼손하신경우 해당 도서의 정가로 변상해 주셔야 합니다. </li>
</ul>
<div class="align-center">
	<input type="button" class="lost-btn" value="분실(훼손) 신고하기" onclick="location.href='bookLostReport.do'">
</div>
</div>