<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 1:1문의 등록 시작 -->
<div class="page-main">
	<h2>문의하기-작성</h2>
	<form:form modelAttribute="askVO" action="askWrite.do" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul class="form-default">
			<li>
				<label for="ask_category"></label>
				<select id="ask_category" name="ask_category">
					<option value="1" >배송</option>
					<option value="2" >취소/교환</option>
					<option value="3" >포인트/쿠폰</option>
					<option value="4" >중고</option>
					<option value="5" >상품</option>
					<option value="6" >회원관리</option>
					<option value="7" >주문/결제</option>
				</select>
			</li>
		    <li>
				<form:input path="ask_title" class="input-box" placeholder="제목을 입력하세요."/>
				<form:errors path="ask_title" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="ask_content" class="input-textarea" placeholder="내용을 입력하세요."/>
				<form:errors path="ask_content" cssClass="error-color"/>
			</li>
			<li>	
				<label for="upload1">사진</label>
				<input type="file" name="upload1" id="upload1"
				              accept="image/gif,image/png,image/jpeg">
			    <form:errors path="ask_image" cssClass="error-color"/>	              
			</li>
		</ul>
		<div class="align-center">
			<form:button>작성</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>
<!-- 게시판 글 등록 끝 -->




