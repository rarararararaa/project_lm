<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<input type="button" value="글쓰기" onclick="location.href='announceWrite.do'">
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
				<a href="detail.do?board_num=${board.board_num}">${board.board_title}</a>
			</td>
			<td class="align-center">${board.board_reg_date}</td>
			<td class="align-center"><c:if test="${board.board_filename!=null}">O</c:if></td>
		</tr>
		</c:forEach>
	</table>
</div>