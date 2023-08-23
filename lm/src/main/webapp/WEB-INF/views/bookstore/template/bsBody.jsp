<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookStoreMain.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
const myCarouselElement = document.querySelector('#myCarousel')

const carousel = new bootstrap.Carousel(myCarouselElement, {
  interval: 2000,
  touch: false
})
</script>

<style>
body {
  font-family: sans-serif;
  font-size: 16px;
  line-height: 1.5;
  color: #333;
  background-color: #fff;
}

h3 {
  font-size: 20px;
  margin-top: 0;
  margin-bottom: 10px;
}

table {
  width: 1200px;
  border-collapse: collapse;
  border: 1px solid;
}

td {
  padding: 10px;
}

img {
  max-width: 100%;
}

.main-body {
  margin: 0 auto;
  width: 1200px;
}

.page {
  text-align: center;
}

.product {
  display: flex;
  flex-direction: column;
}

.product img {
  width: 200px;
  margin-bottom: 10px;
}

.product .info {
  margin-bottom: 10px;
}

.product .info h3 {
  margin-bottom: 0;
}

.product .info .author-publisher {
  font-size: 12px;
}
a {
	text-decoration: none; /* 언더바 제거 */
	color: black;
}
a:hover {
	text-decoration: none; /* 호버링 시 언더바 제거 */
	color: #E19B50;
}
</style>

<div class="main-body">
	<div class="content">
			<!-- 메인 이미지 시작 -->
		<div class="container">
		
			<!-- 상단 링크 버튼 시작-->
			<div class="service-up">
				<jsp:include page="/WEB-INF/views/common/up_button.jsp" />
			</div>
			<!-- 상단 링크 버튼 끝-->
			
			<div id="carouselExample" class="carousel slide event" data-ride="carousel">
			<!-- indicator 시작 -->
			<div class="carousel-indicators">
				<button class="active" type="button" data-bs-target="#carouselExample" data-bs-slide-to="0"></button>
				<c:forEach var="c" items="${list}" varStatus="status" begin="0" end="${count}">
				<button type="button" data-bs-target="#carouselExample" data-bs-slide-to="${status.index+1}"></button>
				</c:forEach>
			</div>
			<!-- indicator 끝 -->
			<!-- 이미지 표시 영역 시작 -->
			<div class="carousel-inner">
				<!-- item 시작 -->
				<div class="carousel-item active">
					<p>
						오늘 출석하셨나요?<br>
						꾸준히 출석하면 최대 600포인트 지급!
					</p>
					<div class="carousel-caption">
					</div>
					<a href="${pageContext.request.contextPath}/bookstore/event/attendanceEvent.do">
					<img src="${pageContext.request.contextPath}/images/출석체크.png" class="d-block w-100">
					</a>
				</div>
				<!-- item 끝 -->
				<!-- item 시작 -->
				<c:forEach var="event" items="${list}" varStatus="status" begin="0" end="${count}">
				<div class="carousel-item">
					<p>${event.event_title}</p>
					<div class="carousel-caption">
					</div>
					<a href="${pageContext.request.contextPath}/bookstore/event/detail.do?event_board_num=${event.event_board_num}">
					<img src="${pageContext.request.contextPath}/bookstore/event/imageView.do?event_board_num=${event.event_board_num}&event_board_type=1" class="d-block w-100" style="max-height: 325.85px;">
					</a>
				</div>
				</c:forEach>
				<!-- item 끝 -->
				<!-- 이미지 선택 버튼 시작 -->
					<button class="carousel-control-prev" type="button" data-bs-target="#carouselExample"
					data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span>
						<span class="visually-hidden">Previous</span>
					</button>	
					
					<button class="carousel-control-next" type="button" data-bs-target="#carouselExample"
					data-bs-slide="next">
						<span class="carousel-control-next-icon"></span>
						<span class="visually-hidden">Next</span>
					</button>	
				<!-- 이미지 선택 버튼 끝 -->
			</div>
			<!-- 이미지 표시 영역 끝 -->
		</div>
		</div>
		<!-- 메인 이미지 끝 -->
		<!-- 인기도서 시작 -->
		<div class="best-book main-book">
			<span>요즘 HOT한 인기도서</span>
			<table>
				<tr>
				<c:forEach var="image" items="${best}">
						<td>
						<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${image.store_product_isbn13}">
							<img alt="" src="${image.store_product_cover}">
						</a>
						</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach var="Info" items="${best}">
					<td>
						<ul>
							<li>${Info.store_product_searchcategoryName}</li>
							<li>${Info.store_product_title}</li>
							<li>${Info.store_product_author}</li>
						</ul>
					</td>
				</c:forEach>
				</tr>
			</table>
		</div>
		<!-- 인기도서 끝 -->
		<!-- 신간 도서 시작 -->
		<div class="new-book main-book">
			<span>신간도서</span>
			<table>
				<tr>
				<c:forEach var="image" items="${newBook}">
						<td>
						<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${image.store_product_isbn13}">
							<c:if test="${image.store_product_cover == ' '}">
								<img src="${pageContext.request.contextPath}/images/noImage.png">
							</c:if>
							<img alt="" src="${image.store_product_cover}">
						</a>
						</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach var="Info" items="${newBook}">
					<td>
						<ul>
							<li>${Info.store_product_searchcategoryName}</li>
							<li>${Info.store_product_title}</li>
							<li>${Info.store_product_author}</li>
						</ul>
					</td>
				</c:forEach>
				</tr>
			</table>
		</div>
		<!-- 신간 도서 끝 -->
		<!-- 출판 예쩡 도서 시작 -->
		<div class="future-book main-book">
			<span>출판예정도서</span>
			<table>
				<tr>
				<c:forEach var="image" items="${futureBook}">
						<td>
						<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${image.store_product_isbn13}">
							<c:if test="${image.store_product_cover == ' '}">
								<img src="${pageContext.request.contextPath}/images/noImage.png">
							</c:if>
							<img alt="" src="${image.store_product_cover}">
						</a>
						</td>
				</c:forEach>
				</tr>
				<tr>
				<c:forEach var="Info" items="${futureBook}">
					<td>
						<ul>
							<li>${Info.store_product_searchcategoryName}</li>
							<li>${Info.store_product_title}</li>
							<li>${Info.store_product_author}</li>
						</ul>
					</td>
				</c:forEach>
				</tr>
			</table>
		</div>
		<!-- 출판 예정 도서 끝 -->
	</div>
<div>
</div>
</div>
<!-- 내용 끝 -->