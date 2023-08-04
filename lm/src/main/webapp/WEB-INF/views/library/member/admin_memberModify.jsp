<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 회원권한 수정 시작 -->
<div class="page-main">
	<h2>회원 권한 수정</h2>
	<form:form modelAttribute="memberVO" action="admin_update.do"
	             id="modify_form">
	    <form:hidden path="mem_num"/>         
	    <form:errors element="div" cssClass="error-color"/>         
		<ul>
			<li>
				<label>회원권한</label>
				<c:if test="${memberVO.mem_auth < 9}">
				<form:radiobutton path="mem_auth" value="0"/>탈퇴
				<form:radiobutton path="mem_auth" value="1"/>정지
				<form:radiobutton path="mem_auth" value="2"/>휴면
				<form:radiobutton path="mem_auth" value="3"/>일반
				<form:radiobutton path="mem_auth" value="4"/>미인증
				</c:if>      
				<c:if test="${memberVO.mem_auth == 9}">관리</c:if>
			</li>
		</ul>
		<ul>	
			<li>
				<label>이름</label>
				${memberVO.mem_name}    
			</li>
			<li>
				<label>주민등록번호</label>
				${memberVO.mem_identify}      
			</li>
			<li>
				<label>전화번호</label>
				${memberVO.mem_cell}      
			</li>
			<li>
				<label>이메일</label>
				${memberVO.mem_email}      
			</li>
			<li>
				<label>보유 포인트</label>
				${memberVO.mem_point}      
			</li>
			<li>
				<label>가입일</label>
				${memberVO.mem_reg_date} 
			</li>
		</ul>
		<div class="align-center">
			<c:if test="${memberVO.mem_auth != 9}">
			<form:button>권한수정</form:button>
			</c:if>
			<input type="button" value="회원목록"
			   onclick="location.href='admin_list.do'">
		</div>          
	</form:form>
</div>
<!-- 회원권한 수정 끝 -->







