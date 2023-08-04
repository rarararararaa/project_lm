$(function(){
	//찜 선택여부
	function selectZzim(){
		$.ajax({
			url:'/bookstore/product/getZzim.do',
			type: 'post',
			data:{store_product_num:$('#output_zzim').attr('data-num')},
			dataType: 'json',
			success:function(param){
				displayZzim(param);
			},
			error:function(){
				alert('네트위크 오류 발생');
			}
		});
	}
	
	//찜 등록(삭제)이벤트 처리
	$('#output_zzim').click(function(event){
		event.preventDefault();
		let content=document.getElementsByClassName("wish-ico")[0];
		$.ajax({
			url: '/bookstore/product/writeZzim.do',
			type: 'post',
			data:{store_product_num:$('#output_zzim').attr('data-num')},
			dataType: 'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 좋아요를 눌러주세요');
				}else if(param.result == 'success'){
					content.classList.toggle("active");
				}else{
					alert('좋아요 표시 오류 발생');
				}
			},
			error:function(){
				alert('네트위크 오류 발생');
			}
		});
	});
	
	//찜 표시
	function displayZzim(param){
		let content=document.getElementsByClassName("wish-ico")[0];
		if(param.status=='yesFav'){
			content.classList.add("active");
		}else if(param.status=='noFav'){
			content.classList.remove("active");
		}else{
			alert('좋아요 표시 오류 발생');
		}

	}
	
	//초기 데이터 호출
	selectZzim();
	
});