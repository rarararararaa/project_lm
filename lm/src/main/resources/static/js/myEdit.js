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


//동적 버튼 설정
function ShowUploadPhoto() {
  document.getElementById("input-area-photo").style.display = "block";
  document.getElementById("upload-photo").style.display = "none";
  const element = document.querySelector('.photo-form');
  element.style.height = '500px';
}

function HideUploadPhoto() {
  document.getElementById("input-area-photo").style.display = "none";
  document.getElementById("upload-photo").style.display = "block";
  
}

function ShowChangePasswd() {
  document.getElementById("input-area-passwd").style.display = "flex";
  document.getElementById("change-passwd").style.display = "none";
  const element = document.querySelector('.passwd-form');
  element.style.height = '250px';
}

function hideChangePasswd() {
  document.getElementById("input-area-passwd").style.display = "none";
  document.getElementById("change-passwd").style.display = "block";
  const element = document.querySelector('.passwd-form');
  element.style.height = '100px';
  $('#pw1').val('').focus();
  $('#pw2').val('').focus();
  $('#pw3').val('').focus();
}

function showConfirmEmail() {
  document.getElementById("input-area-email").style.display = "block";
  document.getElementById("confirm-email").style.display = "none";
  document.getElementById("change-email").style.display = "none";
  const element = document.querySelector('.email-form');
  element.style.height = '300px';
}

function hideConfirmEmail() {
  document.getElementById("input-area-email").style.display = "none";
  document.getElementById("confirm-email").style.display = "block";
  document.getElementById("change-email").style.display = "block";
  const element = document.querySelector('.input-area-email');
  element.style.height = '100px';
}

function showChangeEmail() {
  document.getElementById("input-area-email2").style.display = "block";
  document.getElementById("change-email").style.display = "none";
  document.getElementById("confirm-email").style.display = "none";
}

function hideChangeEmail() {
  document.getElementById("input-area-email2").style.display = "none";
  document.getElementById("change-email").style.display = "block";
  document.getElementById("confirm-email").style.display = "block";
}

function showChangecell() {
  document.getElementById("input-area-cell").style.display = "block";
  document.getElementById("change-cell").style.display = "none";
    const element = document.querySelector('.cell-btn-form');
  element.style.height = '300px';
}

function hideChangecell() {
  document.getElementById("input-area-cell").style.display = "none";
  document.getElementById("change-cell").style.display = "block";
    const element = document.querySelector('.cell-btn-form');
  element.style.height = '100px';
}


