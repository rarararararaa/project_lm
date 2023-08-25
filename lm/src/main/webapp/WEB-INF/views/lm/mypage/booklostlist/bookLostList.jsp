<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
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
				<h2>분실 도서 신고 내역</h2>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-lost-detail">분실 도서 정보</div>
				<div id="t-lost-amount">분실 도서 가격</div>
				<div id="t-lost-time">분실 신고 날짜</div>
				<div id="t-lost-status">결제 상태/수단</div>
			</div>
			<hr id="title-list-hr">
			<c:forEach var="list" items="${list}" varStatus="status">
				<div class="item-list3">
										<div class="view-photo2">
							<a
								href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?lo=${lo}&callNumber=${list.callNumber}">
								<img src="${list.lib_product_bookimageurl}" class="view-photo2">
							</a>
						</div>
					<div id="item-list-detail4">

						<div id="c-lost-title"><a
								href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?lo=${lo}&callNumber=${list.callNumber}"><strong>${list.lib_product_bookname}</strong></a></div>
						<div id="c-lost-author">${list.lib_product_authors}</div>
						<div id="c-lost-publisher">${list.lib_product_publisher}</div>
					</div>
					<div id="c-lost-price">${list.store_product_pricesales}</div>
					<div id="c-lost-time">${list.lost_reg_date}</div>
					<div id="item-list-detail3">
						<c:if test="${list.lost_payment_status==0}">
							<div id="c-lost-status">결제 취소</div>
						</c:if>
						<c:if test="${list.lost_payment_status==1}">
							<div id="c-lost-status">결제 완료</div>
						</c:if>
						<c:if test="${list.lost_payment_type==1}">
							<div id="c-lost-type">신용카드 결제</div>
						</c:if>
						<c:if test="${list.lost_payment_type==2}">
							<div id="c-lost-type">카카오페이 결제</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
			<div class="align-center">${page}</div>
		</div>
	</div>
</body>
</html>