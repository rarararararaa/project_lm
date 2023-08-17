<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                           <input type="checkbox" id="rating1" name="review_rating" class="star_radio" value="1">
                           <input type="checkbox" id="rating2" name="review_rating" class="star_radio" value="2">
                              <input type="checkbox" id="rating3" name="review_rating" class="star_radio" value="3">
						<input type="checkbox" id="rating4" name="review_rating" class="star_radio" value="4">
                           <input type="checkbox" id="rating5" name="review_rating" class="star_radio" value="5">
                           <input type="checkbox" id="rating6" name="review_rating" class="star_radio" value="6">
                           <input type="checkbox" id="rating7" name="review_rating" class="star_radio" value="7">
                           <input type="checkbox" id="rating8" name="review_rating" class="star_radio" value="8">
                              <input type="checkbox" id="rating9" name="review_rating" class="star_radio" value="9">
                              <input type="checkbox" id="rating10" name="review_rating" class="star_radio" value="10">
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
<!-- 리뷰 수정 다이얼로그 시작 -->
<div id="re_mopwd">
	<div class="modal_container">
		<form id="new_form2" enctype="multipart/form-data" action="/bookstore/review/reviewModify.do" method="post">
			<input type="hidden" name="store_product_isbn13" value="${product.store_product_isbn13}">
			<input type="hidden" name="store_product_num" value="${product.store_product_num}">
			<input type="hidden" id="review_num" name="review_num" value="" >
			<img src="${ pageContext.request.contextPath }/images/modal-btn.png" onclick="fnHidePop('re_mopwd')" class="modal-btn">
			<ul class="modal-ul">
				<li style="font-size:18px;font-weight:500;">
					${product.store_product_title}
				</li>
				<li class="review-rating">
					<img src="${product.store_product_cover}" width="70">
					<fieldset class="review_rating" >
					<div class="starpoint_box">
                              <label class="label_star" for="rating11" title="0.5점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating12" title="1점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating13" title="1.5점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating14" title="2점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating15" title="2.5점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating16" title="3점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating17" title="3.5점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating18" title="4점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating19" title="4.5점">
                              	<span class="blind">
                              	</span>
                              </label>
                              <label class="label_star" for="rating20" title="5점">
                              	<span class="blind">
                              	</span>
                              </label>
                           <input type="checkbox" id="rating11" name="review_rating" class="star_radio" value="1">
                           <input type="checkbox" id="rating12" name="review_rating" class="star_radio" value="2">
                              <input type="checkbox" id="rating13" name="review_rating" class="star_radio" value="3">
						<input type="checkbox" id="rating14" name="review_rating" class="star_radio" value="4">
                           <input type="checkbox" id="rating15" name="review_rating" class="star_radio" value="5">
                           <input type="checkbox" id="rating16" name="review_rating" class="star_radio" value="6">
                           <input type="checkbox" id="rating17" name="review_rating" class="star_radio" value="7">
                           <input type="checkbox" id="rating18" name="review_rating" class="star_radio" value="8">
                              <input type="checkbox" id="rating19" name="review_rating" class="star_radio" value="9">
                              <input type="checkbox" id="rating20" name="review_rating" class="star_radio" value="10">
                              <span class="starpoint_bg"></span>
                        		</div>
					<textarea rows="3" cols="50" name="review_content" id="review_content2"
							  style="resize:none;" placeholder="후기를 작성해주세요."></textarea>
                        		</fieldset>
				</li>
				<li>
				</li>
				<li>
					<label for="review_image2">
						<div class="file-button"></div>
					</label>
					<div id="image_container2"></div>
					<input type="file" name="upload" id="review_image2"
					accept="image/gif,image/png,image/jpeg" onchange="setThumbnail2();"> 									
				</li>
			</ul>
			
			<div class="align-center">
				<input id="review_button2" type="button" class="sm-button review_submit_btn" value="리뷰 수정">
			</div>
		</form>
	</div>
</div>
<!-- 리뷰 수정 다이얼로그 끝 -->
