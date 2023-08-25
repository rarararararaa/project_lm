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
	<form:form modelAttribute="myPageVO" action="memberOutMain.do" id="memberout-form" class="memberout-form">
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
				<div class="right-div-form" id="outform">
					<form:input path="mem_id" maxlength="15" placeholder="아이디를 입력하세요." />
					<form:errors path="mem_id" cssClass="error-color" />
					<form:password path="mem_passwd" maxlength="20"
						placeholder="비밀번호를 입력하세요." />
					<form:errors path="mem_passwd" cssClass="error-color" />
						<form:button class="outbtn">탈퇴</form:button>
						<input type="button" value="취소" class="outbtn"
							onclick="location.href='${pageContext.request.contextPath}/lm/mypage/myedit/myEditMain.do?lo=${lo}'">
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>