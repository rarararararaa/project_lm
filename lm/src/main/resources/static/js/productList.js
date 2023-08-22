$(function(){
	$('.select-cate').click(function(){
		let detail = $(this).attr('data-category');
		let cate = $('.search-cate').attr('data-cate');
		//alert(detail);
		location.href='/bookstore/product/productCeteList.do?detail='+detail+'&cate='+cate;
	})
	$('#order').change(function(){
		let order = $(this).val();
		let detail = $('#cate_title').attr('data-category');
		let count_num  = $('#count_num').val();
		let cate = $('.search-cate').attr('data-cate');
		//alert(cate);
		location.href='/bookstore/product/productCeteList.do?detail='+detail+'&order='+order+'&count_num='+count_num+'&cate='+cate;
	})
	$('#count_num').change(function(){
		let order = $('#order').val();
		let detail = $('#cate_title').attr('data-category');
		let count_num = $(this).val();
		let cate = $('.search-cate').attr('data-cate');
		//alert(cate);
		location.href='/bookstore/product/productCeteList.do?detail='+detail+'&order='+order+'&count_num='+count_num+'&cate='+cate;
	})
	$('#drop_down , .search-cate').hover(function(){
		$('.search-cate').toggleClass('hidden-place');
	})
	$('.payGo').click(function(){
		let total= $(this).closest('tr').find('.price').val();
		let cartInfo=[];
			let book_info = {};
			book_info.cart_quantity = '1';
			book_info.store_product_pricestandard = $(this).closest('tr').find('.price').val();
			book_info.store_product_num = $(this).closest('tr').find('.product').val();
			//alert(book_info.store_product_num);
			cartInfo.push({...book_info});
			console.log(cartInfo); 
			
			submitOrder(cartInfo,total);
	})
	
})

//중고 장바구니
function submitUsedProduct(){
	let form_data = $('.book-cart').serialize();	
	console.log(form_data);	
	//서버와 통신
	$.ajax({
		url:'/bookstore/payment/cart.do',
		type:'post',
		data:form_data,
		dataType:'json',
		success:function(param){
			if(param.result == 'logout'){
				alert('로그인 후 사용하세요');
			}else if(param.result == 'existBook'){
				alert('장바구니에 이미 담겨있습니다.');
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
}			

function submitOrder(cartInfo,total){
			//기본 이벤트 제거
			event.preventDefault();
						
			//서버와 통신
			$.ajax({
				url:'/bookstore/payment/cartAction.do',
				type:'post',
				data:{
					data:JSON.stringify(cartInfo),total
				},
				dataType:'json',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요');
					}else if(param.result == 'success'){
						let check=confirm('결제창으로 이동하시겠습니까?');
						if(check){
							location.href='/bookstore/payment/order.do';
						}else{
							location.reload();
						}
					}else{
						alert('바로결제 오류');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});

}