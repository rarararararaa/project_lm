<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/payment.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/payment.js"></script>
</head>
<body>
<div class="payment-main">
	<p class="payment-title">장바구니</p>
	<div class="payment-allSelect">
		<div id="all">
			<input type="checkbox" value="1">전체
		</div>
		<div id="select">
			<button class="book-select" value="1" id="lm">전체</button>
			<button class="book-select" value="1" id="lm">LM문고</button>
			<button class="book-select" value="1" id="used">중고상품</button>
		</div>
		<div class="lm-all">
			<input type="checkbox" value="1">LM문고 선택
		</div>
	</div>
	<div class="payment-book">
		<table>
		<!-- 반복문 -->
			<tr>
				<td><input type="checkbox" value="1"></td>
				<td colspan="3"><!-- 장바구니 상품 내용 -->
					<img alt="" src="${pageContext.request.contextPath}/images/noImage.png">
					<div class="pay-book-detail">
						<ul>
							<li>[국내도서]위기의 역사</li>
							<li>10%</li>
							<li>25,000원</li>
							<li>(1,400p)</li>
						</ul>
					</div>
				</td>
				<td><!-- 삼품 개수 증감 -->
					<ul>
						<li>25,000원</li>
						<li>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/minus.png">
							<span>1</span>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/plus.png">
						</li>
					</ul>
				</td>
				<td colspan="2"><!-- 배송 정보 -->
					<img src="${pageContext.request.contextPath}/images/Rectangle_blue.png">
				</td>
			</tr>		
		</table>
	</div>
</div>
</body>
</html>