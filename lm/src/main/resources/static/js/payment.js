$(function(){
	//전체 선택 확인
	allCheck();
	
	totalPrice();
	
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
	})
	
	//전체 체크
	$('#allSelect').change(function(){
		//alert('we')
		if($(this).is(':checked')){
			$('input[type=checkbox]').prop('checked',true);
		}else{
			$('input[type=checkbox]').prop('checked',false);
		}
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
	
	
	//======데이터 전송======//
	$('#payForm').submit(function(event){
		event.preventDefault();	
		let form_data_LM = $('#LM_payForm').serialize();
		let form_data_USED = $('#USED_payForm').serialize();
		console.log(form_data_LM);
		console.log(form_data_USED);
	})
	
	
})
//===============================함수[LM/중고 선택]==================================//

//===============================함수[상품 개수 증감&가격]==================================//
function changeNum(tag, num){
//	let test = $(tag).closest('table').find('ul').children('li').eq(2).text(); 상품 가격
//	alert(test);
	let number =  parseInt(num);
	let price = parseInt($(tag).closest('tr').find('ul').children('li').eq(2).text().slice(0,-1))*1000;//n번쨰 자식노드
	//alert(price);
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
	$('.LM-item:checked').each(function(){
		let price = parseInt($(this).closest('tr').find('ul').children('li').eq(2).text().slice(0,-1))*1000;
		let num = parseInt($(this).closest('tr').children('td').eq(2).find('.MP').children('span').text());
		//alert(price);
		//alert(num);
		total += (price*num);
	})
	if(total >= 50000){
		delivery = 0;
	}
	$('#total').text(total.toLocaleString("ko-KR")+"원");
	$('#delivery').text(delivery.toLocaleString("ko-KR")+"원");
	$('#due').text((total+delivery).toLocaleString("ko-KR")+"원");
	$('#due_point').text(((total)/100).toLocaleString("ko-KR")+"원");
}

//===============================함수[checkbox]==================================//

//LM문고/중고거래 전체 선택
	function selectAllLM(name, check){
		//alert($(name).find('input[type="checkbox"]').val());
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
			$('#allSelect').prop('checked',false);t
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
