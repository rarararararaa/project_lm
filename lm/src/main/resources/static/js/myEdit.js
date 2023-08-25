//비밀번호 안전성 등급
$(document).ready(function() { 
	$('#mem_new_passwd').on('keydown', function() {

		let count=0; 
		let count1=0; 
		let count2=0; 
		let count3=0; 
		let count4=0; 
		let count5=0; 
	
		let mem_new_passwd = $('#mem_new_passwd').val();
	    let upperCaseCount; //대문자
	    let lowerCaseCount; //소문자
	    let numberCount; //숫자
	    let specialCharacterCount; //특수문자
	    let lengthCount; //길이
	    
			if(mem_new_passwd.search(/[ㄱ-ㅎㅏ-ㅣ]/g)!=-1){ //한글 입력 시 알리문구 출력 및 초기화
				$('#mem_new_passwd').val('').focus();
				$('#message_new_passwd').css('color', 'red').text('영문 대소문자, 숫자만 사용 가능');
				$('#message_new_passwd_status').css('color', 'red').text('');
				return false;
	    	}else{
	    
			    //입력된 문자열 처리
				if(mem_new_passwd.search(/[A-Z]/g)!=-1){
					upperCaseCount = mem_new_passwd.match(/[A-Z]/g).length;
				}else{
					upperCaseCount = 0;
				}
				if(mem_new_passwd.search(/[a-z]/g)!=-1){
					lowerCaseCount = mem_new_passwd.match(/[a-z]/g).length;
				}else{
					lowerCaseCount = 0;
				}
				if(mem_new_passwd.search(/[0-9]/g)!=-1){
					numberCount = mem_new_passwd.match(/[0-9]/g).length;
				}else{
					numberCount = 0;
				}
				if(mem_new_passwd.search(/[^A-Za-z0-9]/g)!=-1){
					specialCharacterCount = mem_new_passwd.match(/[^A-Za-z0-9]/g).length;
				}else{
					specialCharacterCount = 0;
				}
				lengthCount = mem_new_passwd.length;
				
				//개수별 점수 배정
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
				if (lengthCount >= 8 && lengthCount <= 11) {
				  count5 = 1;
				} else if (lengthCount >= 12 && lengthCount <= 15) {
				  count5 = 2;
				} else if (lengthCount >= 16) {
				  count5 = 3;
				} else {
				  count5 = 0;
				}
				count = count1 + count2 + count3 + count4 + count5;
				
				//점수와, 점수에 따른 등급 출력
				if(count>=1 && count<=3){
					$('#mem_new_passwd').css('color', 'red').text(count);	
					$('#mem_new_passwd_status').css('color', 'red').text('취약');
				}else if(count>=4 && count<=7){
					$('#mem_new_passwd').css('color', 'orange').text(count);
					$('#mem_new_passwd_status').css('color', 'orange').text('보통');
				}else if(count>=8 && count<=9){
					$('#mem_new_passwd').css('color', 'green').text(count);
					$('#mem_new_passwd_status').css('color', 'green').text('강함');	
				}else if(count==10){
					$('#mem_new_passwd').css('color', 'blue').text(count);
					$('#mem_new_passwd_status').css('color', 'blue').text('안전');
				}
				return true;
			}
	}); 
});

//이메일 인증
var Confirm = document.getElementById("Confirm");
var save = '';
function emailCheck(){
		if($('#mem_email').val().trim()==''){
			alert('이메일을 입력하세요');
			$('#mem_email').val('').focus();
			return false;
		}
		if(!/^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test($('#mem_email').val())){
			alert('이메일은 영문자와 숫자 및 특정 특수문자 입력 가능합니다.');
			$('#mem_email').val('').focus();
			return false;
		}
		var hidden_email = document.getElementById("hidden_email").value;
		var mem_email = $('#mem_email').val();
		if(hidden_email!==mem_email){
			alert('현재 이메일 불일치');
			return false;
		}
	    $.ajax({
	        url:'/emailCheck.do',
	        type:'post',
	        dataType:'json',
	        data:{'userEmail' : $('#mem_email').val()},
	        success: function(data){
	            save = data.result;
	            alert('인증번호 전송 완료.');
	        },
			error: function(){
				alert('네트워크 오류발생');
			}
	    });
}
function emailConfirm(){
	//.value 없으면 오브젝트로 받아옴 [object HTMLInputElement]
	var save2 = document.getElementById("mem_confirm_email").value;
	if(save!==save2){
		alert('인증번호가 일치하지 않습니다.');
	}else{
	    $.ajax({
	        url:'/emailCheckApply.do',
	        type:'post',
	        dataType:'json',
	        data:{'userEmailApply' : 'true'},
	        success: function(data){
	            alert('이메일 인증 완료');
	            document.getElementById("confirm-email").style.display = "none";
	            document.getElementById("input-area-email").style.display = "none";
	            document.getElementById("change-email").style.display = "block";
	        },
			error: function(){
				alert(data.resut);
			}
    	});
	}
}

