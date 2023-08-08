<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.promotion-box {
	width: 1200px;
	height: 400px;
	overflow: hidden;
	position: relative;
}

.promotion-box img {
	display: none;
	object-fit: cover;
}

.promotion-box img.active {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	display: block;
}



.library-facility-box {
	width: 1200px;
	height: 500px;
	border: solid 1px green;
}

.library-recommend {
	width: 1200px;
	height: 400px;
	border: solid 1px green;
	display:flex;
}

.arrow-right {
	width: 40px;
	height: 40px;
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	margin-right: 100px;
	cursor: pointer;
	z-index: 2;
	right: 10px;
}

.arrow-right img {
	display: block;
	width: 40px;
	height: 40px;
}

.arrow-left {
	width: 40px;
	height: 40px;
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	margin-left: 100px;
	cursor: pointer;
	z-index: 2;
	left: 10px;
}

.arrow-left img {
	display: block;
	width: 40px;
	height: 40px;
	transform: rotate(180deg); /* 이미지를 180도 회전 */
}
</style>
<script type="text/javascript">
$(function() {
		//초기값 설정
		let promotion_img_num = 1;
		
		// 5초마다 promotion_img_num을 1씩 증가시키기
		function incrementPromotionImgNum() {
			$('#pro-0' + promotion_img_num).removeClass('active');
			promotion_img_num++;

			if (promotion_img_num > 5) {
				promotion_img_num = 1;
			}

			$('#pro-0' + promotion_img_num).addClass('active');
		}

		setInterval(incrementPromotionImgNum, 4000);
    	// 증가 시키기 끝
    	
    	//클릭 시 작동
    	$('.arrow-right').click(function(){
    		$('#pro-0'+promotion_img_num).removeClass('active');
    		promotion_img_num++;
    		
    		if(promotion_img_num >5) {
    			promotion_img_num = 1;
    		} 
    		
    		
    		$('#pro-0'+promotion_img_num).addClass('active');
    	});
    	
    	$('.arrow-left').click(function(){
    		$('#pro-0'+promotion_img_num).removeClass('active');
    		promotion_img_num--;
    		
    		if(promotion_img_num <1){
				promotion_img_num = 5;
			}
    		
    		$('#pro-0'+promotion_img_num).addClass('active');
    	});
    	
    	
    	
    	$('.ajax-active').append(' >');
    	
    	$('#recommend-01, #recommend-02, #recommend-03, #recommend-04').click(function() {
            // Remove ajax-active class from all recommendation links
            $('.ajax-active').removeClass('ajax-active');

            // Add ajax-active class to the clicked link
            $(this).addClass('ajax-active');

            // Add ">" symbol to the clicked link
            $(this).append(' >');
            
        });
	});
</script>
<div class="used-contents">
	<div class="promotion-box" id="promotion_docker">
		<div class="arrow-right">
			<img src="${pageContext.request.contextPath}/images/lib_icon_right01.svg">
		</div>
		<div class="arrow-left">
			<img src="${pageContext.request.contextPath}/images/lib_icon_right01.svg">
		</div>
		<a href="#"><img id="pro-01" src="${pageContext.request.contextPath}/images/lib-prom01.png" class="active"></a>
		<a href="#"><img id="pro-02" src="${pageContext.request.contextPath}/images/lib-prom02.png"></a>
		<a href="#"><img id="pro-03" src="${pageContext.request.contextPath}/images/lib-prom03.png"></a>
		<a href="#"><img id="pro-04" src="${pageContext.request.contextPath}/images/lib-prom04.png"></a>
		<a href="#"><img id="pro-05" src="${pageContext.request.contextPath}/images/lib-prom05.png"></a>
	</div>
	<div class="working-time-box">
		<div class="working-time-notice">
			<img src="${pageContext.request.contextPath}/images/time-open1.png" width="30px" height="30px">&nbsp;현재 시간 : (${currentTime}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도서관 운영시간 09:00 ~ 18:00(야간도서관 매주 수요일 18:00 ~ 21:00)
		</div>
		<hr class="horizon-hr-1200">
	</div>
	
	<div class="library-main-second-box">
		<div class="library-board-box">
			<span>공지사항 <a href="#">+</a></span>
			<hr class="horizon-hr">
			<div class="striped-cover-div">
				<table class="striped-table">
					<c:forEach var="ann" items="${ann}">
						<tr>
							<td class="align-center"><a href="detail.do?notice_num=${ann.notice_num}">${ann.notice_title} (${ann.notice_hit})</a></td>
							<td class="align-center">${ann.notice_reg_date}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="library-lost-box">
			<span>분실물 목록 <a href="#">+</a></span>
			<hr class="horizon-hr">
			<div class="striped-cover-div">
				<table class="striped-table">
					<c:forEach var="lost" items="${lost}">
						<c:if test="${lost.item_status == 0 }">
						<tr>
							<td class="align-center"><a href="detail.do?notice_num=${lost.item_num}">${lost.item_title} (${lost.item_hit})</a></td>
							<td class="align-center">${lost.item_reg_date}</td>
						</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<div class="library-facility-box">
		<span>편의시설 들어갈 자리</span>
	</div>
	<div class="library-recommend">
		<div class="align-column">
			<a href="#" class="ajax-active" id="recommend-01">TOP 5</a>
			<a href="#" id="recommend-02">추천 도서</a>
			<a href="#" id="recommend-03">Best 독후감</a>
			<a href="#" id="recommend-04">신간 도서</a>
		</div>
		
		<div class="recommend-contents">
		
		</div>
		
	</div>
	
	<%-- <span>메인 상품</span>
	<div class="search-box-byUsed">
		<c:forEach var="list" items="${list}">
			<div class="used-all-contents-div-width">
				<div class="used-all-contents-img">
					<a href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?callNumber=${list.callnumber}"><img src="${list.lib_product_bookimageurl}"></a>
				</div>
				<div class="used-all-contents-column">
					<div class="used-all-contents-box">책 제목 : ${list.lib_product_bookname }</div>
					<div class="used-all-contents-box">저자 : ${list.lib_product_authors} | 출판사 : ${list.lib_product_publisher}</div>
					<div class="used-all-contents-box">${list.lib_product_detail}</div>
				</div>
			</div>
		</c:forEach>
	</div> --%>
</div>
