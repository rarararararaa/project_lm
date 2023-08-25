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
		let total = parseInt($('#total').attr('data-total'));
		let maxpoint = $('#mem_point').attr('data-maxPoint');
		let due = parseInt($('#paySubmit').attr('data-dueTotal'));
		if(parseInt(maxpoint) >= due){
			alert(due);
			$('#due').text(0+'원');
			$('#paySubmit').attr('data-dueTotal',0);
			$('#mem_point').val(due);
			$('#side_point').val(due);
			$('#side_point').text(due.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
			return;
		}
		$('#mem_point').val(maxpoint);
		$('#side_point').val(maxpoint);
		$('#side_point').text(maxpoint.replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
		totalPrice(total);
	})
	//포인트 키보드 이벤트
	$('#mem_point').on('keydown keyup',function(key){
		let total = $('#total').attr('data-total');
		let point = parseInt($(this).val());
		let due = parseInt($('#paySubmit').attr('data-dueTotal'));
		
		overPoint(point); 
		if(!((key.keyCode > 95 && key.keyCode < 106) || (key.keyCode > 47 && key.keyCode < 58)  || key.keyCode == 8)){
			return false;
		}
		if($(this).val().trim() == ''){
			$(this).val(0);
			$('#side_point').text(0+'원');
			return false;
		}
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
//배송지 선택
	$('#deli_submit').click(function(){
		//선택한 주소의 home_num
		let zipcode = $('.deli-default:checked').closest('tr').find('.deliInfo').children('li').eq(2).text().trim().substring(1,6);
		let memInfo = $('.deli-default:checked').closest('tr').find('.deliInfo').children('li').eq(1).text().trim();
		let deliInfo = $('.deli-default:checked').closest('tr').find('.deliInfo').children('li').eq(2).text().trim();
		//기본 배송지 설정 여부
		let check = $('#home_default').is(':checked');
		let origin_default = $('.deli-default:checked').attr('data-default');
		if(check && origin_default == 0){
			alert('기본 배송지로 등록된 배송지 입니다.');
			return;
		}else if(check){
			let home_num = parseInt($('.deli-default:checked').val());
			$.ajax({
				url:'defaultChange.do',
				type:'get',
				data:{home_num:home_num},
				dataType:'json',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 이용하세요');
						location.href='/member/login.do';
					}
					else if(param.result == 'success'){
						alert('등록되었습니다.');
						$('#deli_table').empty();
						deliList(param.home_list);
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			})
		}
		$('#memInfo').attr('data-zipcode',zipcode);
		$('#cell').text(memInfo);
		$('#default_deli').text(deliInfo);
		fnHidePop('re_pwd');
		//defaultMain();
		
	})
//==배송지 수정 폼==//
	$('#deli_table').on('click','.modify-btn',function(){
		let home_num = $(this).attr('data-homenum');
		//alert(home_num);
		$.ajax({
			url:'homeModify.do',
			type:'get',
			data:{home_num:home_num},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용할 수 있습니다.');
					location.href='/member/login.do';
				}else if(param.result == 'success'){
					let Info = param.homeInfo;
					let modify_div = $('.deli-modal-con');
					//배송점보 보여줒기
					$(modify_div).find('#deli_title').val(Info.home_title);
					$(modify_div).find('#recipient').val(Info.home_name);
					$(modify_div).find('#recipient').attr('data-num',Info.home_num);
					$(modify_div).find('#phone').val(Info.home_cell);
					$(modify_div).find('#home_zipcode').val(Info.home_zipcode);
					$(modify_div).find('#address1').val(Info.home_address);
					$(modify_div).find('#address2').val(Info.home_address_detail);
					if(Info.home_default == 0){
						$(modify_div).find('#home_default').prop('checked',true);
					}else{
						$(modify_div).find('#home_default').prop('checked',false);
					}
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		})
	})
//==배송지 수정action==//
	$('#modify_form').submit(function(event){
		let form_data = $(this).serialize();
		let homenum = $('.deli-modal-con').find('#recipient').attr('data-num');
		if(!$('#home_default').is(':checked')){
			form_data += '&home_default=1';
		}
		form_data += '&home_num='+homenum;
		$.ajax({
			url:'homeModify.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용가능합니다.');
				}else if(param.result == 'success'){
					alert('수정되었습니다.');
					$('#deli_table').empty();
					console.log(param.home_list);
					deliList(param.home_list);
					$("#re_mo_pwd").removeClass("on");
					let newHome = param.default;
					if(newHome != null){
						defaultMain(newHome);
					}
					$('#deli_form')[0].reset();
					fnHidePop('re_pwd');
				}else{
					alert('수정 action 오류');
				}
			},
			error:function(){
				
			}
		})
		event.preventDefault();
	})
//==배송지 추가==//
	$('#deli_form').submit(function(event){
		
		let form_data = $(this).serialize();
		if(!$('#home_default').is(':checked')){
			form_data += '&home_default=1';
		}
		console.log(form_data);
		$.ajax({
			url:'homeInsert.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용가능합니다.');
				}else if(param.result == 'success'){
					$('#deli_table').empty();
					deliList(param.home_list);
					$("#re_re_pwd").removeClass("on");
					alert('추가되었습니다.');
					let newHome = param.default;
					if(newHome != null){
						defaultMain(newHome);
					}
					$('#deli_form')[0].reset();
					fnHidePop('re_pwd');
				}
			},
			error:function(){
				
			}
		})
		event.preventDefault();
	})
