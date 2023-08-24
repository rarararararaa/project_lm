function emailCheck(){
			if($('#mem_name').val().trim()==''){
				$('#message_name').css('color', 'red').text('이름을 입력하세요.');
				$('#mem_name').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z가-힣]+$/.test($('#mem_name').val())){
				$('#message_name').css('color', 'red').text('이름은 영어와 한글만 입력 가능합니다.');
				$('#mem_name').val('').focus();
				return false;
			}
			$('#message_name').css('color', 'red').text('');
			if($('#mem_id').val().trim()==''){
				$('#message_id').css('color', 'red').text('아이디를 입력하세요.');
				$('#mem_id').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9]+$/.test($('#mem_id').val())){
				$('#message_id').css('color', 'red').text('아이디는 영문 대소문자와 숫자만 입력 가능합니다.');
				$('#mem_id').val('').focus();
				return false;
			}
			$('#message_id').css('color', 'red').text('');
			if($('#mem_email').val().trim()==''){
			$('#message_email').css('color', 'red').text('이메일을 입력하세요.');
				$('#mem_email').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test($('#mem_email').val())){
			$('#message_email').css('color', 'red').text('이메일은 영문자와 숫자 및 특정 특수문자 입력 가능합니다.');
				$('#mem_email').val('').focus();
				return false;
			}
			$('#message_email').css('color', 'red').text('');
			    $.ajax({
			        url:'/findPasswdCheck.do',
			        type:'post',
			        dataType:'json',
			        data:{'mem_name' : $('#mem_name').val(),
			        'mem_id' : $('#mem_id').val(),
			        'mem_email' : $('#mem_email').val()},
			        success: function(data){
			        $('#mem_id').val('').focus();
			        $('#mem_email').val('').focus();
			        $('#mem_name').val('').focus();
			            alert(data.result);
			        },
					error: function(){
						alert('네트워크 오류발생');
				}
	    	});
	};