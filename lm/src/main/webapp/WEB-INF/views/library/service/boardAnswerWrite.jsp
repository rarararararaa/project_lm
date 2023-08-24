<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 사용자 1:1문의 답변등록 시작 -->
<div class="page-main">
	<div class="box">
		<div class="title">" 회원번호(${boardAsk.ask_num})님의 1:1문의내역 "</div>
		<div class="content-box-U">
			<div class="content-detail">
				<div class="detail-title">제목</div>
				<div class="specific">${boardAsk.ask_title}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">내용</div>
				<div class="specific">${boardAsk.ask_content}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">작성일</div>
				<div class="specific">${boardAsk.ask_reg_date}</div>
			</div>
		</div>

		<div class="box">
			<div class="title">" 관리자 답변 등록"</div>
			<form:form modelAttribute="boardAnswerVO"
				action="boardAnswerWrite.do" id="register_form"
				class="content-box-U" enctype="multipart/form-data">
				<form:errors element="div" cssClass="error-color" />
				<input type="hidden" name="ask_num" value="${boardAsk.ask_num}" />

				<div class="content-detail">
				<div class="detail-title">답변</div>
				<div class="specific">
					<form:textarea path="answer_content" />
					<form:errors path="answer_content" cssClass="error-color" />
					<script>
					function MyCustomUploadAdapterPlugin(editor){
						editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
							return new UploadAdapter(loader);
						}
					}
					
					ClassicEditor.create(document.querySelector('#answer_content'),{
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
					<form:button class="small-button-B">작성</form:button>
					<input class="small-button-U" type="button" value="목록"
						onclick="location.href='user_boardAskList.do'">
				</div>
			</form:form>
		</div>
	</div>
</div>
<!-- 사용자 1:1문의 답변등록 끝 -->




