$(function(){
	$('.decrease').click(function(){
		let num=$(this).closest('span').children('input').val();
		changeNum($(this),num);
		
	});
	$('.increase').click(function(){
		let num=$(this).closest('span').children('input').val();
		changeNum($(this),num);
	});
	
	$('.cart-btn').on('click',function(){
		//서버와 통신
		$.ajax({
			url:'cart.do',
			type:'post',
			data:{mem_cart_num:$(this).attr('data-cartnum'),
				  store_product_num:$(this).attr('data-itemnum'),
				  cart_quantity:$(input[type='number']).val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'noSale'){
					alert('판매가 중지되었습니다.');
					location.href='list.do';
				}else if(param.result == 'noQuantity'){
					alert('상품의 수량이 부족합니다.');
					location.href='list.do';
				}else if(param.result == 'success'){
					alert('상품의 개수가 수정되었습니다.');
					location.href='list.do';
				}else{
					alert('장바구니 상품 개수 수정 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});	
});

//증감 함수
function changeNum(tag,num){
	let number=parseInt(num);
	let total=0;
	let fixedprice =parseInt($(tag).closest('.right-area').prev()
							  .find('.prod-info-price').children().eq(0).text());
					
	if($(tag).attr('class')=='decrease'){
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
							  .find('.prod-info-price').children().eq(1).text(total.toLocaleString("ko-KR")+" 원");
	
}





















