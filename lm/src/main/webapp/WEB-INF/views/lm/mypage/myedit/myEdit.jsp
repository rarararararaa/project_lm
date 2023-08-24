<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/myEdit.js"></script>
	<form:form modelAttribute="myPageVO" action="myEditMain.do"
		id="myEdit-form" class="myEdit-form" enctype="multipart/form-data">
		<!-- 파라미터 세팅 -->
		<%
		pageContext.setAttribute("lo", request.getParameter("lo"));
		%>
		<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
		<input type="hidden" name="lo" value="${lo}" />
		<div class="container" id="container_edit">
			<div class="left-div">
				<div class="page-name">내 정보</div>
				<ul class="menu-box">
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/main/myPageMain.do?lo=${lo}">주문
							내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/asklist/askListMain.do?lo=${lo}">문의
							내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do?lo=${lo}">대출/반납
							내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/wantbooklist/wantBookListMain.do?lo=${lo}">희망도서
							신청 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/programapplylist/programApplyListMain.do?lo=${lo}">프로그램
							신청 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/facilityapplylist/facilityApplyListMain.do?lo=${lo}">시설
							이용 신청 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/bookreservationlist/bookReservationListMain.do?lo=${lo}">도서
							예약 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/booklostlist/bookLostListMain.do?lo=${lo}">분실
							도서 신고 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/eventparticipatelist/eventParticipateListMain.do?lo=${lo}">이벤트
							참여 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/usedbookapplylist/usedBookApplyListMain.do?lo=${lo}">중고
							도서 등록 내역</a></li>
				</ul>
			</div>
			<div class="right-div">
				<div class="list-name">
					<h2>회원 정보 수정</h2>
				</div>
				<div class="right-div-form">
					<!-- 폼 시작 -->
					<div class="photo-form">
						<div class="view-photo">
							<img
								src="${pageContext.request.contextPath}/lm/mypage/myedit/photoView.do?mem_num=${mem_num}"
								class="view-photo">
						</div>
						<input type="button" onclick="ShowUploadPhoto();" value="사진 변경"
							id="upload-photo" class="edit-button-photo">
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id=input-area-photo>
							<input type="file" id="upload-photo-file"
								class="edit-button-photo" name="upload"
								accept="image/gif,image/png,image/jpeg"> <br>
							<form:button class="edit-button" id="photo_confirm">확인</form:button>
							<input type="button" onclick="HideUploadPhoto();" value="취소"
								id="cancel-photo" class="edit-button-photo">
						</div>
						<!-- 끝 -->
					</div>
					<hr class="hr-edit">
					<div class="id-form">
						<div class="view-data-title">아이디</div>
						<div class="view-data">${mem_id}</div>
					</div>
					<hr class="hr-edit">
					<div class="passwd-form">
						<div class="passwd-btn-form">
							<div class="view-data-title">비밀번호</div>
							<div class="view-data">**********</div>
							<input type="button" onclick="ShowChangePasswd();"
								value="비밀번호 변경" id="change-passwd" class="edit-button">
						</div>
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-passwd" class="input-area">
							<div class="input-area-form">
								<div class="view-data-title2">현재 비밀번호</div>
								<form:password path="mem_old_passwd" maxlength="20"
									placeholder="현재 비밀번호 입력" class="input-button" id="pw1" />
								<span id="message_old_passwd"></span>
							</div>
							<div class="input-area-form">
								<div class="view-data-title2">신규 비밀번호</div>
								<form:password path="mem_new_passwd" maxlength="20"
									placeholder="신규 비밀번호 입력" class="input-button" id="pw2" />
								<span id="message_new_passwd"></span> <span
									id="message_new_passwd_status"></span>
							</div>
							<div class="input-area-form">
								<div class="view-data-title2">신규 비밀번호 재입력</div>
								<input type="password" maxlength="20" placeholder="신규 비밀번호 재입력"
									class="input-button" id="pw3" />
							</div>
							<div class="input-area-form">
								<form:button class="edit-button" id="passwd_confirm">확인</form:button>
								<span id="message_new_passwd2"></span> <input type="button"
									onclick="hideChangePasswd();" value="취소" id="cancel-passwd"
									class="edit-button">
							</div>
							<!-- 끝 -->
						</div>
					</div>
					<hr class="hr-edit">
					<div class="name-form">
						<div class="view-data-title">이름</div>
						<div class="view-data">${mypageVO.mem_name}</div>
					</div>
					<hr class="hr-edit">
					<div class="email-form">
						<div class="email-btn-form">
							<div class="view-data-title">이메일</div>
							<div class="view-data-email">${mypageVO.mem_email}</div>
							<input type="hidden" value="${mypageVO.mem_email}"
								id="hidden_email">
							<c:if test="${mem_auth==4}">
								<!-- 이메일 미인증 유저만 보임 auth=4-->
								<input type="button" onclick="showConfirmEmail();"
									value="이메일 인증" id="confirm-email" class="edit-button">
							</c:if>
							<input type="button" onclick="showChangeEmail();" value="이메일 변경"
								id="change-email" class="edit-button">
						</div>
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-email" class="input-area">
							<div>메일주소 입력 후 인증하기 버튼을 누르시면, 회원님의 이메일로 이메일 인증 번호가 적힌 메일이
								발송됩니다.</div>
							<div id="email-tip">아래에 인증 번호를 입력하시면 인증이 완료됩니다.</div>
							<input id="mem_email" maxlength="50" placeholder="이메일 주소 입력"
								class="input-button" /> <input type="button" value="인증"
								id="check-email" class="edit-button" onclick="emailCheck();">
							<input id="mem_confirm_email" maxlength="50"
								placeholder="인증번호 입력" class="input-button" /> <input
								type="button" value="확인" id="confirm-email2" class="edit-button"
								onclick="emailConfirm();"> <input type="button"
								onclick="hideConfirmEmail();" value="취소" id="cancel-email"
								class="edit-button">
						</div>

						<!-- 끝 -->
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-email2" class="input-area">
							<form:input path="mem_old_email" maxlength="50"
								placeholder="현재 이메일 입력" class="input-button" />
							<form:input path="mem_new_email" maxlength="50"
								placeholder="신규 이메일 입력" class="input-button" />
							<form:button class="edit-button" id="email_confirm">확인</form:button>
							<input type="button" onclick="hideChangeEmail();" value="취소"
								id="cancel-email" class="edit-button">
						</div>
						<!-- 끝 -->
					</div>
					<hr class="hr-edit">
					<div class="cell-form">
						<div class="cell-btn-form">
							<div class="view-data-title">전화번호</div>
							<div class="view-data-cell">${mypageVO.mem_cell}</div>
							<input type="hidden" value="${mypageVO.mem_cell}"
								id="hidden_cell"> <input type="button"
								onclick="showChangecell();" value="전화번호 변경" id="change-cell"
								class="edit-button">
						</div>
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-cell" class="input-area">
							<form:input path="mem_old_cell" maxlength="50"
								placeholder="현재 전화번호 입력" class="input-button" id="cell1" />
							<form:input path="mem_new_cell" maxlength="50"
								placeholder="신규 전화번호 입력" class="input-button" id="cell2" />
							<form:button class="edit-button" id="cell_confirm">확인</form:button>
							<input type="button" onclick="hideChangecell();" value="취소"
								id="cancel-cell" class="edit-button">
						</div>
					</div>
					<hr class="hr-edit">
					<div class="home-form">
						<div id="home-form-first">
							<div id="button-form">
								<input type="button" onclick="showAddHome();" value="주소지 추가"
									id="show-home" class="edit-button"> <input
									type="button" onclick="showEditHome();" value="주소지 관리"
									id="show-edit-home" class="edit-button">
							</div>
							<div class="page-input-box" id="pib1">
								<!-- 주소지 별명 입력 -->
								<div class="view-data-title">주소지 별명</div>
								<form:input path="home_title" maxlength="50"
									class="input-button" />
								<span id="message_title_null"></span>
							</div>
							<div class="page-input-box" id="pib2">
								<!-- 주소지 전화번호 입력 가능-->
								<div class="view-data-title">전화번호</div>
								<form:input path="home_cell" maxlength="13" class="input-button" />
							</div>
							<div class="page-input-box" id="pib3">
								<!-- 주소지 이름 입력 가능-->
								<div class="view-data-title">수령인 이름</div>
								<form:input path="home_name" maxlength="10" class="input-button" />
							</div>
							<div class="page-input-box" id="pib4">
								<!-- 우편번호 입력 불가능-->
								<div class="view-data-title">우편번호</div>
								<form:input path="home_zipcode" maxlength="6"
									class="input-button" readonly="true" />
								<span id="message_zipcode_null"></span> <input type="button"
									onclick="execDaumPostcode()" value="우편번호 찾기"
									class="input-style-check">
							</div>
							<div class="page-input-box" id="pib5">
								<!-- 주소 입력 불가능 -->
								<div class="view-data-title">주소</div>
								<span id="message_address_null"></span>
								<form:input path="home_address" maxlength="150"
									class="input-button" readonly="true" />
							</div>
							<div class="page-input-box" id="pib6">
								<!-- 상세주소 입력 -->
								<div class="view-data-title">상세주소</div>
								<form:input path="home_address_detail" maxlength="150"
									class="input-button" />
								<span id="message_address_detail_null"></span>
							</div>
							<div id="button-form">
								<form:button class="edit-button" id="home_confirm">확인</form:button>
								<input type="button" onclick="hideAddHome();" value="취소"
									id="hide-home" class="edit-button">
							</div>

						</div>
						<div class="home-form-second">
							<input type="hidden" name="count" id="count" value="${length}" />
							<c:forEach var="list" items="${list}" varStatus="status">
								<div class="home-info-box" id="home_info_box${status.index}">
									<c:if test="${list.home_default==0}" >
										<input type="button" class="edit-home-info-detail-button" value="기본배송지"/>
									</c:if>
									<c:if test="${list.home_default!=0}">
										<form:button class="edit-home-info-detail-button" onclick="home_default(${list.home_num})">기본배송지 설정</form:button>
									</c:if>
									<div class="home-info-detail">
										<strong>이름</strong>
									</div>
									<div class="home-info-detail" id="home_name2">${list.home_name}</div>
									<div class="home-info-detail">
										<strong>주소지 전화번호</strong>
									</div>
									<div class="home-info-detail" id="home_cell2">${list.home_cell}</div>

									<div class="home-info-detail">
										<strong>배송지명</strong>
									</div>
									<div class="home-info-detail" id="home_title2">${list.home_title}</div>
									<div class="home-info-detail">
										<strong>배송지 정보</strong>
									</div>
									<div class="home-info-detail" id="home_zipcode">${list.home_zipcode}</div>
									<div class="home-info-detail" id="home_address">${list.home_address}</div>
									<div class="home-info-detail" id="home_address_detail">${list.home_address_detail}</div>
								</div>
							</c:forEach>
							<div id="button-form">
								<input type="button" onclick="hideEditHome();" value="취소"
									id="hide-edit-home" class="edit-button">
							</div>
						</div>
					</div>

					<hr class="hr-edit">
					<div class="member-out">
						<c:if test="${lo == 1}">
							<input type="button" value="회원 탈퇴"
								onclick="location.href='${pageContext.request.contextPath}/lm/mypage/memberout/memberOutMain.do?lo=1'">
						</c:if>
						<c:if test="${lo != 1}">
							<input type="button" value="회원 탈퇴"
								onclick="location.href='${pageContext.request.contextPath}/lm/mypage/memberout/memberOutMain.do?lo=2'">
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<!-- 우편번호 검색 시작 -->
	<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
	<div id="layer"
		style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
		<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
			id="btnCloseLayer"
			style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
			onclick="closeDaumPostcode()" alt="닫기 버튼">
	</div>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		// 우편번호 찾기 화면을 넣을 element
		var element_layer = document.getElementById('layer');

		function closeDaumPostcode() {
			// iframe을 넣은 element를 안보이게 한다.
			element_layer.style.display = 'none';
		}

		function execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J).
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								//(주의)address1에 참고항목이 보여지도록 수정
								// 조합된 참고항목을 해당 필드에 넣는다.
								//(수정) document.getElementById("address2").value = extraAddr;

							}
							//(수정) else {
							//(수정)    document.getElementById("address2").value = '';
							//(수정) }

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('home_zipcode').value = data.zonecode;
							//(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
							document.getElementById("home_address").value = addr
									+ extraAddr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("home_address_detail")
									.focus();

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
		function initLayerPosition() {
			var width = 300; //우편번호서비스가 들어갈 element의 width
			var height = 400; //우편번호서비스가 들어갈 element의 height
			var borderWidth = 5; //샘플에서 사용하는 border의 두께

			// 위에서 선언한 값들을 실제 element에 넣는다.
			element_layer.style.width = width + 'px';
			element_layer.style.height = height + 'px';
			element_layer.style.border = borderWidth + 'px solid';
			// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
			element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
					+ 'px';
			element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
					+ 'px';
		}
	</script>
	<!-- 우편번호 검색 끝 -->
</body>
</html>