$(function(){
	
	//주문 상품 리스트 보여주기
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
	
	//배송지 버튼 클릭시 모달 창 띄우기
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
		return;	
	}
	price = total_due - point;
	console.log(price);
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
