<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EESAMSAOH.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/productDetail.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/productZzim.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<div class="product-main">
	<div class="content-wrap">
		<div class="content-header">
			<div>${product.store_product_title}</div>
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="content">
			<div class="prod-detail-content">
				<div class="prod-author">${product.store_product_author}</div>
				<div class="prod-pub">${product.store_product_publisher} · ${product.store_product_pubdate}</div>
				<div class="review-box">
					<div class="star-ratings stop-dragging">
						<div class="star-ratings-fill" style="--rating: ${product.store_product_ratingScore};">
							<span>⭐</span>
							<span>⭐</span>
							<span>⭐</span>
							<span>⭐</span>
							<span>⭐</span>
						</div>
					</div>
					<div>
						<span class="review-score">
							${product.store_product_ratingScore} 
						</span>
						<span class="review-count">
							(${product.store_product_ratingCount}개의 리뷰)
						</span>
					</div>
				</div>
			</div>
			<div class="prod-detail-content">
				<img src="${product.store_product_cover}" width="380px">
			</div>
			<div class="prod-detail-content">
				<div class="prod-price">
					<span class="discount">
						${product.store_product_discount}% 
					</span>
					<span class="price">
						<span class="val">
							<fmt:formatNumber value="${product.store_product_pricestandard}"/>
						</span>
						<span class="unit">원</span>
					</span>
				</div>
				<div class="point">
					<div>
						적립 
					</div>
					<div class="point-value">
					<fmt:formatNumber value=" ${product.store_product_pricestandard*0.005}" pattern="0"/>P
					</div>
				</div>
				<div class="point">
					<div>
						배송안내
					</div>
					<div style="font-weight:400;font-size:15px;">
						도서 포함 50,000원 이상 무료배송
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="tabs-area">
		<ul class="tabs">
			<li class="tab_item"><a href="#scrollProdInfo" >상품정보</a></li>
			<li class="tab_item"><a href="#scrollProdReview">리뷰 (${product.store_product_ratingCount})</a></li>
			<li class="tab_item"><a href="#scrollProdClaim">교환/반품/품절</a></li>
		</ul>
	</div>
	<div class="content-wrap">
		<div class="left-area">
			<div id="scrollProdInfo">
				<h2>책 소개</h2>
				<p>이 책이 속한 분야</p>
				<div class="category">
				<c:forEach var="category" items="${categoryname }" varStatus="status">
					<a <c:if test="${status.first}">href="/bookstore/product/productCeteList.do?cate=${category}"</c:if>
					   <c:if test="${not status.first}">href="/bookstore/product/productCeteList.do?detail=${category}&cate=${categoryname[0]}"</c:if>>${category }</a><c:if test="${not status.last}"> > </c:if>
				</c:forEach>
				</div>
				<div class="description">
					${product.store_product_description }
				</div>
				<c:if test="${!empty product.store_product_seriesName }">
					<div>
						<h2>시리즈</h2>
						${product.store_product_seriesName }
					</div>
				</c:if>

			</div>
			<div id="scrollProdReview">
				<div class="first">
					<h2>리뷰</h2>
					<div class="right-area">
						<span>* 구매 후 리뷰 작성 시, e교환권 150원 적립 </span>
						 <input id="review" type="button" class="sm-button review" value="리뷰 작성" 
						 		data-num="${product.store_product_num}">
					<jsp:include page="product_modal.jsp"/>
					</div>
				</div>
				<div class="review-box">
					<div class="box-left">
						<div class="star-ratings stop-dragging">
							<div class="star-ratings-fill" style="--rating: ${product.store_product_ratingScore};">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>
								<span class="review-score">
									${product.store_product_ratingScore} 
								</span>
								<span class="review-count">
									(${product.store_product_ratingCount}개의 리뷰)
								</span>
							</div>
						</div>
					</div>
					<div class="box-right">
						<div class="star-ratings stop-dragging">
							<div class="star-ratings-fill" style="--rating: 2;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>${review_count_1} 개</div>
						</div>					
						<div class="star-ratings stop-dragging">
							<div class="star-ratings-fill" style="--rating: 4;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>${review_count_2} 개</div>
						</div>					
						<div class="star-ratings stop-dragging">
							<div class="star-ratings-fill" style="--rating: 6;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>${review_count_3} 개</div>
						</div>					
						<div class="star-ratings stop-dragging">
							<div class="star-ratings-fill" style="--rating: 8;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>${review_count_4} 개</div>
						</div>					
						<div class="star-ratings stop-dragging">
							<div class="star-ratings-fill" style="--rating: 10;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>${review_count_5} 개</div>
						</div>					
					</div>					
					<div>
					</div>
				</div>
				<div>
					<div class="review-area">
						<div class="tabs-wrap">
							<c:forEach var="review" items="${review}">
								<input type="hidden" value="${review.review_num }" name="review_num">
								<div class="review-header">
									<div class="left-area">
										${review.mem_id} / ${review.review_reg_date }
									</div>
									<div class="right-area">
										<div class="star-ratings stop-dragging">
											<div class="star-ratings-fill" style="--rating: ${review.review_rating};">
												<span>⭐</span>
												<span>⭐</span>
												<span>⭐</span>
												<span>⭐</span>
												<span>⭐</span>
											</div>
										</div>												
									</div>
								</div>
								<div class="review-content-wrap">
									<div class="left-area">
									 	${review.review_content}
									</div>
									<div class="right-area">
										<c:if test="${!empty review.review_image }">
											<img src="${pageContext.request.contextPath}/bookstore/product/imageView.do?review_num=${review.review_num}" width="64" height="64">
										</c:if>
									</div>
								</div>
								<div class="button-box">
								<c:if test="${sessionScope.mem_num ==review.mem_num}">
									<input type="button" class="small-button review-detail" value="수정" data-num="${review.review_num }">
									<input type="button" class="small-button" value="삭제"
									onclick="location.href='/bookstore/review/reviewDelete.do?store_product_num=${product.store_product_num}&review_num=${review.review_num }'">
								</c:if>
								</div>
										
							</c:forEach>
						</div>
						<div>
						</div>
					</div>
				</div>
				
			</div>
			<div id="scrollProdClaim">
				<div>
					<div>교환/반품/품절 안내</div>
					<input id="review" type="button" class="sm-button review" value="1:1문의" width="76%"
							onclick="location.href='/bookstore/service/mainDesk.do'">
				</div>
				<ul>
					<li>
						<div><b>반품/교환방법</b></div>
						마이룸 > 주문관리 > 주문/배송내역 > 주문조회 > 반품/교환 신청, [1:1 상담 > 반품/교환/환불] 또는 고객센터 (1544-1900)<br>
						* 오픈마켓, 해외배송 주문, 기프트 주문시 [1:1 상담>반품/교환/환불] 또는 고객센터 (1544-1900)
					</li>
					<li>
						<div><b>반품/교환가능 기간</b></div>
						변심반품의 경우 수령 후 7일 이내,<br>
						상품의 결함 및 계약내용과 다를 경우 문제점 발견 후 30일 이내
					</li>
					<li>
						<div><b>반품/교환비용</b></div>
						변심 혹은 구매착오로 인한 반품/교환은 반송료 고객 부담					
					</li>
					<li>
						<div><b>반품/교환 불가 사유</b></div>
						1) 소비자의 책임 있는 사유로 상품 등이 손실 또는 훼손된 경우<br>
						(단지 확인을 위한 포장 훼손은 제외)<br>
						2) 소비자의 사용, 포장 개봉에 의해 상품 등의 가치가 현저히 감소한 경우<br>
						예) 화장품, 식품, 가전제품(악세서리 포함) 등<br>
						3) 복제가 가능한 상품 등의 포장을 훼손한 경우<br>
						예) 음반/DVD/비디오, 소프트웨어, 만화책, 잡지, 영상 화보집<br>
						4) 소비자의 요청에 따라 개별적으로 주문 제작되는 상품의 경우 ((1)해외주문도서)<br>
						5) 디지털 컨텐츠인 eBook, 오디오북 등을 1회 이상 다운로드를 받았을 경우<br>
						6) 시간의 경과에 의해 재판매가 곤란한 정도로 가치가 현저히 감소한 경우<br>
						7) 전자상거래 등에서의 소비자보호에 관한 법률이 정하는 소비자 청약철회 제한 내용에 해당되는 경우					
					</li>
					<li>
						<div><b>상품 품절</b></div>
						공급사(출판사) 재고 사정에 의해 품절/지연될 수 있으며, 품절 시 관련 사항에 대해서는 이메일과 문자로 안내드리겠습니다.					
					</li>
					<li>
						<div><b>소비자 피해보상 환불 지연에 따른 배상</b></div>
						1) 상품의 불량에 의한 교환, A/S, 환불, 품질보증 및 피해보상 등에 관한 사항은 소비자분쟁 해결 기준 (공정거래위원회 고시)에 준하여 처리됨<br>
						2) 대금 환불 및 환불지연에 따른 배상금 지급 조건, 절차 등은 전자상거래 등에서의 소비자 보호에 관한 법률에 따라 처리함					
					</li>
				</ul>
			</div>
		</div>
		<div class="right-wrap">
			<c:if test="${!empty list}">
			<div>
				<h2>중고책</h2>
				<div class="used-product">
				<c:forEach var="list" items="${list}">
					<form id="used_product">
						<input type="hidden" name="used_product_num" value="${list.used_product_num }">
						<input type="hidden" name="store_product_num" value="${product.store_product_num }">
						<input type="hidden" name="store_product_status" value="1">
						<div>
							<img src="${product.store_product_cover}"width="94px">
							<c:if test="${list.used_product_condition ==1}">
								<b>상</b>
							</c:if>
							<c:if test="${list.used_product_condition ==2}">
								<b>중</b>
							</c:if>
							<c:if test="${list.used_product_condition ==3}">
								<b>하</b>
							</c:if>
							<input type="button" class="sm-button" value="장바구니" onclick="submitUsedProduct();">
						</div>
					</form>	
				</c:forEach>
				</div>
			</div>
			</c:if>		
			<div>
				<h2>이벤트</h2>
				<div class="event">
				<c:forEach var="event" items="${event}">
					<a class="wid" href="/bookstore/event/detail.do?event_board_num=${event.event_board_num}">
						<img src="/bookstore/event/imageView.do?event_board_num=${event.event_board_num}&event_board_type=1" width="258px">
					</a>
				</c:forEach>
				</div>
			</div>
			<c:if test="${!empty randomRecommend}">
			<div>
				<h2>${categoryname[1]}에 이런 책도 있어요!</h2>
				<div class="random">
				<c:forEach var="random" items="${randomRecommend}">
					<a href="/bookstore/product/productDetail.do?store_product_isbn13=${random.store_product_isbn13 }">
						<img src="${random.store_product_cover}" width="94px">
					</a>
				</c:forEach>
				</div>
			</div>
			</c:if>
		</div>
	</div>
		
	<div class="purchase-wrap">
		<div class="purchase-inner">
		<form id="product_cart">
			<div class="left-area">
				<span class="prod-info-title"></span>
				<span class="prod-info-price">
					<input id="fixed" type="hidden" value="${product.store_product_pricestandard}">
					<span id="total">
						<fmt:formatNumber value="${product.store_product_pricestandard}"/> 원
					</span>
				</span>
			</div>
			<div class="right-area">
				<div class="count-box">
					<span class="adjust-box">
						<button class="decrease adjust" id="decrease"></button>
						<input id="number" name="cart_quantity" type="number" value="1" autocomplete="off" readonly="readonly">
						<button class="increase adjust" id="increase"></button>
					</span>
				</div>
						<input type="hidden" name="store_product_num" value="${product.store_product_num}" id="product">
						<input type="hidden" name="store_product_pricestandard" value="${product.store_product_pricestandard}" id="price">
						<input type="hidden" name="store_product_stock" value="${product.store_product_stock}">
						<input type="hidden" name="store_product_title" value="${product.store_product_title}">
						
				<div class="prod-button-box">
						<button class="btn-line-gray" id="output_zzim"  data-num="${product.store_product_num}"><span class="wish-ico"></span></button>
				</div>
						<input type="button" class="btn-lg cart-btn" id="cart_btn" onclick="submitCart();" value="장바구니">
						<input type="button" class="btn-lg orderRightAway" id="orderRightAway" onclick="submitOrder();" value="바로구매">
			</div>
			</form>
		</div>
	</div>
</div>