//동적 버튼 설정
//1
var container_edit = 700;
function ShowUploadPhoto() {
  document.getElementById("input-area-photo").style.display = "block";
  document.getElementById("upload-photo").style.display = "none";
  const element = document.querySelector('.photo-form');
  element.style.height = '500px';
  const container = document.querySelector('.container');
  container_edit += 80;
  container.style.height = (container_edit)+'px';
}

function HideUploadPhoto() {
  document.getElementById("input-area-photo").style.display = "none";
  document.getElementById("upload-photo").style.display = "block";
   const element = document.querySelector('.photo-form');
  element.style.height = '500px';
    const container = document.querySelector('.container');
  container_edit -= 80;
  container.style.height = (container_edit)+'px';
}
//2
function ShowChangePasswd() {
  document.getElementById("input-area-passwd").style.display = "flex";
  document.getElementById("change-passwd").style.display = "none";
  const element = document.querySelector('.passwd-form');
  element.style.height = '250px';
    const container = document.querySelector('.container');
  container_edit += 200;
  container.style.height = (container_edit)+'px';
}

function hideChangePasswd() {
  document.getElementById("input-area-passwd").style.display = "none";
  document.getElementById("change-passwd").style.display = "block";
  const element = document.querySelector('.passwd-form');
  $('#pw1').val('').focus();
  $('#pw2').val('').focus();
  $('#pw3').val('').focus();
  element.style.height = '50px';
    const container = document.querySelector('.container');
  container_edit -= 200;
  container.style.height = (container_edit)+'px';
}
//3
function showConfirmEmail() {
  document.getElementById("input-area-email").style.display = "block";
  document.getElementById("confirm-email").style.display = "none";
  document.getElementById("change-email").style.display = "none";
  const element = document.querySelector('.email-form');
  element.style.height = '300px';
    const container = document.querySelector('.container');
  container_edit += 86;
  container.style.height = (container_edit)+'px';
}

function hideConfirmEmail() {
  document.getElementById("input-area-email").style.display = "none";
  document.getElementById("confirm-email").style.display = "block";
  document.getElementById("change-email").style.display = "block";
  const element = document.querySelector('.email-form');
element.style.height = '300px';
  const container = document.querySelector('.container');
  container_edit -= 86;
  container.style.height = (container_edit)+'px';
}
//4
function showChangeEmail() {
  document.getElementById("input-area-email2").style.display = "block";
  document.getElementById("change-email").style.display = "none";
  document.getElementById("confirm-email").style.display = "none";
  document.getElementById("input-area-email").style.display = "none";
    const element = document.querySelector('.email-form');
element.style.height = '300px';
  const container = document.querySelector('.container');
  container_edit += 37;
  container.style.height = (container_edit)+'px';
}

function hideChangeEmail() {
  document.getElementById("input-area-email2").style.display = "none";
  document.getElementById("change-email").style.display = "block";
  document.getElementById("confirm-email").style.display = "block";
    const element = document.querySelector('.email-form');
element.style.height = '300px';
    const container = document.querySelector('.container');
  container_edit -= 37;
  container.style.height = (container_edit)+'px';
}
//5
function showChangecell() {
  document.getElementById("input-area-cell").style.display = "block";
  document.getElementById("change-cell").style.display = "none";
    const element = document.querySelector('.cell-btn-form');
  element.style.height = '300px';
    const container = document.querySelector('.container');
  container_edit += 37;
  container.style.height = (container_edit)+'px';
}

