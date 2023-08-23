$(function(){
	//이벤트 정답 클릭시
	$('.quiz-sel').click(function(){
		let answer = $(this).find('span').attr('data-quizSel');
		let event_board_num = $(this).find('span').attr('data-boardNum');
		$.ajax({
			url:'writeQuiz.do',
			type:'post',
			data:{answer:answer, event_board_num:event_board_num},
			dataType:'json',
			success:function(param){
				if(param.status == 'logOut'){
					alert('로그인 후 사용하세요');
				}else if(param.status == 'fin'){
					alert("종료된 이벤트 입니다.")
				}
				else if(param.status == 'already'){
					alert('이미 참여한 이벤트 입니다.');
				}else if(param.status == 'wrongAnswer'){
					alert('오답입니다. 다른 답을 선택해보세요');
				}else if(param.status == 'success'){
					alert('정답입니다! 50Point가 증정되었습니다.')
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
});