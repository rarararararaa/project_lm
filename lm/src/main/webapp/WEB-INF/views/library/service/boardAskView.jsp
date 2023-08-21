<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내가 쓴 1:1문의 게시글 상세 시작 -->
<div class="result-main">
	<ul class="detail">
		<li class="title">회원번호(${boardAskVO.ask_num})님 문의 내역</li>
		<li>제목 : ${boardAskVO.ask_title}</li>
		<li>내용 : ${boardAskVO.ask_content}</li>
		<li>
			<c:if test="${!empty boardAskVO.ask_modify_date}">
				최근 수정일 : ${boardAskVO.ask_modify_date}
			</c:if>
			<c:if test="${empty boardAskVO.ask_modify_date}">
				작성일 : ${boardAskVO.ask_reg_date}
			</c:if>
		</li>
	</ul>
	<hr>
	<ul class="detail">
		<li class="title">관리자 답변</li>
		<li>${boardAnswerVO.answer_content}</li>
	</ul>
	<hr size="1" width="100%">
	<div class="align-right">
		<input type="button" value="수정"
		 onclick="location.href='boardAskUpdate.do?ask_num=${boardAskVO.ask_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('boardAskDelete.do?ask_num=${boardAskVO.ask_num}');
				}
			};
		</script>             
		<input type="button" value="목록"
		          onclick="location.href='user_boardAskList.do'">
		<input type="button" value="재문의" 
				onclick="location.href='boardAskWrite.do'">  
	</div>
	
</div>
<!-- 내가 쓴 1:1문의 게시글 상세 끝 -->







