$(function(){
		//0:중복 체크 미실시, id 중복
		//1:id 미중복
	let checkId = 0;
	
	//아이디 중복 체크
	$('#confirmId').click(function(){
		if($('#mem_id').val().trim()==''){
			$('#message_id').css('color','red').text('아이디를 입력하세요');
			$('#mem_id').val('').focus();
		}
		
		$('#message_id').text('');//메시지 초기화
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{mem_id:$('#mem_id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					$('#message_id').css('color','#000').text('등록가능ID');
					checkId = 1;
				}else if(param.result == 'idDuplicated'){
					$('#message_id').css('color','red').text('중복된 ID');
					$('#mem_id').val('').focus();
					checkId = 0;
				}else if(param.result == 'notMatchPattern'){
					$('#message_id').css('color','red')
					                .text('6~15자의 영문 대소문자, 숫자만 사용 가능');
					$('#mem_id').val('').focus();
					checkId = 0;
				}else{
					checkId = 0;
					alert('ID중복체크 오류');
				}
			},
			error:function(){
				checkId = 0;
				alert('네트워크 오류 발생');
			}
		});		
	});//end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register-form #mem_id').keydown(function(){
		checkId = 0;
		$('#message_id').text('');
	});//end of keydown
	
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#register-form').submit(function(){
		if(checkId==0){
			$('#message_id').css('color','red')
			                .text('아이디 중복 체크 필수');
			if($('#mem_id').val().trim()==''){
				$('#mem_id').val('').focus();
			}
			return false;
		}
	});//end of submit
		
		
		//공백 입력 방지
		$('#register-form').submit(function(){
			if($('#mem_id').val().trim()==''){
				alert('아이디를 입력하세요');
				$('mem_#id').val('').focus();
				return false;
			}
			if($('mem_#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요');
				$('mem_#passwd').val('').focus();
				return false;
			}
			if($('#mem_name').val().trim()==''){
				alert('이름을 입력하세요');
				$('#mem_name').val('').focus();
				return false;
			}
			if($('#mem_cell').val().trim()==''){
				alert('전화번호를 입력하세요');
				$('#mem_cell').val('').focus();
				return false;
			}
			if($('#mem_zipcode').val().trim()==''){
				alert('우편번호를 입력하세요');
				$('#mem_zipcode').val('').focus();
				return false;
			}
			if($('#mem_address').val().trim()==''){
				alert('주소를 입력하세요');
				$('#mem_zipcode').val('').focus();
				return false;
			}
			if($('#mem_address_detail').val().trim()==''){
				alert('상세주소를 입력하세요');
				$('#mem_address_detail').val('').focus();
				return false;
			}
			if($('#mem_identify').val().trim()==''){
				alert('주민번호를 입력하세요');
				$('#mem_identify').val('').focus();
				return false;
			}
			//잘못된 입력 방지
			if(!/^[0-9\-]+$/.test($('#mem_cell').val())){
				alert('전화번호는 숫자와 -만 입력 가능합니다.');
				$('#mem_cell').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z가-힣]+$/.test($('#mem_name').val())){
				alert('이름은 영문자와 한글만 입력 가능합니다.');
				$('#mem_name').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9.@]+$/.test($('#mem_email').val())){
				alert('이메일은 영문자와 숫자 및 특정 특수문자 입력 가능합니다.');
				$('#mem_email').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9]+$/.test($('#mem_passwd').val())){
				alert('비밀번호는 영문자와 숫자만 입력 가능합니다.');
				$('#mem_passwd').val('').focus();
				return false;
			}
			if(!/^[0-9]+$/.test($('#mem_zipcode').val())){
				alert('우편번호는 숫자만 입력 가능합니다.');
				$('#mem_zipcode').val('').focus();
				return false;
			}
			if(!/^(\d{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/.test($('#mem_identify').val())){
				alert('올바른 주민번호를 입력하세요.');
				$('#mem_identify').val('').focus();
				return false;
			}
		});
		
		//회원 정보 등록 유효성 체크
		$('#register-form').submit(function(){
			let items = document.querySelectorAll(
					   'input[type="text"],input[type="mem_password"],input[type="mem_email"]');
			 for(let i=0;i<items.length;i++){
				 
			    if(items[i].value.trim()==''){
					let label = 
						document.querySelector(
					 'label[for="'+items[i].id+'"]');
					alert(label.textContent + ' 항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
			    }
			    
			    if(items[i].id == 'id' && 
			    	 !/^[A-Za-z0-9]{4,12}$/.test(
			    	             $('#mem_id').val())){
					alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자를 사용하세요');
					$('#mem_id').val('');
					$('#mem_id').focus();
					return false;
				}
			    
			    if(items[i].id == 'id' && 
			    		            idChecked == 0){
					alert('아이디 중복 체크 필수');
					return false;
			    }
			    if(items[i].email == 'email' && 
    		            emailChecked == 0){
		alert('이메일 중복 체크 필수');
		return false;
    }
			    if(items[i].cell == 'cell' && 
    		            cellChecked == 0){
		alert('전화번호 중복 체크 필수');
		return false;
    }
			}
			
			
		});
});


$(document).ready(function() {
    let count=0;
    let count1=0;
    let count2=0;
    let count3=0;
    let count4=0;
    let count5=0;
  $('#mem_passwd').on('keyup', function(e) {

    //키업 될 때 마다 카운트
    let upperCaseCount = ($('#mem_passwd').val()).match(/[A-Z]/g).length;
    let lowerCaseCount = ($('#mem_passwd').val()).match(/[a-z]/g).length;
    let numberCount = ($('#mem_passwd').val()).match(/[0-9]/g).length;
    let specialCharacterCount = ($('#mem_passwd').val()).match(/[^A-Za-z0-9]/g).length;
    let lengthCount = ($('#mem_passwd').val()).length;

    if (upperCaseCount == 1) {
      count1 = 1;
    } else if (upperCaseCount >= 2) {
      count1 = 2;
    } else {
      count1 = 0;
    }
    if (lowerCaseCount >= 1) {
      count2 = 1;
    } else {
      count2 = 0;
    }
    if (numberCount == 1) {
      count3 = 1;
    } else if (numberCount >= 2) {
      count3 = 2;
    } else {
      count3 = 0;
    }
    if (specialCharacterCount == 1) {
      count4 = 1;
    } else if (specialCharacterCount >= 2) {
      count4 = 2;
    } else {
      count4 = 0;
    }
    if (lengthCount >= 8 && lengthCount <= 10) {
      count5 = 1;
    } else if (lengthCount >= 11 && lengthCount <= 14) {
      count5 = 2;
    } else if (lengthCount >= 15 && lengthCount <= 18) {
      count5 = 3;
    } else {
      count5 = 0;
    }
    count = count1 + count2 + count3 + count4 + count5;
    $('#message_passwd').css('color', 'red').text(count);
    return true;
  });
});