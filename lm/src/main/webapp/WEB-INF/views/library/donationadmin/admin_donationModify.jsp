<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 기증상태 수정 시작 -->
<div class="page-main">
<div class="box">
	<div class="title">기증 상세</div>
	<form:form modelAttribute="donationVO" action="admin_donationupdate.do"
	             id="modify_form" class="content-box">
	    <form:hidden path="donation_num"/>         
	    <form:errors element="div" cssClass="error-color"/>         
		<div class="content-detail">
			<div class="detail-title">기증번호</div>
			<div class="specific">${donationVO.donation_num}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">관리자 확인</div>
			<div class="specific">
				<c:if test="${donationVO.donation_status==0}"><div style="color: orange;"><b>미확인</b></div></c:if>
				<c:if test="${donationVO.donation_status==1}"><div style="color: blue;"><b>기증수락</b></div></c:if>
				<c:if test="${donationVO.donation_status==2}"><div style="color: red;"><b>기증거부</b></div></c:if>
			</div>
			<div class="specific">
				<form:radiobutton path="donation_status" value="0"/><div style="color: orange;">미확인</div>
				<form:radiobutton path="donation_status" value="1"/><div style="color: blue;">기증수락</div>
				<form:radiobutton path="donation_status" value="2"/><div style="color: red;">기증거부</div>
				<form:button class="small-button-B">수정</form:button>
			</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">제목</div>
			<div class="specific">${donationVO.donation_title}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">등록일</div>
			<div class="specific">${donationVO.donation_reg_date}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">기증자</div>
			<div class="specific">${donationVO.donation_name}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">기증자 정보</div>
			<div class="specific">${donationVO.donation_info}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">내용</div>
			<div class="specific">${donationVO.donation_content}</div>
		</div>
		<div class="content-detail">
			<div class="detail-title">기증도서정보</div>
			<div class="specific">${donationVO.donation_book_info}</div>
		</div>
		<div class="button-box">
			<input class="small-button" type="button" value="목록"
			   onclick="location.href='admin_donationlist.do'"> 
			<input class="small-button-R" type="button" value="삭제" id="delete_btn">
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
				delete_btn.onclick = function() {
					let choice = confirm('삭제하시겠습니까?');
					if (choice) {
						location
								.replace('delete.do?donation_num=${donation.donation_num}');
					}
				};
			</script>
		</div>          
	</form:form>
	</div>
</div>
<!-- 기증글 상태수정 끝 -->







