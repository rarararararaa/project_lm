<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${mem_auth < 9}">
	<h2>1:1상담/문의</h2>
	<input type="button" value="1:1문의하기" onclick="location.href='${pageContext.request.contextPath}/library/service/boardAskWrite.do'">
</c:if>
<h2>최근 나의 문의 내역</h2>
<table class="striped-table">
	<tr>
		<th>문의 번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>답변상태</th>
	</tr>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 1:1문의내역이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<c:forEach var="boardAsk" items="${list}">
	<tr>
		<td class="align-center">${boardAsk.ask_num}</td>
		<td class="align-center" width="400">
			<a href="boardAskDetail.do?ask_num=${boardAsk.ask_num}">${boardAsk.ask_title}</a>
		</td>
		<td class="align-center">${boardAsk.ask_reg_date}</td>
		<td class="align-center">
		<c:if test="${boardAsk.ask_status==0}">
		<span style="color: red;">미답변</span>
			<c:if test="${mem_auth==9}">
				<input type="button" value="답변하기" 
					onclick="location.href='boardAnswerWrite.do?ask_num=${boardAsk.ask_num}'">
			</c:if>
		</c:if>
		<c:if test="${boardAsk.ask_status==1}">
			<a href="boardAskDetail.do?ask_num=${boardAsk.ask_num}">
				<span style="color: blue;">답변완료</span>
			</a>
		</c:if>
		</td>
	</tr>
	</c:forEach>
	</c:if>
</table>
