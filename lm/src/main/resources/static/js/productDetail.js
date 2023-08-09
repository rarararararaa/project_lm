$(function(){
	$('.decrease').click(function(event){
		event.preventDefault();			
		let num=$(this).closest('span').children('input').val();
		changeNum($(this),num);
		
	});
	$('.increase').click(function(event){
		event.preventDefault();			
		let num=$(this).closest('span').children('input').val();
		changeNum($(this),num);
	});
	
		
	

});

//증감 함수
function changeNum(tag,num){
	let number=parseInt(num);
	let total=0;
	let fixedprice =parseInt($(tag).closest('.right-area').prev()
							  .find('.prod-info-price').children().eq(0).val());
					
	if($(tag).attr('id')=='decrease'){
		if(number==1){
			return false;
		}
		number--;
		$(tag).closest('span').children('input').val(number);
	}else{
		number++;
		//나중에 재고연동
		$(tag).closest('span').children('input').val(number);
	}
	
	total+=(fixedprice*number);
	$(tag).closest('.right-area').prev()
							  .find('.prod-info-price').find('#total').text(total.toLocaleString()+' 원');
	
}

//submit
function submitCart(){
		$('#product_cart').on('submit',function(event){
			//기본 이벤트 제거
			event.preventDefault();			
			let form_data = $(this).serialize();		
			//서버와 통신
			$.ajax({
				url:'/bookstore/payment/cart.do',
				type:'post',
				data:form_data,
				dataType:'json',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요');
					}else if(param.result == 'success'){
						let check=confirm('장바구니로 이동하시겠습니까?');
						if(check){
							location.href='/bookstore/payment/cart.do';
						}else{
							location.reload();
						}
					}else{
						alert('장바구니 담기 오류');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
		});
}

function submitOrder(){
		$('#product_cart').on('submit',function(event){
			//기본 이벤트 제거
			event.preventDefault();
						
			let form_data = $(this).serialize();		
			//서버와 통신
			$.ajax({
				url:'order.do',
				type:'post',
				data:form_data,
				dataType:'json',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요');
					}else if(param.result == 'success'){
						confirm('결제창으로 이동하시겠습니까?');
						if(!false){
							location.reload();
						}
					}else{
						alert('장바구니 담기 오류');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
		});	
}



















