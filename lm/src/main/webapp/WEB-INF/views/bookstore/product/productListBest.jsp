<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productBest.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/productBest.js"></script>
<!-- swiper -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<div class="main-page">
	<b>베스트셀러</b>
	<!-- 월간 베스트 -->
	<div class="category-best best">	
		<p style="margin: 60px 0 0 0;">분야별 TOP 3</p>
		<!-- Slider main container -->
		<div class="swiper category">
			<!-- Additional required wrapper -->
			<div class="swiper-wrapper cate-wrapper">
				<!-- Slides -->
					<c:forEach var="cate" items="${category}">
					
					<div class="swiper-slide cate-top3">
						<div class="cate-list">
							${cate}
						</div>
						<div>
							<table>
							<c:forEach var="top" items="${top3}">
							<c:if test="${top.key == cate }">
							<tr>
								<td>
								<ul>
									<c:forEach var="topInfo" items="${top.value }">
									<li class="float-left">
										<img src="${topInfo.store_product_cover}"><br>
										${topInfo.store_product_author}
									</li>
									</c:forEach>
								</ul>
								</td>
							</tr>							
							</c:if>
							</c:forEach>
							</table>
						</div>
					</div>
					</c:forEach>
			</div>
			<!-- If we need pagination -->
			<div class="swiper-pagination"></div>
			<!-- If we need navigation buttons -->
			<!-- If we need scrollbar -->
			<div class="swiper-scrollbar"></div>
		</div>
			<!-- <div class="swiper-button-prev cate-prev"></div>
			<div class="swiper-button-next cate-next"></div> -->
		<!-- Slider main container -->	
	</div>
	
	<div class="months-best best">
		<p style="margin-bottom: 0">종합 월간 베스트</p>
		<!-- Slider main container -->
		<div class="swiper month">
			<!-- Additional required wrapper -->
			<div class="swiper-wrapper">
				<!-- Slides -->
				<c:forEach var="best" items="${bestList}">
				<div class="swiper-slide ${best.store_product_isbn13}">
				<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${best.store_product_isbn13}">
					<img src="${best.store_product_cover}"><br>
				</a>
				<div class="detail">
					<ul>
						<li>${best.store_product_title}</li>
						<li>
							${best.store_product_author} | ${best.store_product_publisher}
						</li>
					</ul>					
				</div>
				</div>
				</c:forEach>
			</div>
			<!-- If we need pagination -->
			<div class="swiper-pagination"></div>
			<!-- If we need navigation buttons -->
			<!-- If we need scrollbar -->
		</div>
			<div class="swiper-button-prev month-prev"></div>
			<div class="swiper-button-next month-next"></div>
		<!-- Slider main container -->	
	</div>
	<!-- 월간 베스트 -->
	<!-- 연간 베스트 -->
	<div class="year-best best">
		<p style="margin-bottom: 0">종합 연간 베스트</p>
		<!-- Slider main container -->
		<div class="swiper year">
			<!-- Additional required wrapper -->
			<div class="swiper-wrapper">
				<!-- Slides -->
				<c:forEach var="best" items="${bestYear}">
				<div class="swiper-slide ${best.store_product_isbn13}">
				<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${best.store_product_isbn13}">
					<img src="${best.store_product_cover}"><br>
				</a>
				<div class="detail">
					<ul>
						<li>${best.store_product_title}</li>
						<li>
							${best.store_product_author} | ${best.store_product_publisher}
						</li>
					</ul>					
				</div>
				</div>
				</c:forEach>
			</div>
			<!-- If we need pagination -->
			<div class="swiper-pagination"></div>
			<!-- If we need navigation buttons -->
			<!-- If we need scrollbar -->
		</div>
			<div class="swiper-button-prev year-prev"></div>
			<div class="swiper-button-next year-next"></div>
		<!-- Slider main container -->	
	</div>
	<!-- 연간 베스트 -->
</div>
