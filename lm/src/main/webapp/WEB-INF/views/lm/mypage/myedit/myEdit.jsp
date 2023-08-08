<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<div class="container">
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
						href="${pageContext.request.contextPath}/lm/mypage/donatebooklist/donateBookListMain.do?lo=${lo}">책
							기증 신청 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/facilityapplylist/facilityApplyListMain.do?lo=${lo}">시설
							이용 신청 내역</a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/lm/mypage/bookreservationlist/bookReservationListMain.do?lo=${lo}">책
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
							<img src="${pageContext.request.contextPath}/lm/mypage/myedit/photoView.do?mem_num=${mem_num}" width="50" height="50"
								class="view-photo">
						</div>
						<input type="button" onclick="ShowUploadPhoto();" value="사진 변경"
							id="upload-photo" class="edit-button-photo">
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id=input-area-photo>
							<input type="file" id="upload-photo-file"
								class="edit-button-photo" name="upload"
								accept="image/gif,image/png,image/jpeg"> <br>
							<form:button class="edit-button">확인</form:button>
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
								<input type="password" id="nmew-passwd2" maxlength="20"
									placeholder="신규 비밀번호 재입력" class="input-button" id="pw3" />
							</div>
							<div class="input-area-form">
								<form:button class="edit-button">확인</form:button>
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
							<!-- 이메일 미인증 유저만 보임 3-->
							<input type="button" onclick="showConfirmEmail();" value="이메일 인증"
								id="confirm-email" class="edit-button"> <input
								type="button" onclick="showChangeEmail();" value="이메일 변경"
								id="change-email" class="edit-button">
						</div>
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-email" class="input-area">
							<div>메일주소 입력 후 인증하기 버튼을 누르시면, 회원님의 이메일로 이메일 인증 번호가 적힌 메일이
								발송됩니다.</div>
							<div id="email-tip">아래에 인증 번호를 입력하시면 인증이 완료됩니다.</div>
							<input id="mem_email" maxlength="50" placeholder="이메일 주소 입력"
								class="input-button" /> <input type="button" onclick=""
								value="인증" id="check-email" class="edit-button"> <input
								id="mem_confirm_email" maxlength="50" placeholder="인증번호 입력"
								class="input-button" />
							<form:button class="edit-button">확인</form:button>
							<input type="button" onclick="hideConfirmEmail();" value="취소"
								id="cancel-email" class="edit-button">
						</div>

						<!-- 끝 -->
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-email2" class="input-area">
							<form:input path="mem_old_email" maxlength="50"
								placeholder="현재 이메일 입력" class="input-button" />
							<form:input path="mem_new_email" maxlength="50"
								placeholder="신규 이메일 입력" class="input-button" />
							<form:button class="edit-button">확인</form:button>
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
							<input type="button" onclick="showChangecell();" value="전화번호 변경"
								class="edit-button">
						</div>
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div id="input-area-cell" class="input-area">
							<form:input path="mem_old_cell" maxlength="50"
								placeholder="현재 전화번호 입력" class="input-button" />
							<form:input path="mem_new_cell" maxlength="50"
								placeholder="신규 전화번호 입력" class="input-button" />
							<form:button class="edit-button">확인</form:button>
							<input type="button" onclick="hideChangecell();" value="취소"
								id="cancel-cell" class="edit-button">
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
</body>
</html>