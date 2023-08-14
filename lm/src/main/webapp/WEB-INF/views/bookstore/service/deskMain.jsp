<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2>1:1상담/문의</h2>
<input type="button" value="1:1문의하기" onclick="location.href='askWrite.do'">
<input type="button" value="실시간 채팅으로 문의하기">

<c:if test="${mem_num!=null}">
<h2>최근 문의 내역</h2>
<table class="striped-table">
	<tr>
		<th>문의 번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>답변상태</th>
	</tr>
	<c:forEach var="ask" items="${list}">
	<tr>
		<td class="align-center">${ask.ask_num}</td>
		<td class="align-center">${ask.ask_title}</td>
		<td class="align-center">${ask.ask_reg_date}</td>
		<td class="align-center">
		<c:if test="${ask.ask_status==0}">미답변<c:if test="${mem_auth==9}">
			<input type="button" value="답변하기" onclick="location.href='answerWrite.do?ask_num=${ask.ask_num}'"></c:if></c:if>
		<c:if test="${ask.ask_status==1}"><a href="answerDetail.do?ask_num=${ask.ask_num}">답변완료</a></c:if>
		</td>
	</tr>
	</c:forEach>
</table>
</c:if>