function hideChangecell() {
  document.getElementById("input-area-cell").style.display = "none";
  document.getElementById("change-cell").style.display = "block";
    const element = document.querySelector('.cell-btn-form');
  element.style.height = '50px';
    const container = document.querySelector('.container');
  container_edit -=37;
  container.style.height = (container_edit)+'px';
}
function showAddHome(){
 document.getElementById("show-home").style.display = "none";
  document.getElementById("pib1").style.display = "flex";
  document.getElementById("pib2").style.display = "flex";
  document.getElementById("pib3").style.display = "flex";
  document.getElementById("pib4").style.display = "flex";
  document.getElementById("pib5").style.display = "flex";
  document.getElementById("pib6").style.display = "flex";
  document.getElementById("home_confirm").style.display = "block";
  document.getElementById("hide-home").style.display = "block";
   document.getElementById("show-edit-home").style.display = "none";
       const element = document.querySelector('.home-form');
  element.style.height = '400px';
    const container = document.querySelector('.container');
  container_edit += 300;
  container.style.height = (container_edit)+'px';
}
function hideAddHome(){
 document.getElementById("hide-home").style.display = "none";
  document.getElementById("pib1").style.display = "none";
  document.getElementById("pib2").style.display = "none";
  document.getElementById("pib3").style.display = "none";
  document.getElementById("pib4").style.display = "none";
  document.getElementById("pib5").style.display = "none";
  document.getElementById("pib6").style.display = "none";
  document.getElementById("home_confirm").style.display = "none";
  document.getElementById("show-home").style.display = "block";
   document.getElementById("show-edit-home").style.display = "block";
          const element = document.querySelector('.home-form');
  element.style.height = '50px';
    const container = document.querySelector('.container');
  container_edit -= 300;
  container.style.height = (container_edit)+'px';
}
function showEditHome(){
 document.getElementById("show-edit-home").style.display = "none";
 document.getElementById("hide-edit-home").style.display = "block";
 var count = document.getElementById("count").value;
 for(var i=0; i<count; i++){
 	document.getElementById("home_info_box"+i).style.display = "block";
 }
 document.getElementById("home-form-first").style.display = "none";
 document.getElementById("show-home").style.display = "none";
    const element = document.querySelector('.home-form');
  element.style.height = '380px';
    const container = document.querySelector('.container');
  container_edit += 300;
  container.style.height = (container_edit)+'px';
}
function hideEditHome(){
 document.getElementById("show-edit-home").style.display = "block";
 document.getElementById("hide-edit-home").style.display = "none";
   var count = document.getElementById("count").value;
 for(var i=0; i<count; i++){
 	document.getElementById("home_info_box"+i).style.display = "none";
 }
  document.getElementById("home-form-first").style.display = "block";
  document.getElementById("show-home").style.display = "block";
            const element = document.querySelector('.home-form');
  element.style.height = '50px';
    const container = document.querySelector('.container');
  container_edit -= 300;
  container.style.height = (container_edit)+'px';
}

