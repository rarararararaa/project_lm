<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<!-- 파라미터 세팅 -->
	<% pageContext.setAttribute("lo",request.getParameter("lo")); %>
	<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
	<input type="hidden" name="lo" value="${lo}"/>
  <div class="container">
    <div class="left-div">
      <div class="page-name">내 정보</div>
      <ul class="menu-box">
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=${lo}">주문 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/asklist/askListMain.do?lo=${lo}">문의 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do?lo=${lo}">대출/반납 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/wantbooklist/wantBookListMain.do?lo=${lo}">희망도서 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/programapplylist/programApplyListMain.do?lo=${lo}">프로그램 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/facilityapplylist/facilityApplyListMain.do?lo=${lo}">시설 이용 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/bookreservationlist/bookReservationListMain.do?lo=${lo}">도서 예약 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/booklostlist/bookLostListMain.do?lo=${lo}">분실 도서 신고 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/eventparticipatelist/eventParticipateListMain.do?lo=${lo}">이벤트 참여 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/usedbookapplylist/usedBookApplyListMain.do?lo=${lo}">중고 도서 등록 내역</a></li>
      </ul>
    </div>
    <div class="right-div">
      <div class="list-name">
        <h2> 주문 내역</h2>
      </div>
      	<div class="status">
      		<div class="status-info" id="step1">입금/결제 완료</div>
      		<div class="status-info" id="step2">배송중</div>
      		<div class="status-info" id="step3">배송완료</div>
      	</div>
      	<hr id="status-hr">
        <div class="title-list">
           <div id="title-list1">주문 번호</div>
           <div id="title-list2">상품정보</div>
           <div id="title-list3">주문 금액(수량)</div>
           <div id="title-list4">주문 일자</div>
           <div id="title-list5">주문 상태</div>
        </div>
        <hr id="title-list-hr">
        <div class="item-list">
           <div class=""></div>
        </div>
      <div class="align-center"></div>
    </div>
  </div>
</body>
</html>