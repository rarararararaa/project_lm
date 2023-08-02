<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시글 상세 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<h2>${board.board_title}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${!empty board.board_modify_date}">
				최근 수정일 : ${board.board_modify_date}
			</c:if>
			<c:if test="${empty board.board_modify_date}">
				작성일 : ${board.board_reg_date}
			</c:if>
		</li>
	</ul>
	<hr size="1" width="100%">
	<div class="detail-content">
		${board.board_content}
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${mem_auth==9}">
		<input type="button" value="수정"
		 onclick="location.href='update.do?board_num=${board.board_num}'">
		<input type="button" value="삭제" id="delete_btn">
		</c:if>
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?board_num=${board.board_num}');
				}
			};
		</script> 
		<input type="button" value="목록"
		          onclick="location.href='announceList.do'">
	</div>
	
</div>
<!-- 게시글 상세 끝 -->








