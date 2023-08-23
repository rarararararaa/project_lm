<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/libBasicPage_style.css">
<!-- include libraries (jquery,bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.ck-editor__editable_inline {
	min-height: 250px;
}
</style>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include ckeditor js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<!-- 1:1문의 등록 시작 -->
<div class="page-main">
	<div class="box">
	<div class="title">1:1문의글 작성</div>
	<form:form modelAttribute="boardAskVO" action="boardAskWrite.do" id="register_form" class="content-box">
		<form:errors element="div" cssClass="error-color"/>
		<div class="content-detail">
				<div class="detail-title">
					<form:label path="ask_title">제목</form:label>
				</div>
				<div class="specific">
					<form:input path="ask_title" class="input-box" placeholder="제목을 입력하세요."/>
					<form:errors path="ask_title" cssClass="error-color" />
				</div>
		</div>
		<div class="content-detail">
				<div class="detail-title">내용</div>
				<div class="specific">
					<form:textarea path="ask_content" class="input-textarea" placeholder="내용을 입력하세요." />
					<form:errors path="ask_content" cssClass="error-color" />
					<script>
					function MyCustomUploadAdapterPlugin(editor){
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);
						}
					}
					
					ClassicEditor.create(document.querySelector('#ask_content'),{
						           extraPlugins:[MyCustomUploadAdapterPlugin]
					             })
					             .then(editor => {
					            	 window.editor = editor;
					             })
					             .catch(error => {
					            	 console.error(error);
					             });
				</script>
				</div>
		</div>
		<div class="button-box">
			<form:button class="small-button">작성</form:button>
			<input class="small-button" type="button" value="목록"
				onclick="location.href='user_boardAskList.do'">
		</div>	                               
	</form:form>
	</div>
</div>
<!-- 1:1문의 등록 등록 끝 -->




