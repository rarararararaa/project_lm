$(function(){
	$('.cancelPay').click(function(){
		let order_num = $(this).val();
		//alert(order_num);
		$.ajax({
			url:'/bookstore/payment/cancelPay.do',
			data:{order_num:order_num},
			type:'get',
			dataType:'json',
			success:function(param){
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
	})
})