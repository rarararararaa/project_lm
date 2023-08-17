<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- (관리자) 1:1문의 답변 수정 시작 -->
<div class="page-main">
	<h2>(관리자) 답변수정</h2>
	<form:form modelAttribute="boardAnswerVO" action="boardAnswerUpdate.do" id="modify_form" enctype="multipart/form-data">
		<form:hidden path="answer_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul class="form-default">
			<li>
				<form:textarea path="answer_content" class="input-textarea" placeholder="내용을 입력하세요."/>
				<form:errors path="answer_content" cssClass="error-color"/>
			</li>
			<li>	
				<label for="upload1">사진</label>
				<input type="file" name="upload1" id="upload1"
				              accept="image/gif,image/png,image/jpeg">
			    <form:errors path="answer_image" cssClass="error-color"/>	              
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
			<input type="button" value="목록" onclick="location.href='boardAskList.do'">
		</div>	                               
	</form:form>
</div>
<!-- (관리자) 1:1문의 답변 수정 끝 -->

