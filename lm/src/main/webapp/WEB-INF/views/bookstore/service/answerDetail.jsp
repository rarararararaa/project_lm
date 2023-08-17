<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="result-main">
	<ul class="detail">
		<li class="title">문의 내역</li>
		<li>${askVO.ask_reg_date}</li>
		<li>제목 : ${askVO.ask_title}</li>
		<li>내용 : ${askVO.ask_content}</li>
		
	</ul>
	<hr>
	<ul class="detail">
		<li class="title">답변</li>
		<li>${answerVO.answer_reg_date}</li>
		<li>${answerVO.answer_content}</li>
	</ul>
	<input type="button" value="목록" onclick="location.href='mainDesk.do'">
	<input type="button" value="다시문의하기" onclick="location.href='askWrite.do'">
</div>