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
				<h2>프로그램 신청 내역</h2>
			</div>
			<div class="status">
				<div class="status-info" id="step1">미확인</div>
				<div class="status-info" id="step2">확인 완료</div>
				<div class="status-info" id="step3">등록 완료</div>
			</div>
			<hr id="status-hr">
			<div class="title-list">
				<div id="t-program-num">신청 번호</div>
				<div id="t-program-title">프로그램명(신청 내용)</div>
				<div id="t-program-date">신청 날짜</div>
				<div id="t-program-time">프로그램 시간</div>
			</div>
			<hr id="title-list-hr">
			<c:forEach var="list" items="${list}" varStatus="status">
				<div class="item-list">
					<div id="c-program-num">${list.program_num}</div>
					<div id="c-program-title">${list.program_title}</div>
					<div id="c-program-date">${list.program_reg_date}</div>
					<div id="c-program-time">${list.program_date}</div>
					<input type="button" id="button-more-info-${status.count}"
						class="button-more-info" onclick="ShowContent(${status.count})">
					<input type="button" id="button-more-info-close-${status.count}"
						class="button-more-info-close"
						onclick="HideContent(${status.count})">
				</div>
				<!-- 버튼 클릭 시 활성화 시작 -->
				<div id="show-content-${status.count}" class="show-detail" style="width:900px; margin-bottom:50px;">ㄴ ${list.program_content}</div>
				<!-- 버튼 클릭 시 활성화 끝 -->
			</c:forEach>
			<div class="align-center">${page}</div>
		</div>
	</div>
</body>
</html>