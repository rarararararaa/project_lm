<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<h2>lm문고 공지사항</h2>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>NO.</th>
			<th>제목</th>
			<th>작성일</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td class="align-center">${board.board_num}</td>
			<td width="400">
				<a href="announceDetail.do?board_num=${board.board_num}">${board.board_title}</a>
			</td>
			<td class="align-center">${board.board_reg_date}</td>
			<td class="align-center"><c:if test="${board.board_filename!=null}">O</c:if></td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<c:if test="${mem_auth==9}">
	<input type="button" value="글쓰기" onclick="location.href='announceWrite.do'">
	</c:if>
</div>