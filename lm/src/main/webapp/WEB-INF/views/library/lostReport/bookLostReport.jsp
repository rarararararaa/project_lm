<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bookLostReport.css">

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lostReport.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="lost-form-page">
	<!-- 대출중인 도서 출력 -->
	<div class="rent-list">
		<div class="text-box">
		<span class="te-title">현재 대출중인 도서</span>
		<span class="te-subtitle">분실 신고할 도서 하나를 선택해주세요</span>
		</div>
		<table>
		<tr>
			<th>선택</th>
			<th>제목</th>
			<th>대출일</th>
			<th>도서가격</th>
		</tr>
		<c:forEach var="rent" items="${list}" varStatus="status">
		<tr>
			<td class="al-c">
				<input type="radio" id="lostBook_sel${status.index}" name="lostBook" class="lostBook">
			</td>
			<td>
				<label for="lostBook_sel${status.index}" class="book-name" data-name="${rent.product_bookName}">${rent.product_bookName}</label>
				<input type="hidden" class="book-isbn" data-isbn="${rent.lib_product_isbn}">
				<input type="hidden" class="callNumber" data-callNumber="${rent.callNumber}">
				<input type="hidden" class="rent_num" data-rentNum="${rent.rent_num}">
			</td>
			<td class="al-c">${rent.rent_reg_date}</td>
			<td class="al-c"><span class="book-price" data-price="${rent.product_price}">${rent.product_price}</span></td>
		</tr>	
		</c:forEach>
	</table>
	</div>

	<!-- 도서 선택후 출력되는 부분 -->
	<div id="checked_action" class="check">
		<form action="">
			<!-- 분실신고 선택된 도서 -->
			<div class="select-book">
				<h2>선택된 분실 신고 도서</h2>
				<table>
					<tr>
						<th>ISBN</th>
						<th >제목</th>
						<th>도서 가격</th>
					</tr>
					<tr>
						<td><span class="sel-book-ISBN"></span></td>
						<td><span class="sel-book-name"></span></td>
						<td><span class="sel-book-price"></span></td>
					</tr>
				</table>
				<!-- <span class="sel-book-name"></span> <span class="sel-book-price"></span>
				 -->
			</div>
			<div class="mem-info">
				<h3>결제 회원 정보</h3>
				<ul>
					<li>
						<span class="li-left">이름</span>
						<span class="buyer-name">${memberVO.mem_name}</span>
					</li>
					<li>
						<span class="li-left">전화번호</span>
						<span class="buyer-tell">${memberVO.mem_cell}</span>
					</li>
					<li>
						<span class="li-left">이메일</span>
						<span class="buyer-email">${memberVO.mem_email}</span>
					</li>
				</ul>
				<%-- <span class="buyer-name">이름 : ${memberVO.mem_name}</span> <span
					class="buyer-tell">전화번호 : ${memberVO.mem_cell}</span> <span
					class="buyer-email">이메일 : ${memberVO.mem_email}</span> --%>
			</div>

			<div class="pay-check">
				<h3>결제 방법 선택</h3>
				<div class="align-center">
				<label class="radio-selBox"> 
				<input type="radio" id="card" value="1" class="type-payment" name="payment-sel"> 
					<span>신용카드</span>
				</label> 
				
				<label class="radio-selBox"> 
				<input type="radio" id="kakaoPay" value="2" class="type-payment" name="payment-sel">
					<span>카카오페이</span>
				</label>
				</div>
				<div class="pay-btn align-center">
					<div class="price-text">결제금액 <span class="fin-price"></span></div>
					<div><input type="submit" value="결제하기" id="lostSubmit" data-type=""></div>
				</div>
				
				
			</div>
		</form>
	</div>
</div>
	
	
</body>
</html>