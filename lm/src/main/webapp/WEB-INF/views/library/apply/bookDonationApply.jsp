<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main">
	<form:form modelAttribute="donationVO" action="donationApply.do" id="register_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<form:input path="donation_book_info" class="input-box" placeholder="도서 정보를 입력해 주세요."/>
				<form:errors path="donation_book_info" cssClass="error-color"/>
			</li>
			<li>
				<form:input path="donation_title" class="input-box" placeholder="제목을 입력해 주세요."/>
				<form:errors path="donation_title" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="donation_content" class="input-textarea" placeholder="내용을 입력해 주세요."/>
				<form:errors path="donation_content" cssClass="error-color"/>
			</li>
			<li>
				<form:input path="donation_name" class="input-box" placeholder="기증자 이름을 입력해 주세요."/>
				<form:errors path="donation_name" cssClass="error-color"/>
			</li>
			<li>
				<form:input path="donation_info" class="input-box" placeholder="기증자 별명(이름 대신 공개되기 원하는 호칭이 있으면 적어주세요)"/>
				<form:errors path="donation_info" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>