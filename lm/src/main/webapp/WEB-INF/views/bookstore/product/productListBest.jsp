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
	<img src="${pageContext.request.contextPath}/images/medals.png" class="medal">
	<b>
		베스트셀러
	</b>
	<!-- 월간 베스트 -->
	<div class="category-best best">	
		<p style="margin: 60px 0 0 0;">
			분야별 TOP 3
		</p>
		<!-- Slider main container -->
		<div class="swiper cate-best-swiper">
			<!-- Additional required wrapper -->
			<div class="swiper-wrapper cate-wrapper">
				<!-- Slides -->
					<c:forEach var="cate" items="${category}" varStatus="status">
					
					<div class="swiper-slide cate-top3">
						<div class="cate-list">
						<!-- 이전 버튼 -->
						<c:if test="${status.index == 0}">
							<div class="swiper-button-prev cate-prev" id="ca-btn">${category[21]}</div>
						</c:if>
						<c:if test="${status.index != 0}">
						<div class="swiper-button-prev cate-prev" id="ca-btn">${category[status.index-1]}</div>
						</c:if>
							${cate}<br>
						<!-- 다음 버튼 -->
						<c:if test="${status.index == 21}">
							<div class="swiper-button-next cate-next" id="ca-btn">${category[0]}</div>	
						</c:if>
						<c:if test="${status.index != 21}">
							<div class="swiper-button-next cate-next" id="ca-btn">${category[status.index+1]}</div>	
						</c:if>
						</div>
						<div class="cate-list-position">
							<table>
							<c:forEach var="top" items="${top3}">
							<c:if test="${top.key == cate }">
							<tr class="non-border clear-both">
								<td>
								<ul>
									<c:forEach var="topInfo" items="${top.value }" varStatus="status" begin="0">
									<li class="float-left cate-info-detail">
										<img src="${pageContext.request.contextPath}/images/crown_${status.index+1}.png" id="crown" style="width: 43px; height: 65px;"><br>
										<a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13=${topInfo.store_product_isbn13}">
										<img src="${topInfo.store_product_cover}"><br>
										</a>
										<div>
											${topInfo.store_product_author}
										</div>
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
		</div>
		<!-- Slider main container -->	
	</div>
	
	<div class="months-best best">
		<p style="margin-bottom: 0">종합 월간 베스트</p>
		<!-- Slider main container -->
		<div class="swiper month">
			<!-- Additional required wrapper -->
			<div class="swiper-wrapper m-y-wrapper">
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
			<div class="swiper-wrapper m-y-wrapper">
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
