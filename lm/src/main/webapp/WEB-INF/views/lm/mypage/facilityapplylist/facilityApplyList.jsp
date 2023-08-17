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
				<h2>시설 이용 신청 내역</h2>
			</div>
			<div class="status">
				<div class="status-info" id="step1">미확인</div>
				<div class="status-info" id="step2">확인 완료</div>
				<div class="status-info" id="step3">등록 완료</div>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-facility-detail">시설 정보</div>
				<div id="t-facility-time">사용 시작 시간</div>
				<div id="t-facility-time2">사용 종료 시간</div>
				<div id="t-facility-date">신청 날짜</div>
			</div>
			<hr id="title-list-hr">
			<c:forEach var="list" items="${list}" varStatus="status">
				<div class="item-list3">
					<div class="view-photo2">
						<a
							href="${pageContext.request.contextPath}/library/facilityList.do?lo=${lo}"><img
							src="${pageContext.request.contextPath}/lm/mypage/facilityapplylist/facilityView.do?facility_num=${list.facility_num}"
							class="view-photo2"></a>
					</div>
					<div id="item-list-detail">
						<div id="c-facility-title">
							<strong><a
								href="${pageContext.request.contextPath}/library/facilityList.do?lo=${lo}">${list.facility_name}</a></strong>
						</div>
						<div id="c-facility-content">${list.facility_content}</div>
					</div>
					<div id="c-facility-time">${list.facility_apply_start}</div>
					<div id="c-facility-time2">${list.facility_apply_end}</div>
					<div id="c-facility-date">${list.facility_apply_reg_date}</div>
				</div>
			</c:forEach>
			<div class="align-center">${page}</div>
		</div>
	</div>
</body>
</html>