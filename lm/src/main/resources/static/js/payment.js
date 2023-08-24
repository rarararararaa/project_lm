$(function(){
	//구매 가능 상품 확인
	
	//전체 선택 확인
	allCheck();
	
	totalPrice();
	//장바구니에 상품이 있을 시 상품 없음 표시 제거
	let LM_list = $('#LM_payForm').find('.LM-item').val();
	if(LM_list != null){
		$('#LM_NOT').addClass('hidden-place');
	}
	let USED_list = $('#USED_payForm').find('.LM-item').val();
	if(USED_list != null){
		$('#USED_NOT').addClass('hidden-place');
	}
	
//===============================[전체/LM/중고 선택]==================================//
	$('.book-select').on('click',function(){// 전체 LM문고 중고상품 버튼
		//alert('왜 안됨');
		$('.book-select').not($(this)).removeClass('active animated');
		$(this).addClass('active animated');
		if($(this).val() == 0){
			$('#payBook').removeClass('hidden-place');
			$('.payment-usedBook').removeClass('hidden-place');
			$('#allSelect').prop('checked',true);
		}else if($(this).val() == 1){//LM
			$('.payment-usedBook').addClass('hidden-place');
			$('#payBook').removeClass('hidden-place');
			selectAllLM('#payBook', true);
			selectAllLM('#UesdBook', false);
		}else{//중고
			$('.payment-usedBook').removeClass('hidden-place');
			$('#payBook').addClass('hidden-place');
			selectAllLM('#payBook', false);
			selectAllLM('#UesdBook', true);
		}
		allCheck();
		totalPrice();
	})
//===============================체크 박스==================================//	
	//전체 체크
	$('#allSelect').change(function(){
		//alert('we')
		if($(this).is(':checked')){
			$('input[type=checkbox]').prop('checked',true);
		}else{
			$('input[type=checkbox]').prop('checked',false);
		}
		totalPrice();
	})
	//LM문고 선택
	$('#LM_select').change(function(){
		let check =  $(this).is(':checked');
		let tagName = '#payBook';
		selectAllLM(tagName, check);
		allCheck();
	})
	//중고 선택
		$('#UESD_select').change(function(){
		let check =  $(this).is(':checked');
		let tagName = '#UesdBook';
		selectAllLM(tagName, check);
		allCheck();
	})
	//개별 선택
	$('.LM-item').change(function(){
		let check =  $(this).is(':checked');
		selectLM(this,check);
	})
	
	//======수량 변경=========//
	$('.MP-btn').click(function(){
		let num = $(this).closest('div').children('span').text();
		//alert(num);
		changeNum($(this), num)
	})
	

//주문하기 버튼 이벤트[submit] - cartInfo 
$('#payForm').submit(function(event){
	let checked = $('.LM-item').closest('table').find('input[type=checkbox]:checked').length;
	let sub_total = $('#due').attr('data-due'); 
	if(checked == 0 || sub_total == 3000){
		alert('1개 이상의 상품을 선택하세요!');
		return false;
	}
	event.preventDefault();
	let cartInfo = [];
	let book_info = {};
	$('table').find('input[type=checkbox]').each(function(index){
		if($(this).is(':checked')){
			if($(this).attr('disabled') == null){
			//alert(index);
			let tr = $(this).closest('tr');
			let td = tr.children('td').eq(1).find('ul').children();
			let td2 = tr.children('td').eq(2).find('ul').children();
			//카트 상품 정보
			let used = td.eq(0).attr('data-used');
			let cart = td.eq(0).attr('data-cartnum');
			book_info.store_product_num = td.eq(0).attr('data-num');
			if(used == null){
				used = '0';		
			}
			book_info.mem_cart_num = cart;
			book_info.used_product_num = used;
			book_info.store_product_title = td.eq(0).text();//책 이름
			book_info.store_product_pricestandard = td.eq(2).text(); //책 가격
			book_info.cart_quantity = td2.eq(1).find('span').text(); //수량
			
			console.log(book_info);
			
			cartInfo.push({...book_info});
			}
		}
	})
	let total = $('#total').attr('data-total');
	ajaxOrder(cartInfo, total);
	//let LM_payForm = $('#LM_payForm').serialize();
	//console.log(LM_payForm);
	
})	
	
//선택한 도서 장바구니에서 삭제
$('.del-btn').click(function(){
	let mem_cart_num =  $(this).attr('data-num');
	//alert(mem_cart_num);	
	let check = confirm('해당 상품을 삭제하사겠습니까?');
	if(check){
		$.ajax({
			url:'Cartdelete.do',
			type:'post',
			data:{mem_cart_num:mem_cart_num},
			dataType:'json',
			success: function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용할 수 있습니다.');
					location.href='/member/login.do';
				}else if(param.result == 'success'){
					location.reload();
				}
			},
			error: function(){
				alert('네트워크 오류');
			}
		})
	}
})
})//function() 끝
//*********함수************//
//===============================현재 카트 값 보내기=====================//
function ajaxOrder(cartInfo, total){
	 //console.log(cartInfo);

	$.ajax({
		url: 'cartAction.do',
		type:'post',
		data:{
			data:JSON.stringify(cartInfo),total
		},
		dataType:'json',
		success:function(param){
			if(param.result == 'logout'){
				alert('로그인 후 이용하세요');
				location.href='/member/login.do';
			}
			else if(param.result == 'success'){
				console.log(total);
				//orderForm(param.cartList,total);
				location.href='order.do';
			}
		},
		error:function(){
			alert('네트워크 오류')
		}
	})//
}