//==배송지 삭제==//
	$('#deli_table').on('click','.delete-btn',function(){
		let check = confirm('선택한 주소를 삭제하시겠습니까?');
		if(check){
		let  home_num = $(this).attr('data-homenum');
		$.ajax({
			url:'homeDelete.do',
			type:'get',
			data:{home_num:home_num},
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용할 수 있습니다.')
				}else if(param.result == 'success'){
					$('#deli_table').empty();
					let output = '';
					if(param.home_list == null){
						output += '<div class="non-deliInfo">';
						output += '<img alt="" src="${pageContext.request.contextPath}/images/stop.png">';
						output += '<p>등록된 배송지가 없습니다.</p>';
						output += '</div>';
						$('#deli_table').append(output);
					}else{
						deliList(param.home_list);
					}
				}else{
					alert('배송지 삭제 오류');
				}
			},
			error:function(){
				alert('네트워크 오류');
			}
		})
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
	let name = $('.title').text().trim();
	if(count > 1){
		let length = $('.title:first').text().trim().length;
		console.log('길이 :'+length);
		if(length < 5){
			length = 2;
		}else{
			length = length - 3;
		}
		name = $('.title:first').text().trim().substring(0,length)+'...외 '+(count-1)+'종';
	}
	let total = $('#paySubmit').attr('data-dueTotal');
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
                amount : total,
                buyer_email : email,
                buyer_name : 'LM문고',
                buyer_tel : cell,
                buyer_addr : address,
                buyer_postcode : zipcode
            }, function (rsp) { //callback
				console.log(rsp.success);
					//rsp.imp_uid 값으로 결제 단건조뢰 API를 호출하여 결제결과를 판단합니다.
                if (rsp.success) {
					$.ajax({
                            url: "/verifyIamport/"+rsp.imp_uid,
                            method: "POST",
                            contentType: "application/json",
                            data: JSON.stringify({
                                imp_uid: rsp.imp_uid,            // 결제 고유번호
                                merchant_uid: rsp.merchant_uid,   // 주문번호
                                amount: rsp.paid_amount
                            }),
                        }).done(function (data) {
                            // 가맹점 서버 결제 API 성공시 로직
							if(rsp.paid_amount == data.response.amount){
								console.log(rsp);
								console.log(data);
								ajaxPaycomplete(rsp);
							}else{
								alert('결제 실패');
							}
                        })
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
//기본 주소 변경-부모 화면 갱신
function defaultMain(newHome){
	$('.empty').empty();
	//data속성 값 변경시 두 번째 인자에 값 입력
	$('#memInfo').attr('data-zipcode',newHome.home_zipcode);
	$('#memInfo').attr('data-address',newHome.home_address+newHome.home_address_detail);
	//부모창 기본 배송지 변경
	$('#cell').attr('data-cell',newHome.mem_cell);
	$('#cell').text(newHome.mem_name+' / '+newHome.mem_cell);
	$('#default_deli').text('['+newHome.home_zipcode+']'+newHome.home_address+' '+newHome.home_address_detail);
}

//삭제&추가 후 리스트 화면 갱신
function deliList(list){
	console.log(list);
	let output = '';
	$(list).each(function(index, item){
		output += '<tr>';
		output += '<td>';
		if(item.home_default == 0){//기본 배송지 일떄
			output += '<input type="radio" value="'+item.home_num+'" class="deli-default" name="check_deli" checked=checked"  data-default = "'+item.home_default+'">';
		}else{
			output += '<input type="radio" value="'+item.home_num+'" class="deli-default" name="check_deli" data-default = "'+item.home_default+'">';
		}
		output += '</td>';//첫 번째 열
		output += '<td>';
		output += '<ul class="deliInfo">';
		if(item.home_default == 0){//기본 배송지 일떄
			output += '<li class="default-color">기본 배송지</li>';
		}else{
			output += '<li>일반 배송지</li>';
		}
		output += '<li>'+item.home_name+'/'+item.home_cell+'</li>';
		output += '<li> ['+item.home_zipcode+']/'+item.home_address+item.home_address_detail+'</li>';
		output += '</ul>';
		output += '</td>';//두 번쨰 열
		output += '<td style="text-align: right;" width="70">';//'뒤에 \넣으면 문자로 인식
		output += '<button class="deli-btn modify-btn" id="modify_deli" onclick="fnShowPop(\'re_mo_pwd\')" data-homenum="'+item.home_num+'">수정</button>';
		if(item.home_default > 0){//일반 배송지 일떄
			output += '<button class="deli-btn delete-btn" id="delete_deli" data-homenum="'+item.home_num+'">삭제</button>';
		}
		output += '</td>';//세 번째 열
		output += '</tr>';
	})
	$('#deli_table').append(output);
}
//결제 처리 
function ajaxPaycomplete(rsp){
	let notice = $('#deli-request').val();
	let point = parseInt($('#mem_point').val());
	console.log(rsp);
	$.ajax({
		url:'orderAction.do',
		type:'post',
		data:{
			orderInfo:JSON.stringify(rsp),
			notice:notice,
			point:point
			},
		dataType:'json',
		success:function(param){
			if(param.result == 'success'){
				let order = param.order;
				console.log(order.order_num);
				alert('결제에 성공했습니다.');
				location.href='receipt.do?order_num='+order.order_num;
			}
		},
		error:function(){
			alert('네트워크 오류');
		}
	})
}
//가지고 있는 포인트 보다 많이 입력할 시 현재 가지고 있는 최대 포인트로 입력&사이드바 포인트 바꾸기
function overPoint(point){
	let maxpoint = $('#mem_point').attr('data-maxPoint');
	let total = $('#due').attr('data-total');
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
		$('#home_default').prop('checked',false);
	}else{
		$('re_pwd').removeClass("scroll");
		 closeDaumPostcode();
		$('#deli_form')[0].reset();
	}
}
