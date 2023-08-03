<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 파라미터 세팅 -->
	<% pageContext.setAttribute("lo",request.getParameter("lo")); %>
	<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
	<input type="hidden" name="lo" value="${lo}"/>
<div class="whole">
  <div class="top-div">
  	<div class="top-left-div">
  		<div class="photo">사진
  		</div>
  	</div>
  	
  	<div class="top-right-div">
  		<div class="top-right-left-div">
  			<div class="fir-content">
  				<div class="content-id">
  				${mem_id}
  				</div>
  				<div class="content-change">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">회원정보변경</a>
  				</div>
  			</div>
  			<div class="sec-content">
  				<div class="sec-content-top">
  					<div class="content-grade">
  					<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">등급 : ${mem_grade}</a>
  					</div>
  					<div class="content-reg-date">
  					가입일<br>${mem_reg_date}
  					</div>
  					<div class="content-edit-date">
  					회원정보 수정일<br>${mem_modify_date}
  					</div>
  				</div>
  				<div class="content-remain-grade">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">다음 등급까지 ${mem_grade_remain}</a>
  				</div>
  			</div>
  			<div class="thi-content">
  				<div class="content-point">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">사용 가능 포인트 : ${mem_point} 포인트</a>
  				</div>
  			</div>
  		</div>
  		<div class="top-right-right-div">
  			<div class="sub-button">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}" class="sub-title">찜한 도서</a>
  				<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">${zzim_num_count}개</a></strong>
  			</div>
  			<div class="sub-button">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}" class="sub-title">도서 후기</a>
  				<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">${review_num_count}개</a></strong>
  			</div>
  			<div class="sub-button">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}" class="sub-title">독후감 작성</a>
  				<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">${rep_num_count}개</a></strong>
  			</div>
  			<div class="sub-button">
	  			<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}" class="sub-title">보유 쿠폰</a>
	  			<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">${coupon_num_count}개</a></strong>
  			</div>
  		</div>
  	</div>
  </div>
  <hr>
  <div class="container">
  	
    <div class="left-div">

      <div class="page-name">내 정보</div>
      <ul class="menu-box">
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">주문 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">문의 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">대출/반납 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">희망도서 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">프로그램 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">책 기증 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">시설 이용 신청 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">책 예약 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">분실 도서 신고 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">이벤트 참여 내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">중고 도서 등록 내역</a></li>
      </ul>
    </div>

    <div class="right-div">
      <div class="list-name">
        <h2> 주문 내역</h2>
      </div>
        <div class="list">
          <div class="list-num">주문 번호 : ${order_num}</div>
          <div class="list-list">상품명</div>
          <div class="list-price">총 가격 (수량)</div>
          <div class="list-date">주문 날짜</div>
          <div class="list-status">배송 상태</div>
        </div>
      <div class="align-center">${page}</div>
    </div>
  </div>
</div>
</body>
</html>