//===============================함수[상품 개수 증감&가격]==================================//
function changeNum(tag, num){
//	let test = $(tag).closest('table').find('ul').children('li').eq(2).text(); 상품 가격
	let number =  parseInt(num);
	let price = parseInt($(tag).closest('tr').find('ul').children('li').eq(2).text().slice(0,-1));//n번쨰 자식노드
	if($(tag).attr('id') == 'minus'){
		number--;
		if(number == 0){
			selectLM(tag,false);
			return false;
		}
		$(tag).closest('div').children('span').text(number);
	}else{
		number++;
		$(tag).closest('div').children('span').text(number);
	}
	totalPrice();
	$(tag).closest('ul').children('li:first').text((price*number).toLocaleString("ko-KR")+"원");
}
//총 금액 계산
function totalPrice(){
	let total = 0;
	let delivery = 3000;
	let grade = $('#due_point').attr('data-point');
	$('.LM-item:checked').each(function(){
		let used = $(this).closest('form').attr('id');
		let price = 0;
		let num = 0;
		if($(this).attr('disabled') == null){
			if(used == 'USED_payForm'){
				price = $(this).closest('tr').find('ul').children('li').eq(2).attr('data-price');
				num = 1;
			}else{
				price = parseInt($(this).closest('tr').find('ul').children('li').eq(2).text().slice(0,-1));
				num = parseInt($(this).closest('tr').children('td').eq(2).find('.MP').children('span').text());
			}
		total += (price*num);
		$('#total').attr('data-total',total);
		}
	})
	if(total >= 50000){
		delivery = 0;
	}
	$('#due').attr('data-due',total+delivery);
	$('#total').text(total.toLocaleString("ko-KR")+"원");
	$('#delivery').text(delivery.toLocaleString("ko-KR")+"원");
	$('#due').text((total+delivery).toLocaleString("ko-KR")+"원");
	let point = Math.round(total*grade);
	$('#due_point').text(point.toLocaleString("ko-KR")+"원");
}

//===============================함수[checkbox]==================================//

//LM문고/중고거래 전체 선택
	function selectAllLM(name, check){
		if(check){
			$(name).find('input[type="checkbox"]').prop('checked',true);
		}else{
			$(name).find('input[type="checkbox"]').prop('checked',false);
			$('#allSelect').prop('checked',false);
		}
		totalPrice();
	}
//전체 선택 여부 확인
	function allCheck(){
		if($('#allSelect').is(':checked')){
			$('input[type=checkbox]').prop('checked',true);
			return;
		}
		if($('#LM_select').is(':checked') && $('#UESD_select').is(':checked')){
			$('#allSelect').prop('checked',true);
		}else{
			$('#allSelect').prop('checked',false);
		}
		totalPrice();
	}
//LM문고/중고거래 개별 선택
	function selectLM(tag,check){
		//alert($(tag).closest('div').find('.lm-all').children('input:first').attr('id')); //상위 체크 박스 찾기
		//alert($(tag).closest('table').find('input[type=checkbox]').attr('class'));
		let total = $(tag).closest('table').find('input[type=checkbox]').length;
		let checked = $(tag).closest('table').find('input[type=checkbox]:checked').length;
		//alert(total);
		if(!check){
			$(tag).closest('div').find('.lm-all').children('input:first').prop('checked',false);//closest = 해당 태그의 상위 태그 겁색한 것 중 가장 가까운 태크
			$('#allSelect').prop('checked',false);//전체 체크 취소
		}
		//개별로 전체 체크 확인
		if(total == checked){
			totalPrice();
			$(tag).closest('div').find('.lm-all').children('input:first').prop('checked',true);
			allCheck();
		}
		totalPrice();
	}