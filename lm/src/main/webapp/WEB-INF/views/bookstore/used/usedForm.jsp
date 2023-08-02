<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
 .find-books {
 	background-color: #f0f0f0;
    
 }
</style>
<script type="text/javascript">
$(function(){ 
	$('.find-books').prop('disabled', true); //product_title 비활성화 처리
	

	//used_photo1 처리
	
	//수정 버튼 이벤트 처리
	/*
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide();
	});
	*/
	
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
	//파일 업로드 처리
	/* 원래는 ajax 처리인데 실시간으로 올리는것이 아닌 DB로 올림..
	$('#photo1_submit').click(function(){
		//파일 업로드 취소 시 예외처리
		if($('#upload').val()==''){
			alert('파일을 선택하세요');
			$('#upload').focus();
			return;
		}
		
		//서버에 파일 전송
		
		let form_data = new FormData();
		form_data.append('upload',my_photo);
		
		$.ajax({
			url:'../member/updateMyPhoto.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false,
			processData:false,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					//교체된 이미지 저장
					photo_path = $('.my-photo').attr('src');
					$('#upload').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of click
	*/
	
	//취소 버튼 처리
	/*
	$('#photo1_reset').click(function(){
		$('.used-photo1').attr('src',photo_path);
		$('#upload').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();
	});
	*/
});




</script>

<div class="used-contents">
	<span>책 등록하기</span>
	<form:form modelAttribute="usedVO" action="usedForm.do" id="used_form" enctype="multipart/form-data">
		<ul>
			<li>
				<!-- 책 찾기 -->
				<form:label path="store_product_title" >책 이름</form:label>
				<form:input path="store_product_title" class="find-books"/>
				<input type="button" onclick="execSearchProductcode()" value="책 찾기" class="used-default-btn">
				<span id="message_product_name"></span>
				<input type="hidden" id="hidden_store_product_num" value="" name="store_product_num">
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<li>
				<div>책 상태 선택</div>
				<select name="used_product_condition">
					<option value="1">하</option>
					<option value="2">중</option>
					<option value="3">상</option>
				</select>
				
			</li>
			<li>
				<!-- 책 사진1 -->
				<img src="${pageContext.request.contextPath}/bookstore/used/usedPhoto1View.do" width="200" height="200" class="used-photo1">
				<div class="used-image-upload" id="used_photo1_btn">
					사진 1
				</div>
				<div id="used_photo1_choice">
					<input type="file" id="used_photo1_upload" accept="image/gif,image/png,image/jpeg" name="upload1">
				</div>
			</li>
			<li>
				<!-- 책 사진2 -->
				<img src="${pageContext.request.contextPath}/bookstore/used/usedPhoto2View.do" width="200" height="200" class="used-photo2">
				<div class="used-image-upload" id="used_photo2_btn">
					사진 2
				</div>
				<div id="used_photo2_choice">
					<input type="file" id="used_photo2_upload" accept="image/gif,image/png,image/jpeg" name="upload2">
				</div>
			</li>
			<li>
				<!-- 책 설명 -->
				<form:label path="used_product_info" >책 설명</form:label>
				<form:textarea path="used_product_info" />
				<span id="message_used_product_info"></span>
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<li>
				<!-- 기존 책 찾기 -->
				<form:label path="used_product_price" >판매 희망 가격</form:label>
				<form:input path="used_product_price" />
				<span id="message_used_product_price"></span>
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<form:button class="used-default-btn">제출하기</form:button>
		</ul>
	</form:form>
	
</div>
	<script type="text/javascript">
		function execSearchProductcode() {
			let contextPath = "${pageContext.request.contextPath}"; //팝업 열기
			let popupUrl = contextPath + "/bookstore/used/usedSearchProductPopup.do";
			let child = window.open(popupUrl, "_blank", "width=500, height=500");
		};
	</script>