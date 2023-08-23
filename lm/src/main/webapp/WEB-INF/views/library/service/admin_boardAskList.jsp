<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<div class="box">
		<div class="title">전체 1:1문의 내역</div>
		<div class="content-box">
<table class="form-box">
	<tr>
		<th class="row-title"><b>문의번호</b></th>
		<th class="row-title"><b>제목</b></th>
		<th class="row-title"><b>작성일</b></th>
		<th class="row-title"><b>답변상태</b></th>
	</tr>
	<c:forEach var="boardAsk" items="${list}">
	<tr class="align-center">
		<td class="row-title">${boardAsk.ask_num}</td>
		<td class="row-title" width="400">${boardAsk.ask_title}</td>
		<td class="row-title">${boardAsk.ask_reg_date}</td>
		<td class="row-title">
		<c:if test="${boardAsk.ask_status==0}">
			<input class="small-button-R" type="button" value="미답변"
				onclick="location.href='admin_boardAnswerWrite.do?ask_num=${boardAsk.ask_num}'">
		</c:if>
		<c:if test="${boardAsk.ask_status==1}">
			<input class="small-button-B" type="button" value="답변완료"
				onclick="location.href='admin_boardAskDetail.do?ask_num=${boardAsk.ask_num}'">
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
</div>
</div>
</div>