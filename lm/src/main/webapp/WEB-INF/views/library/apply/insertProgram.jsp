<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- include libraries (jquery,bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<div class="page-main">
	<form:form modelAttribute="programVO" action="insertProgram.do" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<form:label path="program_title">프로그램명</form:label>
				<form:input path="program_title"/>
				<form:errors path="program_title" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="program_content">프로그램 설명</form:label>
				<form:textarea path="program_content"/>
				<form:errors path="program_content" cssClass="error-color"/>
				<script>
					function MyCustomUploadAdapterPlugin(editor){
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);
						}
					}
					
					ClassicEditor.create(document.querySelector('#program_content'),{
						           extraPlugins:[MyCustomUploadAdapterPlugin]
					             })
					             .then(editor => {
					            	 window.editor = editor;
					             })
					             .catch(error => {
					            	 console.error(error);
					             });
				</script>
			</li>
		
			<li>
			시작 날짜:
			<select name="startYear" id="yearSelect">
				<c:forEach var="i" begin="2023" end="2033">
				<option value="${i}">${i}년</option>
				</c:forEach>
			</select>
			<select name="startMonth" id="monthSelect">
				<c:forEach var="i" begin="1" end="12">
				<option value="${i}">${i}월</option>
				</c:forEach>
			</select>
			<select name="startDate" id="dateSelect">
				<c:forEach var="i" begin="1" end="31">
				<option value="${i}">${i}일</option>
				</c:forEach>
			</select>
			</li>
			<li>
			종료 날짜:
			<select name="endYear" id="yearSelect">
				<c:forEach var="i" begin="2023" end="2033">
				<option value="${i}">${i}년</option>
				</c:forEach>
			</select>
			<select name="endMonth" id="monthSelect">
				<c:forEach var="i" begin="1" end="12">
				<option value="${i}">${i}월</option>
				</c:forEach>
			</select>
			<select name="endDate" id="dateSelect">
				<c:forEach var="i" begin="1" end="31">
				<option value="${i}">${i}일</option>
				</c:forEach>
			</select>
			</li>
			<li>
			진행 시간:
			<select name="startTime" id="timeSelect">
				<c:forEach var="i" begin="1" end="24">
				<option value="${i}">${i}시</option>
				</c:forEach>
			</select>
			~
			<select name="endTime" id="timeSelect">
				<c:forEach var="i" begin="1" end="24">
				<option value="${i}">${i}시</option>
				</c:forEach>
			</select>
			</li>
			<li>
				<form:label path="program_admit">정원</form:label>
				<form:input path="program_admit"/>
				<form:errors path="program_admit" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록">
		</div>                        
	</form:form>
</div>