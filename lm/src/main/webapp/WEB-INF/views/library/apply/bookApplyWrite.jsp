<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
$(function(){
	if(${month}>=5||${year}>=15){
		$('#submit').attr("disabled",true);
	}
});
</script>
<div class="page-main">
	<h2>희망도서신청</h2>
	<form:form modelAttribute="bookApplyVO" action="bookApplyWrite.do" id="register_form">
		<div>1개월동안 신청한 희망도서수 : ${month}</div>
		<div>1년동안 신청한 희망도서수 : ${year}</div>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<form:input path="book_apply_title" class="input-box" placeholder="희망도서명을 입력하세요.(도서명/저자/출판사순으로 입력하면 도서 추가에 도움됩니다.)"/>
				<form:errors path="book_apply_title" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="book_apply_content" class="input-textarea" placeholder="책소개(신청사유)를 입력하세요."/>
				<form:errors path="book_apply_content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button id="submit">등록</form:button>
			<input type="button" value="목록" onclick="location.href='bookApplyMain.do'">
		</div>	                               
	</form:form>
</div>