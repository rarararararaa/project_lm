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
	<div class="payment-main-itemList"><!-- 장바구니 상품리스트 -->
	<div class="payment-allSelect">
		<div id="all">
			<input type="checkbox" value="1" id="allSelect" checked="checked">전체
		</div>
		<div id="select">
			<button class="book-select active animated" value="0" id="lm">전체</button>
			<button class="book-select" value="1" id="lm">LM문고</button>
			<button class="book-select" value="2" id="used">중고상품</button>
		</div>
	</div>
	<div class="payment-book" id="payBook">
	<!-- LM 문고 리스트 -->
		<div class="lm-all" id="41084">
			<input type="checkbox" value="11" id="LM_select">LM문고 선택
		</div>
		<form id="LM_payForm">
		<table>
		<!-- 반복문 -->
			<tr>
				<td><input type="checkbox" value="2" class="LM-item"></td>
				<td colspan="3"><!-- 장바구니 상품 내용 -->
					<img alt="" src="${pageContext.request.contextPath}/images/noImage.png">
					<div class="pay-book-detail">
						<ul id="test">
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
						<div class="MP">
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/minus.png" id="minus">
							<span>1</span><%-- 수정 --%>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/plus.png" id="plus">
						</div>
						</li>
					</ul>
				</td>
				<td colspan="2"><!-- 배송 정보 -->
					<p class="delivery-img">배송정보</p>
					<p class="delivery-text">내일(7/25, 화)도착예정</p>
				</td>
			</tr>
			<!-- 반복문 끝-->		
		</table>
		</form>
	</div>
	<div class="payment-usedBook">
		<div class="payment-book" id="UesdBook">
	<!-- LM 중고도서 리스트 -->
		<div class="lm-all">
			<input type="checkbox" value="11" id="UESD_select">중고 선택
		</div>
		<form id="USED_payForm">
		<table>
		<!-- 반복문 -->
			<tr>
				<td><input type="checkbox" value="1" class="LM-item"></td>
				<td colspan="3"><!-- 장바구니 상품 내용 -->
					<img alt="" src="${pageContext.request.contextPath}/images/noImage.png">
					<div class="pay-book-detail">
						<ul>
							<li>[국내도서]위기의 역사</li>
							<li>10%</li>
							<li>26,000원</li>
							<li>(1,400p)</li>
						</ul> 
					</div>
				</td>
				<td><!-- 삼품 개수 증감 -->
					<ul>
						<li>25,000원</li>
						<li>
						<div class="MP">
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/minus.png" id="minus">
							<span>1</span>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/plus.png" id="plus">
						</div>
						</li>
					</ul>
				</td>
				<td colspan="2"><!-- 배송 정보 -->
					<p class="delivery-img">배송정보</p>
					<p class="delivery-text">내일(7/25, 화)도착예정</p>
				</td>
			</tr>
			<!-- 반복문 끝-->		
			<!-- 반복문 -->
			<tr>
				<td><input type="checkbox" value="11" class="LM-item"></td>
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
						<div class="MP">
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/minus.png" id="minus">
							<span>3</span>
							<img class="MP-btn" src="${pageContext.request.contextPath}/images/plus.png" id="plus">
						</div>
						</li>
					</ul>
				</td>
				<td colspan="2"><!-- 배송 정보 -->
					<p class="delivery-img">배송정보</p>
					<p class="delivery-text">내일(7/25, 화)도착예정</p>
				</td>
			</tr>
			<!-- 반복문 끝-->		
		</table>
	</form>
	</div>
	</div>
	</div><!-- 상품 리스트 -->
	<div class="payment-main-sidebar">
		<div class="sidebar-main">
		<form id="payForm">
			<table id="payInfo">
				<tr>
					<td>상품 금액</td>
					<td id="total">0원</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td id="delivery">3,000원</td>
				</tr>
			</table>
			<hr size="2" noshade="noshade" color="#c7c7c7">
			<table id="payAll">
				<tr>
					<th>결제 예정 금액</th>
					<th id="due">0원</th>
				</tr>
				<tr>
					<td>적립예정 포인트</td>
					<td id="due_point">0원</td>
				</tr>
			</table>
			<input type="submit" value="주문하기" id="paySubmit">
			</form>
		</div>
	</div>
</div>
</body>
</html>