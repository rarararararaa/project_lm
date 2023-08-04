	$(function(){
		$('#findId-form').submit(function(){
			if($('#mem_name').val().trim()==''){
				$('#message_name').css('color', 'red').text('이름을 입력하세요.');
				$('#mem_name').val('').focus();
				return false;
			}
			if($('#mem_cell').val().trim()==''){
				$('#message_cell').css('color', 'red').text('전화번호를 입력하세요.');
				$('#mem_cell').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z가-힣]+$/.test($('#mem_name').val())){
			$('#message_name').css('color', 'red').text('이름은 영어와 한글만 입력 가능합니다.');
				$('#mem_name').val('').focus();
				return false;
			}
			if(!/^[0-9\-]+$/.test($('#mem_cell').val())){
			$('#message_cell').css('color', 'red').text('전화번호는 숫자와 -만 입력 가능합니다.');
				$('#mem_cell').val('').focus();
				return false;
			}
		});
	});