$(function(){
	$('.book-btn').on('click',function(){
		//서버와 통신
		$.ajax({
			url:'/library/rent/reservationWrite.do',
			type:'post',
			data:{lib_product_isbn:$(this).attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result=='overValue'){
					alert('최대 대출시 예약 불가합니다.');
					location.reload();
				}else if(param.result=='over1st'){
					alert('이미 예약된 책입니다.');
					location.reload();
				}else if(param.result=='reservationLimit'){
					alert('최대 예약권수는 3권입니다.');
					location.reload();
				}else if(param.result=='rentBook'){
					alert('대출가능인 도서는 예약이 불가능합니다.');
					location.reload();
				}else if(param.result=='success'){
					alert('예약 성공');
					location.reload();
				}else{
					alert('예약 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});		
	});
});