function home_default(home_num){
	    $.ajax({
	        url:'/addDefaultHome.do',
	        type:'post',
	        dataType:'json',
	        data:{'home_num' : home_num},
	        success: function(data){
	            alert(data.result);
	        },
			error: function(){
				alert('네트워크 오류발생');
			}
	    });
}
$(function(){ 
	//이미지 처리 시작 ~~~~~~~~~~~~~~~~~
	//처음 화면에 보여지는 이미지 읽기
	let used_photo1_path = $('.used-photo1').attr('src');
	//let my_photo; //업로드하고자 선택한 이미지 저장
	let used_photo1;
	$('#used_photo1_upload').change(function(){
		used_photo1 = this.files[0];
		if(!used_photo1){
			$('.used-photo1').attr('src',used_photo1_path);
			return;
		}
		
		if(used_photo1.size > 1024* 1024){
			alert(Math.round(used_photo1.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.used-photo1').attr('src',used_photo1_path);
			$(this).val('');
			return;
		}
		
		//이미지 미리보기 처리
		let reader1 = new FileReader();
		reader1.readAsDataURL(used_photo1);
		reader1.onload=function(){
			$('.used-photo1').attr('src',reader1.result);
		};
		
	}); 
});

//예외처리
$(function(){
	//사진
		$('#photo_confirm').click(function(){
			if($('#upload-photo-file').val().trim()==''){
				alert('파일을 선택하세요');
				$('#upload-photo-file').val('').focus();
				return false;
			}
		});
	//비밀번호
		$('#passwd_confirm').click(function(){
			if($('#pw1').val().trim()==''){
				alert('현재 비밀번호를 입력하세요');
				$('#pw1').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9_!@#$%^&*()-+=|\\/?.,<>;:'"~`]{8,20}$/.test($('#pw1').val())){
				alert('비밀번호는 8~20자로 된 영문자와 특수문자 및 숫자만 입력 가능합니다.');
				$('#pw1').val('').focus();
				return false;
			}
			if($('#pw2').val().trim()==''){
				alert('신규 비밀번호를 입력하세요');
				$('#pw2').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9_!@#$%^&*()-+=|\\/?.,<>;:'"~`]{8,20}$/.test($('#pw2').val())){
				alert('비밀번호는 8~20자로 된 영문자와 특수문자 및 숫자만 입력 가능합니다.');
				$('#pw2').val('').focus();
				return false;
			}
			if($('#pw3').val().trim()==''){
				alert('신규 비밀번호 재입력을 입력하세요');
				$('#pw3').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9_!@#$%^&*()-+=|\\/?.,<>;:'"~`]{8,20}$/.test($('#pw3').val())){
				alert('비밀번호는 8~20자로 된 영문자와 특수문자 및 숫자만 입력 가능합니다.');
				$('#pw3').val('').focus();
				return false;
			}
			if($('#pw1').val() == $('#pw2').val() || $('#pw1').val() == $('#pw3').val()){
				alert('현재 비밀번호와 신규 비밀번호가 같습니다.');
				$('#pw3').val('').focus();
				$('#pw2').val('').focus();
				$('#pw1').val('').focus();
				return false;
			}
			if($('#pw2').val() != $('#pw3').val()){
				alert('신규 비밀번호와 신규 비밀번호 재입력이 다릅니다.');
				$('#pw3').val('').focus();
				$('#pw2').val('').focus();
				return false;
			}
			
		//통신
	    $.ajax({
	        url:'/passwdChangeApply.do',
	        type:'post',
	        dataType:'json',
	        data:{'mem_passwd' : $('#passwd').val()},
	        success: function(){
	            alert('비밀번호 변경 완료');
	        },
			error: function(){
				alert('네트워크 오류발생');
			}
    	});
		});
	//이메일 인증
		$('#confirm-email2').click(function(){
			if($('#mem_confirm_email').val().trim()==''){
				alert('인증번호를 입력하세요');
				$('#mem_confirm_email').val('').focus();
				return false;
			}
		});
	//이메일 변경
		$('#email_confirm').click(function(){
			if($('#mem_old_email').val().trim()==''){
				alert('현재 이메일을 입력하세요');
				$('#mem_old_email').val('').focus();
				return false;
			}
			if($('#mem_new_email').val().trim()==''){
				alert('신규 이메일을 입력하세요');
				$('#mem_new_email').val('').focus();
				return false;
			}

			var hidden_email = document.getElementById("hidden_email").value;
			var mem_old_email = $('#mem_old_email').val();
			if(hidden_email!==mem_old_email){
				$('#mem_old_email').val('').focus();
				alert('현재 이메일 불일치');
				return false;
			}
			if($('#mem_old_email').val() === $('#mem_new_email').val()){
				alert('현재 이메일과 신규 이메일이 같습니다.');
				$('#mem_new_email').val('').focus();
				$('#mem_old_email').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test($('#mem_old_email').val())){
				alert('이메일은 영문자와 숫자 및 특정 특수문자 입력 가능합니다.');
				$('#mem_old_email').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test($('#mem_new_email').val())){
				alert('이메일은 영문자와 숫자 및 특정 특수문자 입력 가능합니다.');
				$('#mem_new_email').val('').focus();
				return false;
			}
		});	
	//전화번호
	$('#cell_confirm').click(function(){
		if($('#cell1').val().trim()==''){
			alert('현재 전화번호를 입력하세요');
			$('#cell1').val('').focus();
			return false;
		}
		if($('#cell2').val().trim()==''){
			alert('신규 전화번호를 입력하세요');
			$('#cell2').val('').focus();
			return false;
		}
		if(!/^[0-9-]{10,13}$/.test($('#cell1').val())){
			alert('전화번호는 10~13자리로 된숫자와 -만 입력 가능합니다.');
			$('#cell1').val('').focus();
			return false;
		}
		if(!/^[0-9-]{10,13}$/.test($('#cell2').val())){
			alert('전화번호는 10~13자리로 된숫자와 -만 입력 가능합니다.');
			$('#cell2').val('').focus();
			return false;
		}
		var hidden_cell = document.getElementById("hidden_cell").value;
		if(hidden_cell!==$('#cell1').val()){
			alert('현재 전화번호 불일치');
			$('#cell1').val('').focus();
			return false;
		}
		if($('#cell1').val() == $('#cell2').val()){
			alert('현재 전화번호와 신규 전화번호가 같습니다.');
			$('#cell2').val('').focus();
			$('#cell1').val('').focus();
			return false;
		}
	})
});