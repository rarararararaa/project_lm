<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/BsEventStyle.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">
<script type="text/javascript" src="${pateContext.request.contextPath}/js/bs.Event.Form.js"></script>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pateContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pateContext.request.contextPath}/js/uploadAdapter.js"></script>
<!-- 이벤트 글 수정 시작! -->

<div class="event-form-main">
	<form:form modelAttribute="bsEventVO" action="update.do" id="modify_form" class="event-form"
	                          enctype="multipart/form-data">
	    <form:hidden path="event_board_num"/>                        
		<div class="event_form_div1">
			<ul>
				<li>
				<ul class="li-left">
					<li class="box2-left">
						<form:label path="event_board_status">이벤트 상태</form:label>
						<form:select path="event_board_status">
							<form:option value="1">진행전</form:option>
							<form:option value="2">진행중</form:option>
							<form:option value="3">이벤트종료</form:option>
						</form:select>
					</li>
					<li>
						<form:label path="event_board_category">이벤트 종류</form:label> 
						<form:select path="event_board_category">
							<form:option value="1">댓글</form:option>
							<form:option value="2">퀴즈</form:option>
							<form:option value="3">사은품</form:option>
						</form:select>
					</li>
				</ul>
				</li>
				<li>
					<form:label path="store_product_num" >연관 상품</form:label> 
					<form:input path="store_product_num"/>
				</li>
			</ul>
		</div>
		<hr size="1" noshade>
		<div class="event_form_div2">
			<ul>
				<li>
					<form:label path="event_title">제목</form:label>
					<form:input path="event_title" class="box1-right"/>
					<form:errors path="event_title" cssClass="error-color"/>
				</li>
				
				<li>
					<form:label path="event_short_content">짧은 소개</form:label>
					<form:textarea path="event_short_content" class="box1-right"/>
				</li>
				<li>
					<label for="uploadSmall">이벤트 배너</label>
					<input type="file" name="uploadSmall" id="uploadSmall" accept="image/gif, image/png, image/jpeg"/>
					<form:errors path="event_img_small" cssClass="error-color"/>
				</li>
				<li>
					<label for="uploadBig">이벤트 이미지</label>
					<input type="file" name="uploadBig" id="uploadBig" accept="image/gif, image/png, image/jpeg"/>
					<c:if test="${event.event_img_big.length > 0}">첨부피일 있음</c:if>
					<form:errors path="event_img_big" cssClass="error-color"/>
				</li>
			</ul>
		</div>
		<hr size="1" noshade>
		<div class="event_form_div3">
			<ul>
				<li>
				<ul class="li-left">
					<li class="box2-left">
						<form:label path="event_date_start">시작일</form:label>
						<form:input type="text" path="event_date_start"/>
					</li>
					<li>
					
						<form:label path="event_date_end">종료일</form:label>
						<form:input type="text" path="event_date_end" min="${event.event_date_start}"/>
					</li>
				</ul>
				</li>
				<li class="box1">
					<form:label path="event_content">내용</form:label>
					<form:textarea path="event_content" class="box1-right"/>
					<script>
					console.log(${event.event_date_start});
						function MyCustomUploadAdapterPlugin(editor){
							editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
								return new UploadAdapter(loader);
							}	
						}
					
						ClassicEditor.create(document.querySelector('#event_content'),{
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
			</ul>
		</div>
		<hr size="1" noshade>
		<div class="event_form_div4" id="event_form_div4" <c:if test="${event.event_board_category != 2}">style="display: none;"</c:if>>
			<ul>
				<li>
				<ul class="li-left">
					<li class="box2-left">
						<form:label path="event_quiz_sel1">문제 1</form:label>
						<form:input path="event_quiz_sel1"/>
					</li>
					<li >
						<form:label path="event_quiz_sel2">문제 2</form:label>
						<form:input path="event_quiz_sel2"/>
					</li>
				</ul>
				</li>
				<li>
					<form:label path="event_quiz_an">정답</form:label>
					<form:input path="event_quiz_an"/>
				</li>
			</ul>
		</div>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록" onclick="location.href='admin_list.do'">
		</div>	 
	</form:form>
</div>
<!-- 이벤트 글 등록 끝 -->