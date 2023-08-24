<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main">
	<div class="box">
		<div class="title">신규 시설등록</div>
		<form:form modelAttribute="facilityVO" action="insertFacility.do"
			id="register_form" enctype="multipart/form-data" class="content-box">
			<form:errors element="div" cssClass="error-color" />
			<div class="content-detail">
				<div class="detail-title">
					<form:label path="facility_name">시설이름</form:label>
				</div>
				<div class="specific">
					<form:input path="facility_name" class="input-box"
						placeholder="시설이름을 입력하세요." />
					<form:errors path="facility_name" cssClass="error-color" />
				</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">
					<form:label path="facility_content">시설설명</form:label>
				</div>
				<div class="specific">
					<form:input path="facility_content" class="input-textarea"
						placeholder="시설설명을 입력하세요." />
					<form:errors path="facility_content" cssClass="error-color" />
				</div>
			</div>
			<ul>
				<li><label for="upload1">시설 사진</label> <input type="file"
					name="upload1" id="upload1" accept="image/gif,image/png,image/jpeg">
					<form:errors path="facility_image" cssClass="error-color" /></li>
			</ul>
			<div class="button-box">
				<form:button class="small-button-B">등록</form:button>
				<input class="small-button" type="button" value="목록"
					onclick='location.href="facilityAdminList.do"'>
			</div>
		</form:form>
	</div>
</div>