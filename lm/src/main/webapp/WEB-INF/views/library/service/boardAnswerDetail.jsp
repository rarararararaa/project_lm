<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="result-main">
	<ul class="detail">
		<li class="title">문의 내역</li>
		<li>제목 : ${boardAskVO.ask_title}</li>
		<li>내용 : ${boardAskVO.ask_content}</li>
	</ul>
	<hr>
	<ul class="detail">
		<li class="title">답변</li>
		<li>${boardAnswerVO.answer_content}</li>
	</ul>
	<input type="button" value="목록" onclick="location.href='boardMainDesk.do'">
	<input type="button" value="다시문의하기" onclick="location.href='boardAskWrite.do'">
</div>