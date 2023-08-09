const IMP = window.IMP; 
IMP.init("imp48405101"); //가맹점 식별코드
$(function(){
//==주문 상품 리스트 보여주기==//
	$('#book_detail').click(function(){
		//alert('작동');
		$('.book-list').toggleClass('hidden-place');
	})
	//포인트 전액 사용 버튼
	$('#allPoint').click(function(){
		let total = $('#total').attr('data-total');
		let maxpoint = $('#mem_point').attr('data-maxPoint');
		$('#mem_point').val(maxpoint);
		$('#side_point').val(maxpoint);
		$('#side_point').text(maxpoint.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
		totalPrice(total);
	})
	//포인트 키보드 이벤트
	$('#mem_point').on('keydown keyup',function(key){
		let total = $('#total').attr('data-total');
		let point = parseInt($(this).val());
		overPoint(point);
		if(key.keyCode == 13){
			overPoint(point);
		}
		totalPrice(total);
	})
	//결제 수단 선택 이벤트
	$('.type-payment').click(function(){
		$(this).addClass('type-select');
		if($(this).val() == 1){
			$('#kakaoPay').removeClass('type-select');
			$('#paySubmit').attr('data-type',$(this).val());
		}else{
			$('#card').removeClass('type-select');
			$('#paySubmit').attr('data-type',$(this).val());
		}
	})
	
//==결제화면==//
	$('#paySubmit').on('click',function(event){
		event.preventDefault();
		//고유한 주문번호 만들기
		var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
	//alert('작동');
	let pg;
	let pay_method;
	let type = $(this).attr('data-type');
	if(type == 1){//신용카드 결제
		pg = 'html5_inicis';
		pay_method = 'card';
	}else if(type == 2){//카카오 페이 결제
		pg = 'kakaopay';
		pay_method = 'kakaopay';	
	}else{
		alert('결제 수단을 선택해주세요!');
		return false;
	}
	//결제 정보
	let count = $('#count').attr('data-count');
	let name = $('.title').text();
	if(count > 1){
		name = $('.title:first').text().substring(0,8)+'...외 '+(count-1)+'종';
	}
	let total = $('#due').attr('data-total');
	let cell = $('#cell').attr('data-cell');
	let email = $('#memInfo').attr('data-email');
	let zipcode = $('#memInfo').attr('data-zipcode');
	let address = $('#memINfo').attr('data-address');
	let due = $('#paySubmit').attr('data-dueTotal');
	if(due == total){
		due = parseInt(due)+3000;
	}
	//alert(due);
	IMP.request_pay({
                pg : pg,
                pay_method : pay_method,
                merchant_uid: "IMP"+makeMerchantUid, //주문번호
                name : name,
                amount : 1,
                buyer_email : email,
                buyer_name : 'LM문고',
                buyer_tel : cell,
                buyer_addr : address,
                buyer_postcode : zipcode
            }, function (rsp) { //callback
					//rsp.imp_uid 값으로 결제 단건조뢰 API를 호출하여 결제결과를 판단합니다.
                if (rsp.success) {
					console.log(typeof rsp);
					console.log(rsp);
					ajaxPaycomplete(rsp);
                } else {
					alert('결제 취소')
                    console.log(typeof rsp);
                }
            });
	})
	
//==배송지 버튼 클릭시 모달 창 띄우기==//
	$('#dialog').dialog({
		width:'700px',
		height:'auto',
		autoOpen:false,
		modal:true
	});
	$('#deli-btn').click(function(){
		$('#dialog').dialog('open');
	})
})
//=========================함수 시작==========================//
function ajaxPaycomplete(rsp){

	console.log(rsp);
	$.ajax({
		url:'orderAction.do',
		type:'post',
		data:{
			orderInfo:JSON.stringify(rsp)
			},
		dataType:'json',
		success:function(param){
			if(param.result == 'success'){
				alert('결제에 성공했습니다.');
				location.href='../template/bsMain.do';
			}
		},
		error:function(){
			alert('네트워크 오류');
		}
	})
}
//가지고 있는 포인트 포다 많이 입력할 시 현재 가지고 있는 최대 포인트로 입력&사이드바 포인트 바꾸기
function overPoint(point){
	let maxpoint = $('#mem_point').attr('data-maxPoint');
	if(point >= maxpoint){
		$('#mem_point').val(maxpoint);
		$('#side_point').text(maxpoint.toLocaleString('en')+'원');
		console.log(point + ', '+maxpoint);
		return;
	}
	$('#side_point').text(point.toLocaleString('en')+'원');
}
//결제 예정 금액
function totalPrice(total){
	let total_due = parseInt(total);
	let point = parseInt($('#mem_point').val());
	let price = 0;
	if(total_due < 50000){
		price = total_due + 3000 - point;
		$('#due').text(price.toLocaleString('en')+'원');
		$('#paySubmit').attr('data-dueTotal',price);
		return;	
	}
	price = total_due - point;
	console.log(price);
	$('#paySubmit').attr('data-dueTotal',price);
	$('#due').text(price.toLocaleString('en')+'원');
}
//첫번째 모달-배송 추가
function fnShowPop(sGetName){
    var $layer = $("#"+ sGetName);
    $layer.addClass("on");
	if(sGetName == 're_pwd'){
		document.body.classList.add("scroll");
	}else{
		$('re_pwd').addClass("scroll");
	}
}
 
function fnHidePop(sGetName){
    $("#"+ sGetName).removeClass("on");
		if(sGetName == 're_pwd'){
		document.body.classList.remove("scroll");
	}else{
		$('re_pwd').removeClass("scroll");
		 closeDaumPostcode();
	}
}
