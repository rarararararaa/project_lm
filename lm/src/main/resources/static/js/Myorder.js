$(function(){
	$('.cancelPay').click(function(event){
		event.preventDefault();
		let order_num = $(this).val();
		//alert(order_num);
		let check = confirm('주문을 취소하시겠습니까?');
		if(check){
		$.ajax({
			url:'/bookstore/payment/cancelPay.do',
			data:{order_num:order_num},
			type:'get',
			dataType:'json',
			success:function(param){
				//alert('tlqkf 왜 이상한데로가는거야 rowhwrkxdms');
				if(param.result == 'errorCancel'){
					alert('취소에 실패했습니다. 카드사에 연락해주세요');
				}else if(param.result == 'success'){
					alert('결제가 취소되었습니다.');
					location.reload();
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		})
		}
	})
})