<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내가 쓴 1:1문의 게시글 상세 시작 -->
<div class="result-main">
	<ul class="detail">
		<li class="title">회원번호(${boardAskVO.ask_num})님 문의 내역</li>
		<li>제목 : ${boardAskVO.ask_title}</li>
		<li>내용 : ${boardAskVO.ask_content}</li>
		<li>작성일 : ${boardAskVO.ask_reg_date}</li>
	</ul>
	<hr>
	<ul class="detail">
		<li class="title">관리자 답변</li>
		<li>${boardAnswerVO.answer_content}</li>
	</ul>
	<hr size="1" width="100%">
	<div class="align-right">
		<input type="button" value="목록"
		          onclick="location.href='admin_boardAskList.do'"> 
	</div>
	
</div>
<!-- 내가 쓴 1:1문의 게시글 상세 끝 -->







