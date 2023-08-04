<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#dialog').dialog({
			width: '700px',
			height: 'auto',
			autoOpen: false,
			modal: true
		});
	});
</script>    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rent.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<div>
	<div>
		<input type="button" id="opener" value="대출 등록">
	</div>
	
	<p>
		대출 도서 : 
		<span id="book_name"></span>
		<span id="book_count"></span>
	</p>
	<p>
		대출 회원 : 
		<span id="member_name"></span>
	</p>
		<!-- 대출 등록 다이얼로그 시작 -->
	<div id="dialog">
		<form id="new_form">
			<ul>
				<li>
					<label for="book_search">대출할 도서</label>
					<input type="text" id="book_search" autocomplete="off">
					<ul id="search_area1"></ul>
					<div id="book_list"></div>
				</li>
			</ul>
			<ul>
				<li>
					<label for="member_search">대출할 회원</label>
					<input type="text" id="member_search" autocomplete="off">
					<ul id="search_area"></ul>
					<div id="member_list"></div>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="전송">
			</div>
		</form>
	</div>
		<!-- 대출 등록 다이얼로그 끝 -->
	<c:if test="${count == 0}">
	<div class="result-display">표시할 대출 내역이 없습니다.</div>
	</c:if>	
</div>