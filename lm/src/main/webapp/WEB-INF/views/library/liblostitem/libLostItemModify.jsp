<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 분실물 수정 시작 -->
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
		<div class="title">분실물 수정</div>
		<form:form modelAttribute="libLostItemVO" action="update.do"
			id="modify_form" class="content-box">
			<form:hidden path="item_num" />
			<form:errors element="div" cssClass="error-color" />
			<div class="content-detail">
				<div class="detail-title">
					<form:label path="item_title">제목</form:label>
				</div>
				<div class="specific">
					<form:input path="item_title" />
					<form:errors path="item_title" cssClass="error-color" />
				</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">내용</div>
				<div class="specific">
					<form:textarea path="item_content" />
					<form:errors path="item_content" cssClass="error-color" />
					<script>
				function MyCustomUploadAdapterPlugin(editor){
					editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
						return new UploadAdapter(loader);
					}
				}
				
				ClassicEditor.create(document.querySelector('#item_content'),{
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
				<form:button class="small-button-B">수정</form:button>
				<input class="small-button" type="button" value="목록"
					onclick="location.href='list.do'">
			</div>
		</form:form>
	</div>
</div>
<!-- 분실물 수정 끝 -->




