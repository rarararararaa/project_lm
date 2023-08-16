<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시글 상세 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/BsEventAnnounce.css">

<div class="eventannouncepage-main">
	<div class="eventpage-div">
	<h2>${announceBoard.title}</h2>
	<ul class="detail-info">
		<c:if test="${!empty announceBoard.modify_date}">
			<li>최근 수정일 : ${announceBoard.modify_date}</li>
		</c:if>
		<c:if test="${empty announceBoard.modify_date}">
			<li>작성일 : ${announceBoard.reg_date}</li>
		</c:if>
		<li>조회수 : ${announceBoard.hit}</li>
	</ul>
	<hr size="1" width="100%">
	<div class="detail-content">
		${announceBoard.content}
	</div>
	
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty mem_num && mem_auth == 9}">
		<input type="button" value="수정"
		 onclick="location.href='eventAnnounceUpdate.do?event_announce_board_num=${announceBoard.event_announce_board_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('eventAnnounceDelete.do?event_announce_board_num=${announceBoard.event_announce_board_num}');
				}
			};
		</script>             
		</c:if>
		<input type="button" value="목록"
		          onclick="location.href='eventAnnounceList.do'">
	</div>
	</div>
</div>
<!-- 게시글 상세 끝 -->








