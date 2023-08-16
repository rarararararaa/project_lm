<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- include libraries (jquery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.ck.ck-editor{
	display: inline-block;
	width: 650px;
}
.ck-editor__editable_inline{
	min-height: 250px;
}
</style>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pateContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pateContext.request.contextPath}/js/uploadAdapter.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/BsEventAnnounce.css">

<!-- 게시판 글 수정 시작 -->
<div class="event-announce-page">
	<div class="eventannounce-div">
	<h2>당첨자 발표 페이지 글 작성</h2>
	<form:form modelAttribute="eventAnnounceBoardVO" action="eventAnnounceWrite.do" id="register_form">
	<form:hidden path="event_announce_board_num"/>
	<ul>
		<li>
			<form:label path="title">제목</form:label>
			<form:input path="title"/>
			<!--<form:errors path="title" cssClass="error-color"/>-->
		</li>
		<li>
			<form:label path="content">내용</form:label>
			<form:textarea path="content"/>
			<form:errors path="content" cssClass="error-color"/>
			<script>
				function MyCustomUploadAdapterPlugin(editor){
					editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
						return new UploadAdapter(loader);
					}
				}
					
				ClassicEditor.create(document.querySelector('#content'),{
								extraPlugins:[MyCustomUploadAdapterPlugin]
							 })
							 .then(editor => {
								 window.editor = editor;								 })
							 .catch(error => {
								 console.error(error);
							 });
			</script>
		</li>
	</ul>
	<div class="align-center">
		<form:button>전송</form:button>
		<input type="button" value="목록" onclick="location.href='eventAnnounceList.do'">
	</div>
	</form:form>
	</div>
</div>
<!-- 게시판 글 수정 끝 -->   
