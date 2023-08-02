<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
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
				<p>출판사 : ${book.lib_product_pulisher}</p>	
				<p>발행연도 : ${book.lib_product_publicatoin_year}년</p>	
				<p>ISBN : ${book.lib_product_isbn}</p>	
				<p>도서분류 : ${book.lib_product_description}</p>	
			</li>
		</ul>
	</div>
	<div class="LIB_description">
		<p class="de_title">줄거리</p>
		<p>
			이 편지는 영국에서 최초로 시작되어 일년에 한바퀴를 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는 4일 안에 당신 곁을 떠나야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다. 복사를 해도 좋습니다. 혹 미신이라 하실지 모르지만 사실입니다.
			영국에서 HGXWCH이라는 사람은 1930년에 이 편지를 받았습니다. 그는 비서에게 복사해서 보내라고 했습니다.
			며칠 뒤에 복권이 당첨되어 20억을 받았습니다. 어떤 이는 이 편지를 받았으나 96시간 이내 자신의 손에서 떠나야 한다는 사실을 잊었습니다. 그는 곧 사직되었습니다.
			나중에야 이 사실을 알고 7통의 편지를 보냈는데 다시 좋은 직장을 얻었습니다. 미국의 케네디 대통령은 이 편지를 받았지만 그냥 버렸습니다. 결국 9일 후 그는 암살당했습니다.
			기억해 주세요. 이 편지를 보내면 7년의 행운이 있을 것이고 그렇지 않으면 3년의 불행이 있을 것입니다. 그리고 이 편지를 버리거나 낙서를 해서는 절대로 안됩니다. 7통입니다.
			이 편지를 받은 사람은 행운이 깃들것입니다. 힘들겠지만 좋은게 좋다고 생각하세요. 7년의 행운을 빌면서...
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
					<c:if test="${bookList.lib_product_product_status == 1}">2023-08-08</c:if>
				</td>
				<c:if test="${status.index == 0}">
					<td rowspan="${fn:length(list)}">
						<button class="book-btn">예약하기</button>
					</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>