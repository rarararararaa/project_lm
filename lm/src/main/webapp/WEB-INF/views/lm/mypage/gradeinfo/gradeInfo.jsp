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
	<% pageContext.setAttribute("lo",request.getParameter("lo")); %>
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
			<h2>등급 정보</h2>
			<div class="list-first">
				<div class="photo">
					<img
						src="${pageContext.request.contextPath}/lm/mypage/myedit/photoView.do?mem_num=${mem_num}"
						class="view-photo">
				</div>
				<div class="list-first-data">
					<div class="list-first-data-detail">LV.${mem_grade}
						${mem_grade_name}</div>
					<div class="list-first-data-detail">누적 구매 금액 :
						${mem_order_price_str}</div>
					<div class="list-first-data-detail">현재 등급 점수 :
						${mem_grade_point} 점</div>
					<div class="list-first-data-detail">누적 구매 금액은 실시간 반영이 아닌, 구매
						확정을 기준으로 집계됩니다.</div>
				</div>
			</div>
			<h2>회원 등급 혜택</h2>
			<div class="list-second">
				<div class="list-second-first">
					<div class="lsf">등급 점수</div>
					<div class="lsf">LV.0 Bronze</div>
					<div class="lsf">LV.1 Silver</div>
					<div class="lsf">LV.2 Gold</div>
					<div class="lsf">LV.3 Platinum</div>
					<div class="lsf">LV.4 Diamond</div>
				</div>
				<div class="list-second-second">
					<div class="lsf">구매 금액</div>
					<div class="lsf">
						0원 이상<br>100,000원 미만
					</div>
					<div class="lsf">
						100,000원 이상<br>500,000원 미만
					</div>
					<div class="lsf">
						500,000원 이상<br>1,500,000원 미만
					</div>
					<div class="lsf">
						1,500,000원 이상<br>3,000,000원 미만
					</div>
					<div class="lsf">3,000,000원 이상</div>
				</div>
				<div class="list-second-third">
					<div class="lsf">등급 적립</div>
					<div class="lsf">구매 금액의 0.5%</div>
					<div class="lsf">구매 금액의 1.0%</div>
					<div class="lsf">구매 금액의 1.5%</div>
					<div class="lsf">구매 금액의 2.0%</div>
					<div class="lsf">구매 금액의 3.0%</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>