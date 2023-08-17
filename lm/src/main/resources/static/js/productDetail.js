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

	$('#review').click(function(){
		$.ajax({
			url:'/bookstore/review/reviewForm.do',
			type:'post',
			data:{store_product_num:$(this).attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
					location.reload();
				}else if(param.result == 'reviewAlready'){
					alert('리뷰는 구매당 한 건만 가능합니다.');
					location.reload();
				}else if(param.result == 'success'){
					//리뷰 등록 UI호출
					$('#dialog').dialog('open');
					//초기화
					$('#book_search').val('');
					$('#search_area1').empty();
					$('#book_list').empty();
						
				}else{
					alert('리뷰 작성 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}		
		});
	});
	
	 $("input[type='file']").change(function(e){
		  setThumbnail(e);
	      //div 내용 비워주기
	      $('#image_container').empty();
	 });  	
	$('#image_container').on('click', '.del', function () {
	    $("#image_container").empty()
	    $("#review_image").val("");
	});	
	
	//리뷰 등록
	$("#review_button").on('click',function(event){
		$('#new_form').submit();
	});
});
//썸네일 함수
	function setThumbnail(event) {
		
		var reader = new FileReader();

		reader.onload = function(event) {
			var img = document
					.createElement("img");
			img.setAttribute("src",
					event.target.result);
					
			var div=document.querySelector(
					"div#image_container");
			var span=document.createElement("span");
			span.classList.add("del");
			if(div.children.length>0){
				div.replaceChildren();	
			}
			div.appendChild(img);
			div.appendChild(span);
		};

		reader.readAsDataURL(event.target.files[0]);
	}
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
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
	let form_data = $('#product_cart').serialize();		
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
}

//중고 장바구니
function submitUsedProduct(){
	let form_data = $('#used_product').serialize();		
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

function submitOrder(){
	let total=0;
	let number=parseInt($('#fixed').val());
	let fixedprice =parseInt($('#number').val());	
	total+=(fixedprice*number);					
			//기본 이벤트 제거
			event.preventDefault();
						
			let cartInfo=[];
			let book_info = {};
			book_info.cart_quantity = $('#number').val();
			book_info.store_product_pricestandard = $('#price').val();
			book_info.store_product_num = $('#product').val();

			cartInfo.push({...book_info});
			console.log(cartInfo); 
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
		$('#deli_form')[0].reset();
	}
}
















