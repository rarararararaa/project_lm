<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 1:1문의 등록 시작 -->
<div class="page-main">
	<h2>문의하기-작성</h2>
	<form:form modelAttribute="boardAskVO" action="boardAskWrite.do" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul class="form-default">
		    <li>
				<form:input path="ask_title" class="input-box" placeholder="제목을 입력하세요."/>
				<form:errors path="ask_title" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="ask_content" class="input-textarea" placeholder="내용을 입력하세요."/>
				<form:errors path="ask_content" cssClass="error-color"/>
			</li>
			<li>	
				<label for="upload1">사진</label>
				<input type="file" name="upload1" id="upload1"
				              accept="image/gif,image/png,image/jpeg">
			    <form:errors path="ask_image" cssClass="error-color"/>	              
			</li>
		</ul>
		<div class="align-center">
			<form:button>작성</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>
<!-- 게시판 글 등록 끝 -->




