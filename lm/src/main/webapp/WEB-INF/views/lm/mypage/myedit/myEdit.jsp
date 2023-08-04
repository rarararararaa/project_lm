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
	<form:form modelAttribute="mypageVO" action="myEditMain.do"
		id="myEdit-form" class="myEdit-form">
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
						<div class="view-photo"></div>
						<input type="button" onclick="" value="사진 선택" id="upload-photo"
							class="edit-button">
						<!-- 선택 버튼 클릭 시 활성화 -->
						<input type="button" onclick="" value="취소" id="cancel-photo"
							class="edit-button"> <input type="button" onclick=""
							value="완료" id="submit-photo" class="edit-button">
					</div>
					<div class="id-form">
						<div class="view-data">아이디</div>
						<div class="view-data">${mem_id}</div>
					</div>
					<div class="passwd-form">
						<div class="view-data">비밀번호</div>
						<div class="view-data">**********</div>
						<input type="button" onclick="" value="비밀번호 변경" id="change-passwd"
							class="edit-button">
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div class="now-passwd">현재 비밀번호</div>
						<form:input path="mem_old_passwd" maxlength="20"
							placeholder="현재 비밀번호 입력" />
						<div class="new-passwd">신규 비밀번호</div>
						<form:input path="mem_new_passwd" maxlength="20"
							placeholder="신규 비밀번호 입력" />
						<div class="new-passwd2">신규 비밀번호 재입력</div>
						<form:input path="mem_new_passwd2" maxlength="20"
							placeholder="신규 비밀번호 재입력" />
						<input type="button" onclick="" value="취소" id="cancel-passwd"
							class="edit-button"> <input type="button" onclick=""
							value="완료" id="submit-passwd" class="edit-button">
					</div>
					<div class="name-form">
						<div class="view-data">이름</div>
						<div class="view-data">mem_name</div>
					</div>
					<div class="email-form">
						<div class="view-data">이메일</div>
						<div class="view-data">mem_email</div>
						<!-- 이메일 미인증 유저만 보임 -->
						<c:if test="${mem_auth == 3}">
							<input type="button" onclick="" value="이메일 인증" id="confirm-email"
								class="edit-button">
						</c:if>
						<input type="button" onclick="" value="이메일 변경" id="change-email"
							class="edit-button">
						<!-- 선택 버튼 클릭 시 활성화 -->
						<div>메일주소 입력 후 인증하기 버튼을 누르시면, 회원님의 이메일로 이메일 인증 번호가 적힌 메일이
							발송됩니다.</div>
						<div>아래에 인증 번호를 입력하시면 인증이 완료됩니다.</div>
						<form:input path="mem_new_email" maxlength="50"
							placeholder="이메일 주소 입력" />
						<input type="button" onclick="" value="인증" id="check-email"
							class="edit-button">
						<form:input path="mem_confirm_email" maxlength="50"
							placeholder="인증번호 입력" />
						<input type="button" onclick="" value="취소" id="cancel-email"
							class="edit-button"> <input type="button" onclick=""
							value="완료" id="confirm-email" class="edit-button">
					</div>
					<div class="cell-form">
						<div class="view-data">전화번호</div>
						<div class="view-data">mem_cell</div>
						<input type="button" onclick="" value="전화번호 변경"
							class="change-cell" class="edit-button">
						<!-- 선택 버튼 클릭 시 활성화 -->
						<form:input path="mem_old_cell" maxlength="50"
							placeholder="현재 전화번호 입력" />
						<form:input path="mem_new_cell" maxlength="50"
							placeholder="신규 전화번호 입력" />
						<input type="button" onclick="" value="취소" id="cancel-cell"
							class="edit-button"> <input type="button" onclick=""
							value="완료" id="confirm-cell" class="edit-button">
					</div>
					<div class="member-out">
						<a>회원 탈퇴</a>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>