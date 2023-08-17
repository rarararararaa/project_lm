<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reservation.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/LM_bookDetail.css">
</head>
<body>
<div id="LM_bookDetail_main">
	<p id="bookClassName"> ${ className }</p>
	<div class="bookDetail_info">
		<ul>
			<li>
				<img src="${ book.lib_product_bookImageUrl }" class="info-image">		
			</li>
			<li>
			<div id="info_title">
				<p>${book.lib_product_bookName}</p>
				<p>${book.lib_product_authors}</p>
			</div>
				<p>
			<!-- 분류 -->분류 : ${ className }
				</p>
			<!-- 분류 -->	
				<p>저자 : ${book.lib_product_authors}</p>
				<p>출판사 : ${book.lib_product_publisher}</p>	
				<p>발행연도 : ${book.lib_product_publication_year}년</p>	
				<p>ISBN : ${book.lib_product_isbn}</p>	
				<p>도서분류 : ${book.lib_product_description}</p>	
			</li>
		</ul>
	</div>
	<div class="LIB_description">
		<p class="de_title">도서 소개</p>
		<p>
			${book.lib_product_detail}
		</p>
	</div>
	<!-- 도서 리스트 -->
	<div class="bookDetail_book">
		<p>소장정보</p>
		<table>
			<tr>
				<th>NO.</th>
				<th>등록번호</th>
				<th>도서상태</th>
				<th>반납 예정일</th>
				<th>예약/신청</th>
			</tr>
			<!-- 반복문 -->
			<c:forEach var="bookList" items="${ list }" varStatus="status" begin="0">
			<tr>
				<td>${status.index+1}</td>
				<td>${bookList.callNumber}</td>
				<td>
					<c:if test="${bookList.lib_product_product_status == 0}">대출가능</c:if>
					<c:if test="${bookList.lib_product_product_status == 1}">대출중</c:if>
				</td>
				<td>
					<c:if test="${bookList.lib_product_product_status == 1}">
						${bookList.return_reg_deadline}
					</c:if>
				</td>
				<c:if test="${status.index == 0}">
					<td rowspan="${fn:length(list)}">
						<button class="book-btn" data-num=${bookList.lib_product_isbn}>예약하기</button>
					</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>