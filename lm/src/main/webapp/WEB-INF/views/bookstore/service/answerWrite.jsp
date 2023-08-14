<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(function(){
	$('#ask_image').hide();
	
	$('#image_controller').on('click',function(){
		$('#ask_image').toggle();
		$('#image_controller').text('이미지 닫기');
	})
});
</script>

<!-- 1:1문의 등록 시작 -->
<div class="page-main">
	<h2>문의답변 작성</h2>
	
	<h3>문의 내용</h3>
	<ul>
		<li>${ask.ask_category}</li>
		<li>제목 : ${ask.ask_title}</li>
		<li>내용 : ${ask.ask_content}</li>
		<li>작성일 : ${ask.ask_reg_date}</li>
		<li><span id="image_controller"><B>이미지 열기</B></span><br><img id="ask_image" src="askImageView.do?ask_num=${ask.ask_num}"></li>
	</ul>
	
	<h3>답변 작성</h3>
	<form:form modelAttribute="answerVO" action="answerWrite.do" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<form:hidden path="ask_num" value="${ask.ask_num}"/>
		<ul>
			<li>
				<form:label path="answer_content">내용</form:label>
				<form:textarea path="answer_content"/>
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
			<form:button>작성</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>
<!-- 게시판 글 등록 끝 -->




