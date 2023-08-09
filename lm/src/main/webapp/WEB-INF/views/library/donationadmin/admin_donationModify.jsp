<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 기증상태 수정 시작 -->
<div class="page-main">
	<h2>기증상태 수정</h2>
	<form:form modelAttribute="donationVO" action="admin_donationupdate.do"
	             id="modify_form">
	    <form:hidden path="donation_num"/>         
	    <form:errors element="div" cssClass="error-color"/>         
		<ul>
			<li>
				<label>제목</label>
				${donationVO.donation_title}
			</li>
			<li>
				<label>등록날짜</label>
				${donationVO.donation_reg_date}
			</li>
		</ul>
		<hr size="1" width="100%">
		<ul>	
			<li>
				<label>기증자 이름</label>
				${donationVO.donation_name}      
			</li>
			<li>
				<label>기증자 정보</label>
				${donationVO.donation_info}      
			</li>
			<li>
				<label>내용</label>
				${donationVO.donation_content}      
			</li>
			<li>
				<label>기증도서정보</label>
				${donationVO.donation_book_info}    
			</li>
		</ul>
		<hr size="1" width="100%">
		<ul>
			<li>
				<label>(관리자 확인용)상태수정 </label>
				<form:radiobutton path="donation_status" value="0"/>확인전
				<form:radiobutton path="donation_status" value="1"/>기증수락
				<form:radiobutton path="donation_status" value="2"/>기증거부
				<form:button>상태수정</form:button>
			</li>
		</ul>
		<hr size="1" width="100%">
		
		<div class="align-center">
			<input type="button" value="기증목록"
			   onclick="location.href='admin_donationlist.do'"> 
			<input type="button" value="삭제" id="delete_btn">
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
<!-- 기증글 상태수정 끝 -->







