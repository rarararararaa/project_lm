const IMP = window.IMP; 
IMP.init("imp26887541"); //가맹점 식별코드
//IMP.init("imp48405101"); //가맹점 식별코드

$(function() {
	let name = "";
	let amount;
	let callNumber = $('.callNumber').attr('data-callNumber');
	let rent_num = $('.rent_num').attr('data-rentNum');
	
	//분실도서 선택에 따른 처리
	$('input[type="radio"].lostBook').on('click', function() {
		if($('input:radio[name="lostBook"]').is(":checked"))
		 $('#checked_action').css('display', 'block');
		
		if($(this).is(':checked')){
			$('.sel-book-ISBN').text($(this).parents('tr').find('.book-isbn').attr('data-isbn'));
			$('.sel-book-name').text($(this).parents('tr').find('.book-name').attr('data-name'));
			name = $(this).parents('tr').find('.book-name').attr('data-name');
			
			$('.sel-book-price').text(Number($(this).parents('tr').find('.book-price').attr('data-price')).toLocaleString()+'원');
			$('.fin-price').text(Number($(this).parents('tr').find('.book-price').attr('data-price')).toLocaleString()+'원');
			amount = Number($(this).parents('tr').find('.book-price').attr('data-price'));
			
			callNumber = $(this).parents('tr').find('.callNumber').attr('data-callNumber');
			rent_num = $(this).parents('tr').find('.rent_num').attr('data-rentNum');
		}
	});
	//결제방식 선택에 따른 처리
	$('input[type="radio"].type-payment').on('click', function(){
		if($(this).is(':checked')){
			$('#lostSubmit').attr('data-type', $(this).val());
		}
	});
	
	//결제버튼 클릭
	$('#lostSubmit').on('click', function(event) {
		//submit 기본 이벤트 제거
		event.preventDefault();
		
		//고유한 주문번호 만들기
		var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;

		let pg;
		let pay_method;
		let type = $(this).attr('data-type');
		if (type == 1) {//신용카드 결제
			pg = 'html5_inicis';
			pay_method = 'card';
		} else if (type == 2) {//카카오 페이 결제
			pg = 'kakaopay';
			pay_method = 'kakaopay';
		} else {
			alert('결제 수단을 선택해주세요!');
			return false;
		}
		
		let buyer_name = $('.buyer-name').text();
		let buyer_tel = $('.buyer-tell').text();
		let buyer_email = $('.buyer-email').text();
		

		console.log('name>> '+name);
		console.log('callNumber>> '+callNumber);
		console.log('rent_num>> '+rent_num);
		

		IMP.request_pay({
			pg : pg,
            pay_method : pay_method,
			merchant_uid: "IMP"+makeMerchantUid, //주문번호
			name: name,
			amount: amount,
			buyer_name: buyer_name,
			buyer_tel : buyer_tel,
			buyer_email : buyer_email
		}, function(response) {
			//결제 후 호출되는 callback함수
			if (response.success) { //결제 성공
				console.log(response);
				ajaxPaycomplete(response);
			} else {
				alert('결제실패 : ' + response.error_msg);
			}
		})
	});
	
	function ajaxPaycomplete(response){
		
		$.ajax({
			url: 'bookLostReportAction.do',
			type: 'post',
			data:{
				callNumber: callNumber,
				rent_num : rent_num,
				amount: amount,
				orderInfo: JSON.stringify(response)
			},
			dataType: 'json',
			success: function(param){
				if(param.result == 'success'){
					let lostBook = param.lostBook;
					alert('결제에 성공했습니다.');
					location.href='bookLostCheck.do?lost_report_num='+lostBook.lost_report_num;
				}
			},
			error: function(){
				alert('네트워크 오류');	
			}
		})
	}
});