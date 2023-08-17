<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>최근 문의 내역</h2>
<table class="striped-table">
	<tr>
		<th>문의 번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>답변상태</th>
	</tr>
	<c:forEach var="boardAsk" items="${list}">
	<tr>
		<td class="align-center">${boardAsk.ask_num}</td>
		<td class="align-center" width="400">
			<a href="admin_boardAskdetail.do?ask_num=${boardAsk.ask_num}">${boardAsk.ask_title}</a>
		</td>
		<td class="align-center">${boardAsk.ask_reg_date}</td>
		<td class="align-center">
		<c:if test="${boardAsk.ask_status==0}">
			<span style="color: red;">미답변</span>
			<input type="button" value="답변하기" 
					onclick="location.href='admin_boardAnswerWrite.do?ask_num=${boardAsk.ask_num}'">
		</c:if>
		<c:if test="${boardAsk.ask_status==1}">
			<a href="boardAnswerDetail.do?ask_num=${boardAsk.ask_num}"><span style="color: blue;">답변완료</span></a>
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>