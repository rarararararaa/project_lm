<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
  				아이디123123
  				</div>
  				<div class="content-change">
  				<a href="#">회원정보변경</a>
  				</div>
  			</div>
  			<div class="sec-content">
  				<div class="sec-content-top">
  					<div class="content-grade">
  					<a href="#">등급</a>
  					</div>
  					<div class="content-reg-date">
  					가입일
  					</div>
  					<div class="content-edit-date">
  					회원정보수정일
  					</div>
  				</div>
  				<div class="content-remain-grade">
  				<a href="#">다음 등급까지 n점 남았습니다.</a>
  				</div>
  			</div>
  			<div class="thi-content">
  				<div class="content-point">
  				<a href="#">사용 가능 포인트 : n 포인트</a>
  				</div>
  			</div>
  		</div>
  		<div class="top-right-right-div">
  			<div class="sub-button">
  				<a href="#" class="sub-title">찜한 도서</a>
  				<strong id="sub-content"><a href="#">내용</strong>
  			</div>
  			<div class="sub-button">
  				<a href="#" class="sub-title">상품 후기</a>
  				<strong id="sub-content"><a href="#">내용</strong>
  			</div>
  			<div class="sub-button">
  				<a href="#" class="sub-title">독후감 작성</a>
  				<strong id="sub-content"><a href="#">내용</strong>
  			</div>
  			<div class="sub-button">
	  			<a href="#" class="sub-title">보유 쿠폰</a>
	  			<strong id="sub-content"><a href="#">내용</strong>
  			</div>
  		</div>
  	</div>
  </div>
  <hr>
  <div class="container">
  	
    <div class="left-div">

      <div class="page-name">내 정보</div>
      <ul class="menu-box">
        <li><a class="detail-menu" href="">주문 내역</a></li>
        <li><a class="detail-menu" href="">문의 내역</a></li>
        <li><a class="detail-menu" href="">대출/반납 내역</a></li>
        <li><a class="detail-menu" href="">희망도서 신청 내역</a></li>
        <li><a class="detail-menu" href="">프로그램 신청 내역</a></li>
        <li><a class="detail-menu" href="">책 기증 신청 내역</a></li>
        <li><a class="detail-menu" href="">시설 이용 신청 내역</a></li>
        <li><a class="detail-menu" href="">책 예약 내역</a></li>
        <li><a class="detail-menu" href="">분실 도서 신고 내역</a></li>
        <li><a class="detail-menu" href="">이벤트 참여 내역</a></li>
        <li><a class="detail-menu" href="">이벤트 당첨 내역</a></li>
        <li><a class="detail-menu" href="">중고 상품 등록 내역</a></li>
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

        <c:forEach var="zzim" items="${list}">
          <ul class="list">
            <li class="list-photo1"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}"><img src="${pageContext.request.contextPath}/upload/${zzim.product_photo1}"></a></li>
            <li class="list-list"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}">${zzim.product_title}</a></li>
            <li class="list-price">${zzim.product_price}원 (${zzim.product_quantity}개)</li>
            <li class="list-date">${zzim.order_date}</li>
              <c:if test="${zzim.order_status==0}"><li class="list-status-1">주문 완료</li></c:if>
              <c:if test="${zzim.order_status==1}"><li class="list-status-2">배송중</li></c:if>
              <c:if test="${zzim.order_status==2}"><li class="list-status-3">배송 완료</li></c:if>
          </ul>
        </c:forEach>
      <div class="align-center">${page}</div>
    </div>
  </div>
</div>
</body>
</html>