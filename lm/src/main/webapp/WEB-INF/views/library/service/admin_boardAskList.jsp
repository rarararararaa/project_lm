<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
.button {
  background-color: #337ab7;
  color: white;
  font-weight : bold;
  padding: 15px 15px 35px 15px;
  border: none;
  cursor: pointer;
  font-size: 18px;
}

.st-btnr {
  background-color: red;
  color: white;
  border-style: none;
}

.st-btnb {
  background-color: blue;
  color: white;
  border-style: none;
}
</style>
<h2>전체 1:1문의 내역</h2>
<table class="striped-table">
	<tr>
		<th>No</th>
		<th>회원 번호</th>
		<th>제목</th>
		<th>작성일</th>
		<th>답변상태</th>
	</tr>
	<c:forEach var="boardAsk" items="${list}">
	<tr>
		<td class="align-center">${boardAsk.ask_num}</td>
		<td class="align-center">${boardAsk.mem_num}</td>
		<td class="align-center" width="400">${boardAsk.ask_title}</td>
		<td class="align-center">${boardAsk.ask_reg_date}</td>
		<td class="align-center">
		<c:if test="${boardAsk.ask_status==0}">
			<input type="button" value="미답변" class="st-btnr"
				onclick="location.href='admin_boardAnswerWrite.do?ask_num=${boardAsk.ask_num}'">
		</c:if>
		<c:if test="${boardAsk.ask_status==1}">
			<input type="button" value="답변완료" class="st-btnb"
				onclick="location.href='admin_boardAskDetail.do?ask_num=${boardAsk.ask_num}'">
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>