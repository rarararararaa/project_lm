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
				<h2>도서 후기 작성 내역</h2>
			</div>
			<hr id="status-hr">
			<c:forEach var="list" items="${list}">
				<div class="title-list">
					<div id="t-write-detail">도서 제목</div>
					<div id="t-write-title">리뷰 내용</div>
					<div id="t-write-rating">리뷰 점수</div>
					<div id="t-write-date">리뷰 작성 날짜</div>
				</div>
				<hr id="title-list-hr">
				<div id="item-list2">
					<div id="c-write-detail">${list.store_product_title}</div>
					<div id="c-write-title">${list.review_content}</div>
					<div id="c-write-rating">${list.review_rating}</div>
					<div id="c-write-date">${list.review_reg_date}</div>
				</div>
				<div class="align-center">${page}</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>