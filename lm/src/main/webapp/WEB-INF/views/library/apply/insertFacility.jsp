<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main">
	<form:form modelAttribute="facilityVO" action="insertFacility.do" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<form:input path="facility_name" class="input-box" placeholder="시설이름을 입력하세요."/>
				<form:errors path="facility_name" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="facility_content" class="input-textarea" placeholder="시설설명을 입력하세요."/>
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