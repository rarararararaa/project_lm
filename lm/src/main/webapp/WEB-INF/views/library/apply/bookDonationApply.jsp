<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main">
	<form:form modelAttribute="donationVO" action="donationApply.do" id="register_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<form:label path="donation_book_info">도서 정보</form:label>
				<form:input path="donation_book_info"/>
				<form:errors path="donation_book_info" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="donation_title">제목</form:label>
				<form:input path="donation_title"/>
				<form:errors path="donation_title" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="donation_content">내용</form:label>
				<form:textarea path="donation_content"/>
				<form:errors path="donation_content" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="donation_name">기증자 이름</form:label>
				<form:input path="donation_name"/>
				<form:errors path="donation_name" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="donation_info">기증자 별명(이름 대신 공개되기 원하는 호칭이 있으면 적어주세요)</form:label>
				<form:input path="donation_info"/>
				<form:errors path="donation_info" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>