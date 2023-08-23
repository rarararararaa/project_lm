<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 회원권한 수정 시작 -->
<div class="page-main">
	<div class="box">
	<div class="title">회원 상세</div>
	<form:form modelAttribute="memberVO" action="admin_update.do"
	             id="modify_form" class="content-box">
	    <form:hidden path="mem_num"/>         
	    <form:errors element="div" cssClass="error-color"/>
	    <div class="content-detail">
			<div class="detail-title">회원번호</div>
			<div class="specific">${memberVO.mem_num}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">회원권한</div>
			<div class="specific">
				<c:if test="${memberVO.mem_auth==0}"><div class="expire"><b>탈퇴</b></div></c:if>
				<c:if test="${memberVO.mem_auth==1}"><div class="stop"><b>정지</b></div></c:if>
				<c:if test="${memberVO.mem_auth==2}"><div class="sleep"><b>휴면</b></div></c:if>
				<c:if test="${memberVO.mem_auth==3}"><b>일반</b></c:if>
				<c:if test="${memberVO.mem_auth==4}"><div class="notcheck"><b>미인증</b></div></c:if>
				<c:if test="${memberVO.mem_auth==9}"><div class="answer"><b>관리자</b></div></c:if>
			</div>
			<div class="specific">
				<form:radiobutton path="mem_auth" value="0"/>
					<div class="expire"><b>탈퇴</b></div>
				<form:radiobutton path="mem_auth" value="1"/>
					<div class="stop"><b>정지</b></div>
				<form:radiobutton path="mem_auth" value="2"/>
					<div class="sleep"><b>휴면</b></div>
				<form:radiobutton path="mem_auth" value="3"/>
					<b>일반</b>
				<form:radiobutton path="mem_auth" value="4"/>
					<div class="notcheck"><b>미인증</b></div>
				<form:radiobutton path="mem_auth" value="9"/>
					<div class="answer"><b>관리자</b></div>
			</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">회원등급</div>
			<div class="specific">
				<c:if test="${memberVO.mem_grade==0}"><p class="member-grade-bronze">Bronze</p></c:if>
				<c:if test="${memberVO.mem_grade==1}"><p class="member-grade-silver">Silver</p></c:if>
				<c:if test="${memberVO.mem_grade==2}"><p class="member-grade-gold">Gold</p></c:if>
				<c:if test="${memberVO.mem_grade==3}"><p class="member-grade-platinum">Platinum</p></c:if>
				<c:if test="${memberVO.mem_grade==4}"><p class="member-grade-diamond">Diamond</p></c:if>
			</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">아이디</div>
			<div class="specific">${memberVO.mem_id}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">이름</div>
			<div class="specific">${memberVO.mem_name}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">주민등록번호</div>
			<div class="specific">${memberVO.mem_identify}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">전화번호</div>
			<div class="specific">${memberVO.mem_cell}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">이메일</div>
			<div class="specific">${memberVO.mem_email}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">보유포인트</div>
			<div class="specific">${memberVO.mem_point}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">가입일</div>
			<div class="specific">${memberVO.mem_reg_date}</div>
		</div>
		<div class="button-box">
			<form:button class="small-button-B">권한수정</form:button>
			<input class="small-button" type="button" value="회원목록"
			   onclick="location.href='admin_list.do'">
		</div>
	</form:form>
</div>
</div>
<!-- 회원권한 수정 끝 -->







