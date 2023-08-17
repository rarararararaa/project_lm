<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- (관리자) 1:1문의답변 상세 시작 -->
<div class="result-main">
	<ul class="detail">
		<li class="title">작성한 문의 내역</li>
		<li>제목 : ${boardAskVO.ask_title}</li>
		<li>내용 : ${boardAskVO.ask_content}</li>
	</ul>
	<hr>
	<ul class="detail">
		<li class="title">관리자 답변</li>
		<li>${boardAnswerVO.answer_content}</li>
	</ul>
	<hr size="1" width="100%">
	<div class="align-right">
		<input type="button" value="수정"
		 onclick="location.href='boardAnswerUpdate.do?answer_num=${boardAnswerVO.answer_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('boardAnswerDelete.do?answer_num=${boardAnswerVO.answer_num}');
				}
			};
		</script>             
		<input type="button" value="목록"
		          onclick="location.href='user_boardAsklist.do'">
	</div>
</div>
<!-- (관리자) 1:1문의답변 상세 끝 -->
