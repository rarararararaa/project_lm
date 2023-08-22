<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/showDetail.js"></script>
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
				<h2>도서 예약 내역</h2>
			</div>
			<div class="status">
				<div class="status-info" id="step1">미확인</div>
				<div class="status-info" id="step2">확인 완료</div>
				<div class="status-info" id="step3">등록 완료</div>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-reservation-detail">도서 정보</div>
				<div id="t-reservation-time">예약 신청 시간</div>
				<div id="t-reservation-status">예약 상태</div>
			</div>
			<hr id="title-list-hr">
			<c:forEach var="list" items="${list}" varStatus="status">
				<div class="item-list3">
					<div class="view-photo2"><a
					href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?lo=${lo}&callNumber=${list.callNumber}">
						<img src="${list.lib_product_bookimageurl}" class="view-photo2"></a>
					</div>
					<div id="item-list-detail2">
						<div id="c-reservation-title"><Strong>도서명</Strong> : <a 
					href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?lo=${lo}&callNumber=${list.callNumber}">${list.lib_product_bookname}</a></div>
						<div id="c-reservation-authors"><Strong>작가</Strong> : ${list.lib_product_authors}</div>
						<div id="c-reservation-publisher"><Strong>출판사</Strong> : ${list.lib_product_publisher}</div>
					</div>
					<div id="c-reservation-date">${list.reservation_submit_date}</div>
					<c:if test="${list.reservation_status == 0}">
						<div id="c-reservation-status">예약 대기</div>
					</c:if>
					<c:if test="${list.reservation_status == 1}">
						<div id="c-reservation-status">예약 확정</div>
						<input type="button" value="대출" 
								onclick="location.href='${pageContext.request.contextPath}/library/rent/rentReservationBook.do?lo=${lo}&reservation_num=${list.reservation_num}'">
						<input type="button" value="취소" 
								onclick="location.href='${pageContext.request.contextPath}/library/rent/cancelReservationBook.do?lo=${lo}&reservation_num=${list.reservation_num}'">
						
					</c:if>
					<c:if test="${list.reservation_status == 2}">
						<div id="c-reservation-status">대출 완료</div>
					</c:if>
					<c:if test="${list.reservation_status == 3}">
						<div id="c-reservation-status">취소</div>
					</c:if>
				</div>
			</c:forEach>
			<div class="align-center">${page}</div>
		</div>
	</div>
</body>
</html>