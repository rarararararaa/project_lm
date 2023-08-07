$(function(){
	//주문 상품 리스트 보여주기
	$('#book_detail').click(function(){
		//alert('작동');
		$('.book-list').toggleClass('hidden-place');
	})
	//포인트 전액 사용 버튼
	$('#allPoint').click(function(){
		let maxPoint = $('#mem_point').attr('data-maxPoint');
		$('#mem_point').val(maxPoint);
		$('#side_point').val(maxPoint);
		$('#side_point').text(maxPoint.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	})
	
	$('#mem_point').on('keydown keyup',function(){
		let point = $(this).val();
		overPoint(point);
	})
	
})

//가지고 있는 포인트 포다 많이 입력할 시 현재 가지고 있는 최대 포인트로 입력&사이드바 포인트 바꾸기
function overPoint(point){
	let maxPoint = $('#mem_point').attr('data-maxPoint');
	if(point >= maxPoint){
		$('#mem_point').val(maxPoint);
		$('#side_point').text(maxPoint.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
		return;
	}else{
		$('#side_point').text(point.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	}
	$('#side_point').text(point.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
}
//결제 예정 금액
function totalPrice(){
	let 
}
