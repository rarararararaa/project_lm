<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 게시판 목록 시작 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/BsEventAnnounce.css">
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>

<div class="eventannouncepage-main">
	<h2>당첨자 발표</h2>
	<div class="eventpage-div">
	<form action="announceList.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='eventAnnounceList.do'">
			</li>
		</ul>
		<div class="align-right">
			<c:if test="${mem_auth == 9}">
				<input type="button" value="글쓰기" onclick="location.href='eventAnnounceWrite.do'">
			</c:if>
		</div>
	</form>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td class="align-center">${board.event_announce_board_num}</td>
			<td width="400">
				<a href="eventAnnounceDetail.do?event_announce_board_num=${board.event_announce_board_num}">${board.title}</a>
			</td>
			<td class="align-center">${board.reg_date}</td>
			<td class="align-center">${board.hit}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	</div>
</div>
<!-- 게시판 목록 끝 -->