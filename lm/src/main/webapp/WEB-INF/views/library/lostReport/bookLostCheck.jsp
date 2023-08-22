<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/libLostcheckStyle.css">
<body>
	<div class="lostPage">
		<h2>결제 완료</h2>
		<div>
			<table>
				<tr>
					<th>제목</th>
					<th>분실신고일</th>
					<th>도서가격</th>
				</tr>
				<tr>
					<td>${lostBook.product_bookName}</td>
					<td class="al-c"><fmt:formatDate value="${lostBook.lost_reg_date}" type="date" dateStyle="full"/></td>
					<td class="al-c">${lostBook.store_product_pricesales}</td>
				</tr>
			</table>
		</div>
		<div class="align-center">
		분실 신고 및 결제가 완료되었습니다. 추가 문의사항이 있다면 문의 게시판 또는 도서관에 직접 방문하여 문의해주세요
		</div>
	</div>
</body>
</html>