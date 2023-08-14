<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="result-main">
	<ul>
		<li>문의 내역</li>
		<li>${askVO.ask_title}</li>
		<li>${askVO.ask_content}</li>
	</ul>
	<ul>
		<li>답변</li>
		<li>${answerVO.answer_content}</li>
	</ul>
</div>