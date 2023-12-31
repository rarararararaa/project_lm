<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/payment-modal.css">
<!-- 배송지 등록 모달 -->
<div class="modal re_pwd" id="re_pwd">
        <div class="modal_container">
            <div class="m_content">
                <div class="tit"><h2>배송지 등록</h2><img src="${ pageContext.request.contextPath }/images/modal-btn.png" onclick="fnHidePop('re_pwd')" class="modal-btn"></div>
                <div class="con">
                	<button class="deli-plus" onclick="fnShowPop('re_re_pwd')">배송지 추가</button>
                <!-- 배송지 목록 시작 -->
                	<div id="deli_list">
                	<c:if test="${empty home_list}">
                		<div class="non-deliInfo">
                			<img alt="" src="${pageContext.request.contextPath}/images/stop.png">
	                		<p>등록된 배송지가 없습니다.</p>
                		</div>
                	</c:if>
                	<c:if test="${!empty home_list}">
					<table id="deli_table">  
					<!-- 반복문 -->
					<c:forEach var="home" items="${home_list}">
						<tr>
							<td>
								<input type="radio" value="${home.home_num}" class="deli-default" name="check_deli"
								<c:if test="${home.home_default == 0}">checked="checked"</c:if> data-default = "${home.home_default}">
							</td>
							<td>
								<ul class="deliInfo">
									<c:if test="${home.home_default == 0}">
										<li class="default-color">기본 배송지</li>
									</c:if>
									<c:if test="${home.home_default > 0 }">
										<li>일반 배송지</li>
									</c:if>
									<li>${home.home_name }/${mem.mem_cell }</li>
									<li>
										[${home.home_zipcode}] ${home.home_address} ${home.home_address_detail}
									</li>
								</ul>
							</td>
							<td style="text-align: right;"  width="70">
								<button class="deli-btn modify-btn" id="modify_deli" onclick="fnShowPop('re_mo_pwd')" data-homenum="${home.home_num}">수정</button>
								<c:if test="${home.home_default > 0}">
									<button class="deli-btn delete-btn" id="delete_deli" data-homenum="${home.home_num}">삭제</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<!-- 반복문 -->						
					</table>
                	</c:if>
                	</div>
                	<div class="de">
					<input type="checkbox" name="home_default" value="0" id="home_default" class="de-home">기본 배송지로 설정
					</div>
					<input type="button" value="선택" id="deli_submit">
                </div><!-- end of con -->
            </div>
        </div>
   </div>
 <!-- 배송지 추가 모달 -->
<div class="modal re_pwd" id="re_re_pwd">
	<div class="modal_container deli-modal-con">
		<div class="m_content">
			 <div class="tit"><h2>배송지 추가</h2><img src="${ pageContext.request.contextPath }/images/modal-btn.png" onclick="fnHidePop('re_re_pwd')" class="modal-btn"></div>
			<hr size="1px" noshade="noshade" color="#DBDBDB">
			<form method="post" id="deli_form" class="deli-form">
				<ul>
					<li>
						<label for="deli_title">배송지명</label>
						<input type="text" id="deli_title" name="home_title" placeholder="최대 7자까지 자유롭게 입력가능" maxlength="7">
					</li>
					<li>
						<label for="recipient">받는 분</label>
						<input type="text" id="recipient" name="mem_name" placeholder="최대 7자까지 자유롭게 입력가능" maxlength="7">
						<input type="text" id="phone" name="mem_cell" placeholder="휴대폰 번호를 -없이 입력해 주세요." maxlength="13" oninput="autoHyphen(this)">
					</li>
					<li>
						<p>주소</p>
						<input type="button" onclick="execDaumPostcode(0)"
				            value="우편번호 찾기" class="default-btn" id="zipcode" data-zipcode="" data-test="1">
				        <input type="hidden" name="home_zipcode" id="home_zipcode">
						<input id="address1" name="home_address" type="text" readonly="readonly"/>
						<input id="address2" name="home_address_detail" type="text" maxlength="10"/>
					</li>
				</ul>
				<div>
					<input type="checkbox" name="home_default" value="0" id="home_default">기본 배송지로 설정
				</div>
					<input type="submit" value="저장" id="deli_submit">
			</form>
		</div>
	</div>
</div>
 <!-- 배송지 수정 모달 -->
<div class="modal re_pwd" id="re_mo_pwd">
	<div class="modal_container deli-modal-con">
		<div class="m_content">
			 <div class="tit"><h2>배송지 수정</h2><img src="${ pageContext.request.contextPath }/images/modal-btn.png" onclick="fnHidePop('re_mo_pwd')" class="modal-btn"></div>
			<hr size="1px" noshade="noshade" color="#DBDBDB">
			<form method="post" id="modify_form" class="deli-form">
				<ul>
					<li>
						<label for="deli_title">배송지명</label>
						<input type="text" id="deli_title" name="home_title" placeholder="최대 7자까지 자유롭게 입력가능" maxlength="7">
					</li>
					<li>
						<label for="recipient">받는 분</label>
						<input type="text" id="recipient" name="mem_name" placeholder="최대 7자까지 자유롭게 입력가능" maxlength="7" data-num=>
						<input type="text" id="phone" name="mem_cell" placeholder="휴대폰 번호를 -없이 입력해 주세요." maxlength="13" oninput="autoHyphen(this)">
					</li>
					<li>
						<p>주소</p>
						<input type="button" onclick="execDaumPostcode(1)"
				            value="우편번호 찾기" class="default-btn" id="zipcode">
				        <input type="hidden" name="home_zipcode" id="home_zipcode">
						<input id="address1" name="home_address" type="text" readonly="readonly"/>
						<input id="address2" name="home_address_detail" type="text" maxlength="10"/>
					</li>
				</ul>
				<div>
					<input type="checkbox" name="home_default" value="0" id="home_default">기본 배송지로 설정
				</div>
					<input type="submit" value="저장" id="deli_submit">
			</form>
		</div>
	</div>
</div>
<!-- 우편번호 검색 시작 -->
<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:999;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function execDaumPostcode(type) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    //(주의)address1에 참고항목이 보여지도록 수정
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //(수정) document.getElementById("address2").value = extraAddr;
                
                } 
                //(수정) else {
                //(수정)    document.getElementById("address2").value = '';
                //(수정) }
				//alert(addr + extraAddr);
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
//                var zipcode = document.getElementById("zipcode");
//                zipcode.setAttribute('data-zipcode', data.zonecode);
				if(type == 0){
					$('#deli_form').find('#home_zipcode').attr('value',data.zonecode);
					$('#deli_form').find('#address1').attr('value',addr + extraAddr);
					$('#deli_form').find('#address2').focus();
/* 	                document.getElementById("home_zipcode").value = data.zonecode;
	                document.getElementById("address1").value = addr + extraAddr;
	                document.getElementById("address2").focus(); */
					
				}else{
					$('#modify_form').find('#home_zipcode').attr('value',data.zonecode);
					$('#modify_form').find('#address1').val(addr + extraAddr);
					$('#modify_form').find('#address2').focus();
				}
								
                let modify = $('#modify_form').find('#home_zipcode').val();
                let insert = $('#deli_form').find('#home_zipcode').val();
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                // 커서를 상세주소 필드로 이동한다.

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>
<!-- 우편번호 검색 끝 -->












