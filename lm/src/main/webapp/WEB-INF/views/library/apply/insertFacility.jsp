<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main">
	<form:form modelAttribute="facilityVO" action="insertFacility.do" id="register_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<form:label path="facility_name">시설 이름</form:label>
				<form:input path="facility_name"/>
				<form:errors path="facility_name" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="facility_name">시설 설명</form:label>
				<form:textarea path="facility_content"/>
				<form:errors path="facility_content" cssClass="error-color"/>
			</li>
			<li>	
				<label for="upload1">시설 사진</label>
				<input type="file" name="upload1" id="upload1"
				              accept="image/gif,image/png,image/jpeg">
			    <form:errors path="facility_image" cssClass="error-color"/>	              
			</li>
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>