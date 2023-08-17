<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/Myorder.js"></script>
<body>
<form:form modelAttribute="myPageVO" action="myPageMain.do" id="myPage-form" class="myPage-form">
	<!-- 파라미터 세팅 -->
	<%
	pageContext.setAttribute("lo", request.getParameter("lo"));
	%>
	<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
	<input type="hidden" name="lo" value="${lo}" />
	<div class="container">
		<div class="left-div">
			<div class="page-name">내 정보</div>
			<ul class="menu-box">
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=${lo}">주문
						내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/asklist/askListMain.do?lo=${lo}">문의
						내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do?lo=${lo}">대출/반납
						내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/wantbooklist/wantBookListMain.do?lo=${lo}">희망도서
						신청 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/programapplylist/programApplyListMain.do?lo=${lo}">프로그램
						신청 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/facilityapplylist/facilityApplyListMain.do?lo=${lo}">시설
						이용 신청 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/bookreservationlist/bookReservationListMain.do?lo=${lo}">도서
						예약 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/booklostlist/bookLostListMain.do?lo=${lo}">분실
						도서 신고 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/eventparticipatelist/eventParticipateListMain.do?lo=${lo}">이벤트
						참여 내역</a></li>
				<li><a class="detail-menu"
					href="${pageContext.request.contextPath}/lm/mypage/usedbookapplylist/usedBookApplyListMain.do?lo=${lo}">중고
						도서 등록 내역</a></li>
			</ul>
		</div>
		<div class="right-div">
			<div class="list-name">
				<h2>주문 내역</h2>
			</div>
			<div class="status">
				<div class="status-info" id="step1">입금/결제 완료</div>
				<div class="status-info" id="step2">배송중</div>
				<div class="status-info" id="step3">배송완료</div>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-mypage-num">주문 번호</div>
				<div id="t-mypage-title">상품정보</div>
				<div id="t-mypage-price">총 주문 금액</div>
				<div id="t-mypage-date">주문 일자</div>
				<div id="t-mypage-status">주문 상태</div>
			</div>
			<hr id="title-list-hr">
			<c:forEach var="list" items="${list}">
				<div class="item-list">
					<div id="c-mypage-num"><a href="${pageContext.request.contextPath}/lm/mypage/orderlist/orderListMain.do?lo=${lo}&order_num=${list.order_num}">${list.order_num}</a></div>
					<div id="c-mypage-title"><a href="${pageContext.request.contextPath}/lm/mypage/orderlist/orderListMain.do?lo=${lo}&order_num=${list.order_num}">${list.store_product_title}</a></div>
					<div id="c-mypage-price">${list.order_total_price}</div>
					<div id="c-mypage-date">${list.order_date}</div>
					<c:if test="${list.order_pay_status == 0}">
						<span id="t-mypage-status" style="color: red">주문 취소</span>
					</c:if>
					<c:if test="${list.order_status == 0 && list.order_pay_status == 1}">
						<div id="c-mypage-status">주문 완료
						<button name="order_num" value="${list.order_num}" class="cancelPay">주문 취소</button>
						</div>
					</c:if>
					<c:if test="${list.order_status == 1 && list.order_pay_status != 0}">
						<div id="c-mypage-status">배송 중</div>
					</c:if>
					<c:if test="${list.order_status == 2 && list.order_pay_status != 0}">
					
						<input type="hidden" name="order_status_confirm" value="${list.order_num}">
						<div id="c-mypage-status">배송 완료<form:button  class="order_status_confirm">구매 확정</form:button></div>
						
					</c:if>
									<c:if test="${list.order_status == 3 && list.order_pay_status != 0}">
						<div id="c-mypage-status">구매 확정</div>
					</c:if>
				</div>
			</c:forEach>
			<div class="align-center">${page}</div>
		</div>
	</div>
	</form:form>
</body>
</html>