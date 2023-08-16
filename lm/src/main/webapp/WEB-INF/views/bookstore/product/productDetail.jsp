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
				<div >
					<c:if test="${product.store_product_customerReviewRank!=0}">
						${product.store_product_searchcategoryName} ${product.store_product_customerReviewRank}위
					</c:if>
				</div>
				<div class="review-box">
					<div class="star-ratings">
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
				<img src="${product.store_product_cover}">
			</div>
			<div class="prod-detail-content">
				<div class="prod-price">
					<span>
						<c:if test="${product.store_product_discount>0 }">
							${product.store_product_discount} %
						</c:if>
					</span>
					<span class="price">
						<span class="val">
							<fmt:formatNumber value="${product.store_product_pricestandard}"/>
						</span>
						<span class="unit"> 원</span>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="tabs-area">
		<ul class="tabs">
			<li class="tab_item"><a href="#scrollProdInfo">상품정보</a></li>
			<li class="tab_item"><a href="#scrollProdReview">리뷰 (${product.store_product_ratingCount})</a></li>
			<li class="tab_item"><a href="#scrollProdClaim">교환/반품/품절</a></li>
		</ul>
	</div>
	<div class="content-wrap">
		<div class="left-area">
			<div id="scrollProdInfo">
				<div>
					<h2>책 소개</h2>
				</div>

			</div>
			<div id="scrollProdReview">
				<div class="first">
					<h2>리뷰</h2>
					<div class="right-area">
						<span>* 구매 후 리뷰 작성 시, e교환권 150원 적립 </span>
						 <input id="review" type="button" class="sm-button review" value="리뷰 작성" 
						 		data-num="${product.store_product_num}" onclick="fnShowPop('re_pwd')">
					<!-- 리뷰 등록 다이얼로그 시작 -->
					<div id="re_pwd">
						<div class="modal_container">
							<form id="new_form" enctype="multipart/form-data" action="/bookstore/review/reviewWrite.do" method="post">
								<input type="hidden" name="store_product_isbn13" value="${product.store_product_isbn13}">
								<input type="hidden" name="store_product_num" value="${product.store_product_num}">
								<img src="${ pageContext.request.contextPath }/images/modal-btn.png" onclick="fnHidePop('re_pwd')" class="modal-btn">
								<ul class="modal-ul">
									<li style="font-size:18px;font-weight:500;">
										${product.store_product_title}
									</li>
									<li class="review-rating">
										<img src="${product.store_product_cover}" width="70">
										<fieldset class="review_rating" >
										<div class="starpoint_box">
			                                <label class="label_star" for="rating1" title="0.5점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating2" title="1점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating3" title="1.5점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating4" title="2점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating5" title="2.5점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating6" title="3점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating7" title="3.5점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating8" title="4점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating9" title="4.5점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
			                                <label class="label_star" for="rating10" title="5점">
			                                	<span class="blind">
			                                	</span>
			                                </label>
				                            <input type="radio" id="rating1" name="review_rating" class="star_radio" value="1">
				                            <input type="radio" id="rating2" name="review_rating" class="star_radio" value="2">
			                                <input type="radio" id="rating3" name="review_rating" class="star_radio" value="3">
											<input type="radio" id="rating4" name="review_rating" class="star_radio" value="4">
				                            <input type="radio" id="rating5" name="review_rating" class="star_radio" value="5">
				                            <input type="radio" id="rating6" name="review_rating" class="star_radio" value="6">
				                            <input type="radio" id="rating7" name="review_rating" class="star_radio" value="7">
				                            <input type="radio" id="rating8" name="review_rating" class="star_radio" value="8">
			                                <input type="radio" id="rating9" name="review_rating" class="star_radio" value="9">
			                                <input type="radio" id="rating10" name="review_rating" class="star_radio" value="10">
			                                <span class="starpoint_bg"></span>
		                           		</div>
										<textarea rows="3" cols="50" name="review_content" id="review_content"
												  style="resize:none;" placeholder="후기를 작성해주세요."></textarea>
		                           		</fieldset>
									</li>
									<li>
									</li>
									<li>
										<label for="review_image">
											<div class="file-button"></div>
										</label>
										<div id="image_container"></div>
										<input type="file" name="upload" id="review_image"
										accept="image/gif,image/png,image/jpeg" onchange="setThumbnail();"> 									
									</li>
								</ul>
								
								<div class="align-center">
									<input id="review_button" type="button" class="sm-button review_submit_btn" value="리뷰 등록">
								</div>
							</form>
						</div>
					</div>
					<!-- 리뷰 등록 다이얼로그 끝 -->						 		
					</div>
				</div>
				<div class="review-box">
					<div class="box-left">
						<div class="star-ratings">
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
						<div class="star-ratings">
							<div class="star-ratings-fill" style="--rating: 2;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>0</div>
						</div>					
						<div class="star-ratings">
							<div class="star-ratings-fill" style="--rating: 4;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>0</div>
						</div>					
						<div class="star-ratings">
							<div class="star-ratings-fill" style="--rating: 6;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>0</div>
						</div>					
						<div class="star-ratings">
							<div class="star-ratings-fill" style="--rating: 8;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>0</div>
						</div>					
						<div class="star-ratings">
							<div class="star-ratings-fill" style="--rating: 10;">
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
								<span>⭐</span>
							</div>
							<div>0</div>
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
										<div class="star-ratings">
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
										<img src="${pageContext.request.contextPath}/bookstore/product/imageView.do?review_num=${review.review_num}" width="64" height="64">							
									</div>
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
				</div>
				<ul>
					<li>
						<div>
						</div>
						마이룸 > 주문관리 > 주문/배송내역 > 주문조회 > 반품/교환 신청, [1:1 상담 > 반품/교환/환불] 또는 고객센터 (1544-1900)<br>
						* 오픈마켓, 해외배송 주문, 기프트 주문시 [1:1 상담>반품/교환/환불] 또는 고객센터 (1544-1900)
					</li>
					<li>
						<div>
						</div>
						변심반품의 경우 수령 후 7일 이내,<br>
						상품의 결함 및 계약내용과 다를 경우 문제점 발견 후 30일 이내
					</li>
					<li>
						<div>
						</div>
						변심 혹은 구매착오로 인한 반품/교환은 반송료 고객 부담					
					</li>
					<li>
						<div>
						</div>
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
						<div>
						</div>
						공급사(출판사) 재고 사정에 의해 품절/지연될 수 있으며, 품절 시 관련 사항에 대해서는 이메일과 문자로 안내드리겠습니다.					
					</li>
					<li>
						<div>
						</div>
						1) 상품의 불량에 의한 교환, A/S, 환불, 품질보증 및 피해보상 등에 관한 사항은 소비자분쟁 해결 기준 (공정거래위원회 고시)에 준하여 처리됨<br>
						2) 대금 환불 및 환불지연에 따른 배상금 지급 조건, 절차 등은 전자상거래 등에서의 소비자 보호에 관한 법률에 따라 처리함					
					</li>
				</ul>
			</div>
		</div>
		<div class="right-area">
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
			<div>이벤트</div>
			<div>추천 도서</div>
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
