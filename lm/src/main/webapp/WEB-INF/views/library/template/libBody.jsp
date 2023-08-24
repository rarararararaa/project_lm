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
	
}

.library-recommend {
	width: 1200px;
	height: 500px;
	
	display:flex;
	flex-direction: column;
	
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

.facility-list {
	width:500px;
	
	display:flex;
	flex-direction:column;
	justify-content:center;
	align-items:center;
}
.library-facility-box span {
	margin-left:25px;
}
.facility-list a {
	font-size:25px;
	padding:10px;
}

.facility-list p {
	margin:0;
}
.facility-box {
	margin-top:50px;
	display:flex;
	flex-direction:row;
	justify-content:center;
}

.facility-list {
	font-size:15px;
}
</style>
<script type="text/javascript">
$(function() {
		/////////////////////////////광고 시작
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
		//4초마다 함수 적용...
		
		
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
		// 증가 시키기 끝
    	
    	setInterval(incrementPromotionImgNum, 4000);
		/////////////////////////////광고 끝
		
		/////////////////////////////실시간 시간 가져오기 시작
    	function currentTimeForOneSec(){
    		let ajaxUrl = '${pageContext.request.contextPath}/library/template/giveMeOneSec.do';
				$.ajax({
    		        url: ajaxUrl,
    		        type: 'post',
    		        success: function (param) {
    		        	$('#currentTimeContainer').text(param);
    		        },
    		        error: function () {
    		            alert('실패');
    		        }
    		    });
    	}
    	//1초마다 시간 가져오기...
    	//setInterval(currentTimeForOneSec,1000);
    	//초기 값 가져오기
    	currentTimeForOneSec();
    	/////////////////////////////실시간 시간 가져오기 끝
    	
    	/////////////////////////////추천 카테고리 시작
    	
    	/*
    	$('#recommend-01, #recommend-02, #recommend-03, #recommend-04').click(function() {
            // Remove ajax-active class from all recommendation links
            $('.ajax-active').removeClass('ajax-active');
			
            // Add ajax-active class to the clicked link
            $(this).addClass('ajax-active');
			
            // Add ">" symbol to the clicked link
            $(this).append(' >');
            
        });
    	*/
    	
    	
    	$('#recommend-01').mouseenter(function(){
    		let ajaxUrl = '${pageContext.request.contextPath}/library/template/top5container.do';
    		$('#recommend-contents').empty();
    		$('#recommend-title').empty();
    		$.ajax({
		        url: ajaxUrl,
		        type: 'post',
		        success: function (param) {
		        	$('#recommend-title').append('대출 TOP5');
		        	$(param.top).each(function (index, item) {
		                let output = '<div class="library-ajax">';
		                output += '<div class="library-ajax-contents">';
		                output += '<a href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?callNumber='+item.callNumber +'">';
		                output += '<img src="'+item.lib_product_bookimageurl+'">'
		                output += '</a>';
		               	output += '<span>' + item.lib_product_bookname + '</span>';
		               	output += '<span> 발행 년도 : ' + item.lib_product_publication_year + '</span>';
		               	output += '<span> 저자 : ' + item.lib_product_authors + '</span>';
		                output += '<span> 출판사 : ' + item.lib_product_publisher+ '</span>';
		               	output += '</div>';
		                output += '</div>';
		                $('#recommend-contents').append(output);
		            });
		        },
		        error: function () {
		            alert('실패');
		        }
		    });
    	});
    	
    	$('#recommend-02').mouseenter(function(){
    		let ajaxUrl = '${pageContext.request.contextPath}/library/template/recommendbook.do';
    		$('#recommend-contents').empty();
    		$('#recommend-title').empty();
			$.ajax({
		        url: ajaxUrl,
		        type: 'post',
		        success: function (param) {
		        	$('#recommend-title').append('추천 도서');
		        	$(param.recommend).each(function (index, item) {
		                let output = '<div class="library-ajax">';
		                output += '<div class="library-ajax-contents">';
		                output += '<a href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?callNumber='+item.callNumber +'">';
		                output += '<img src="'+item.lib_product_bookimageurl+'">'
		                output += '</a>';
		               	output += '<span>' + item.lib_product_bookname + '</span>';
		               	output += '<span> 발행 년도 : ' + item.lib_product_publication_year + '</span>';
		               	output += '<span> 저자 : ' + item.lib_product_authors + '</span>';
		                output += '<span> 출판사 : ' + item.lib_product_publisher+ '</span>';
		               	output += '</div>';
		                output += '</div>';
		                $('#recommend-contents').append(output);
		            });
		        },
		        error: function () {
		            alert('실패');
		        }
		    });
    	})
    	
    	$('#recommend-03').mouseenter(function(){
    		let ajaxUrl = '${pageContext.request.contextPath}/library/template/reviewbest.do';
    		$('#recommend-contents').empty();
    		$('#recommend-title').empty();
    		$.ajax({
		        url: ajaxUrl,
		        type: 'post',
		        success: function (param) {
		        	$('#recommend-title').append('Best 독후감');
		        	//$(param.news).each(function (index, item) {
		                let output = '<div class="library-ajax">';
		                output += '구현 중 입니다.'
		                output += '</div>';
		                $('#recommend-contents').append(output);
		            //});
		        },
		        error: function () {
		            alert('실패');
		        }
		    });
    	});
    	
    	$('#recommend-04').mouseenter(function(){
    		let ajaxUrl = '${pageContext.request.contextPath}/library/template/newbook.do';
    		$('#recommend-contents').empty();
    		$('#recommend-title').empty();
			$.ajax({
		        url: ajaxUrl,
		        type: 'post',
		        success: function (param) {
		        	$('#recommend-title').append('신간 도서');
		        	$(param.news).each(function (index, item) {
		        		let output = '<div class="library-ajax">';
		                output += '<div class="library-ajax-contents">';
		                output += '<a href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?callNumber='+item.callNumber +'">';
		                output += '<img src="'+item.lib_product_bookimageurl+'">'
		                output += '</a>';
		               	output += '<span>' + item.lib_product_bookname + '</span>';
		               	output += '<span> 발행 년도 : ' + item.lib_product_publication_year + '</span>';
		               	output += '<span> 저자 : ' + item.lib_product_authors + '</span>';
		                output += '<span> 출판사 : ' + item.lib_product_publisher+ '</span>';
		               	output += '</div>';
		                output += '</div>';
		                $('#recommend-contents').append(output);
		            });
		        },
		        error: function () {
		            alert('실패');
		        	}
		        	
		    	});
    		});
    	
    	
    	$('#recommend-01').mouseenter();
	});
</script>
<%-- <a href="${pageContext.request.contextPath}/library/template/testpage.do">Test Page</a> --%>
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
			<img src="${pageContext.request.contextPath}/images/time-open1.png" width="30px" height="30px">&nbsp;현재 시간 : (
			
			<p id="currentTimeContainer"></p>
			) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도서관 운영시간 09:00 ~ 18:00 (야간도서관 매주 수요일 18:00 ~ 21:00)
		</div>
		<hr class="horizon-hr-1200">
	</div>
	
	<div class="library-main-second-box">
		<div class="library-board-box">
			<span>공지사항 <a href="${pageContext.request.contextPath}/library/boardannounce/Userlist.do">+</a></span>
			<hr class="horizon-hr">
			<div class="striped-cover-div">
				<table class="striped-table">
					<c:forEach var="ann" items="${ann}">
						<tr>
							<td class="align-center"><a href="${pageContext.request.contextPath}/library/boardannounce/detailUser.do?notice_num=${ann.notice_num}">${ann.notice_title} (${ann.notice_hit})</a></td>
							<td class="align-center">${ann.notice_reg_date}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="library-lost-box">
			<span>분실물 목록 <a href="${pageContext.request.contextPath}/library/liblostitem/listUser.do">+</a></span>
			<hr class="horizon-hr">
			<div class="striped-cover-div">
				<table class="striped-table">
					<c:forEach var="lost" items="${lost}">
						<c:if test="${lost.item_status == 0 }">
						<tr>
							<td class="align-center"><a href="${pageContext.request.contextPath}/library/liblostitem/detailUser.do?item_num=${lost.item_num}">${lost.item_title} (${lost.item_hit})</a></td>
							<td class="align-center">${lost.item_reg_date}</td>
						</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="library-recommend">
		<div class="align-column">
			<div class="ajax-active" id="recommend-01">대출 TOP 5</div>
			<div id="recommend-02">추천 도서</div>
			<!-- <div id="recommend-03">Best 독후감</div> -->
			<div id="recommend-04">신간 도서</div>
		</div>
		<div id="recommend-title"></div>
		<hr class="horizon-hr">
		<div id="recommend-contents"></div>
	</div>
	<div class="library-facility-box">
		<span>편의 시설 <a href="${pageContext.request.contextPath}/library/facilityList.do">+</a></span>
		<hr class="horizon-hr">
		<div class="facility-box">
		<c:forEach var="facility" items="${faciList}">
			<div class="facility-list">
				<img src="${pageContext.request.contextPath}/library/imageView.do?facility_num=${facility.facility_num}" width="400" height="300">
				<a href="${pageContext.request.contextPath}/library/facApplyWrite.do?facility_num=${facility.facility_num}">${facility.facility_name}</a>
				<p>${facility.facility_content }</p>
			</div>
		</c:forEach>
		</div>
	</div>
</div>
