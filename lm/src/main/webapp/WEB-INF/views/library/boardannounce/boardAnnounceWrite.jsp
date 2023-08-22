<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 공지사항 게시판 등록 시작 -->
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
<div class="page-main">
	<div class="box">
		<div class="title">공지사항 등록</div>
		<form:form modelAttribute="boardAnnounceVO" action="write.do"
			id="register_form" class="content-box">
			<form:errors element="div" cssClass="error-color" />
			<div class="content-detail">
				<div class="detail-title">
					<form:label path="notice_title">제목</form:label>
				</div>
				<div class="specific">
					<form:input path="notice_title" />
					<form:errors path="notice_title" cssClass="error-color" />
				</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">내용</div>
				<div class="specific">
					<form:textarea path="notice_content" />
					<form:errors path="notice_content" cssClass="error-color" />
					<script>
					function MyCustomUploadAdapterPlugin(editor){
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);
						}
					}
					
					ClassicEditor.create(document.querySelector('#notice_content'),{
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
				<form:button class="small-button">전송</form:button>
				<input class="small-button" type="button" value="목록"
					onclick="location.href='list.do'">
			</div>
		</form:form>
	</div>
</div>
<!-- 게시판 글 등록 끝 -->




