<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
 .find-books {
 	background-color: #f0f0f0;
 }
 
</style>
<script type="text/javascript">
$(function(){ 
	$('.find-books').prop('disabled', true); //product_title 비활성화 처리
	
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
	
	let used_photo2_path = $('.used-photo2').attr('src');
	
	let used_photo2;
	$('#used_photo2_upload').change(function(){
		used_photo2 = this.files[0];
		if(!used_photo2){
			$('.used-photo2').attr('src',used_photo2_path);
			return;
		}
		
		if(used_photo2.size > 1024* 1024){
			alert(Math.round(used_photo2.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.used-photo2').attr('src',used_photo2_path);
			$(this).val('');
			return;
		}
		
		//이미지 미리보기 처리
		let reader2 = new FileReader();
		reader2.readAsDataURL(used_photo2);
		reader2.onload=function(){
			$('.used-photo2').attr('src',reader2.result);
		};
		
	});
	/* 예시
	$('#register_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요');
			$('#id').val('').focus();
			return false;
		}
	*/
	$('#used_submit').click(function(){
		if($('#hidden_store_product_num').val()<=0 || $('#hidden_store_product_num').val() == '') {
			alert('책 이름은 필수 항목입니다!!!');
			return false;
		}
		
		if($('#used_photo1_upload').val().trim()=='') {
			alert('사진 2개를 모두 업로드 해 주세요!!');
			$('#used_photo1_upload').focus();
			return false;
		}
		
		if($('#used_photo2_upload').val().trim()==''){
			alert('사진 2개를 모두 업로드 해 주세요!!');
			$('#used_photo2_upload').focus();
			return false;
		}
		if($('.used-text-area').val().trim() == '') {
			alert('요청사항은 필수 항목입니다.!!!');
			$('.used-text-area').focus();
			return false;
		}
		
	
		if($('.used_price').val().trim()=='' || $('#used_price').val().trim() <=0 ) {
			alert('가격은 0원 이상 입력하세요!!!');
			$('.used_price').val(0).focus();
			return false;
		}
	})
	
});




</script>

<div class="used-contents">
	<span>책 등록하기</span>
	<form action="usedUpdateSubmit.do" id="used_form" method="post" enctype="multipart/form-data">
		<input type="hidden" id="used_product_num" value="${usedVO.used_product_num}" name="used_product_num">
		
		<ul>
			<li>
				<!-- 책 찾기 -->
				<label id="store_product_title">책이름</label>
				<input type="text" id="store_product_title" class="find-books" value="${usedVO.store_product_description}">
				<input type="button" onclick="execSearchProductcode()" value="책 찾기" class="used-default-btn">
				<input type="hidden" id="hidden_store_product_num" value="${usedVO.store_product_num}" name="store_product_num">
				
			</li>
			<li>
				<div>책 상태 선택</div>
				<select name="used_product_condition">
					<option value="1">하 (훼손 상태가 다소 있음)</option>
					<option value="2">중 (훼손 상태가 적음)</option>
					<option value="3">상 (훼손 상태가 거의 없음)</option>
				</select>
				
			</li>
			<li>
				
				
				<img src="${pageContext.request.contextPath}/bookstore/used/photoView01.do?used_product_num=${usedVO.used_product_num}" width="300" height="300" class="used-photo1" name="used-photo1">
				<div class="used-image-upload" id="used_photo1_btn"></div>
				<div id="used_photo1_choice">
					<input type="file" id="used_photo1_upload" accept="image/gif,image/png,image/jpeg" name="upload1">
				</div>
			</li>
			<li>
				<!-- 책 사진2 초기 값 search 필요-->
				<img src="${pageContext.request.contextPath}/bookstore/used/photoView02.do?used_product_num=${usedVO.used_product_num}" width="300" height="300" class="used-photo2">
				<div class="used-image-upload" id="used_photo2_btn"></div>
				<div id="used_photo2_choice">
					<input type="file" id="used_photo2_upload" accept="image/gif,image/png,image/jpeg" name="upload2">
				</div>
			</li>
			<li>
				<!-- 책 설명 -->
				<label id="used_product_info" >요청 사항 및 특이사항</label>
				<textarea name="used_product_info" class="used-text-area">${usedVO.used_product_info}</textarea>
				
			</li>
			<li>
				<!-- 기존 책 찾기 -->
				<label id="used_product_price" >판매 희망 가격</label>
				<input type="number" id="used_product_price" class="used-price" value="${usedVO.used_product_price}" name="used_product_price">
				
			</li>
		</ul>
			<input type="submit" class="used-default-btn" id="used_form" value="수정하기">
	</form>
	
</div>
	<script type="text/javascript">
		function execSearchProductcode() {
			let contextPath = "${pageContext.request.contextPath}"; //팝업 열기
			let popupUrl = contextPath + "/bookstore/used/usedSearchProductPopup.do";
			let child = window.open(popupUrl, "_blank", "width=600, height=1000");
			
		};
	</script>