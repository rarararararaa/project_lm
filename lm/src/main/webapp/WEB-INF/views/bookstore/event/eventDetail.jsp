<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시글 상세 시작 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/BsEventList.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bs.Event.quiz.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bs.Event.reply.js"></script>

<script>
    // 현재 날짜를 가져오는 함수
    function getCurrentDate() {
        var currentDate = new Date();
        return currentDate;
    }

    // 이벤트 종료 날짜를 가져오는 함수 (가정: "yyyy-MM-dd" 형식)
    function getEventEndDate() {
        var eventEndDate = new Date("${event.event_date_end}");
        return eventEndDate;
    }

    // 페이지 로드 시 실행되는 함수
    function checkEventDate() {
        var currentDate = getCurrentDate();
        var eventEndDate = getEventEndDate();

        // 날짜 비교
        if (currentDate > eventEndDate) {
            // 이벤트 종료 날짜가 현재 날짜보다 이전인 경우 textarea를 비활성화
            var replyTextarea = document.getElementById("reply_content");
            replyTextarea.value = "이벤트가 종료되어 댓글 작성이 불가능합니다.";
            document.getElementById("reply_content").disabled = true;
            document.getElementById("re_btn").disabled = true;
        }
    }
    
    // 페이지 로드 시 날짜 비교 함수 호출
    window.onload = checkEventDate;
</script>


<div class="detailPageMain">
	<h2>${event.event_title}</h2>
	<input type="hidden" name="event_board_category" value="${event.event_board_category}" id="event_board_category">
	<ul class="detail-info">
		<c:if test="${!empty event.event_modify_date}">
		<li>
			최근 수정일 : ${event.event_modify_date}
		</li>
		</c:if>
		<c:if test="${empty event.event_modify_date}">
		<li>
			작성일 : ${event.event_reg_date}
		</li>
		</c:if>
		<li>
			조회수 : ${event.event_hit}
		</li>
	</ul>
	<hr size="1" width="100%">
	
	<div class="detail-content">
		<!-- 첨부 이미지 -->
		<c:if test="${event.event_img_big != null}">
		<div class="img_big">
			<img src="imageView.do?event_board_num=${event.event_board_num}&event_board_type=2" width="800">
		</div>
		<hr size="1" noshade="noshade" class="hr-gr">
		</c:if>
		<!-- content 부분 -->
		<div class="content-out">${event.event_content}</div> 
	</div>
	
	<!-- 퀴즈 선택지 부분 -->
	<c:if test="${event.event_board_category == 2}">
	<div class="quiz">
		<div class="quiz-select">
			<div class="quiz-sel sel-1">
				<span data-quizSel="1" data-boardNum="${event.event_board_num}">${event.event_quiz_sel1}</span>
			</div>
			<div class="quiz-sel sel-2">
				<span data-quizSel="2" data-boardNum="${event.event_board_num}">${event.event_quiz_sel2}</span>
			</div>
		</div>
	</div>
	</c:if>
	
	<!-- 연관 상품 부분 -->
	<c:if test="${event.store_product_num > 0 }">
	<hr size="1" width="100%">
	<div>
		<h2>이벤트 연관 상품 안내</h2>
		<div>
			<div class="ev-product-img">
				<a href="/bookstore/product/productDetail.do?store_product_isbn13=${product.store_product_isbn13}"><img src="${product.store_product_cover}" width="130"></a>
			</div>
			<div class="ev-product-info">
				<div class="ev-product-title">
					<a href="/bookstore/product/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">${product.store_product_title}</a>
				</div>
				<!-- 
				<div class="ev-proeduct-price">
					<a href="/bookstore/product/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">${product.store_product_pricesales}</a>
				</div>
				 -->
			</div>
		</div>
	</div>
	</c:if>
	
	<hr size="1" width="100%">
	
	<!-- 댓글 -->
	<c:if test="${event.event_board_category == 1 }">
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<form id="re_form">
			<input type="hidden" name="event_board_num" value="${event.event_board_num}" id="event_board_num">
			<div class="rep-div">
				<div class="reply-box-f">
					<div class="re-textarea">
					<textarea rows="3" cols="50" name="reply_content" id="reply_content" class="rep-content"
			  		<c:if test="${empty mem_num}">disabled="disabled"</c:if>
			  		><c:if test="${empty mem_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			  		</div>
			  		
			  		<c:if test="${!empty mem_num}">
					<div id="re_first">
						<span class="letter-count">300/300</span>
					</div>
			</c:if>
			  	</div>
			  	
			  	<div id="re_second" class="align-right re-submit-btn">
					<input type="submit" id="re_btn"<c:if test="${empty mem_num}">disabled="disabled"</c:if> value="댓글">
				</div>
			</div>
		</form>
	</div>
	<hr size="1" noshade="noshade">
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="댓글 더보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="100" height="100">
	</div>
	<!-- 댓글 UI 끝 -->	
	
	</c:if>
	
	<div class="align-right">
		<c:if test="${!empty mem_num && mem_auth== 9}">
			<input type="button" value="수정" onclick="location.href='update.do?event_board_num=${event.event_board_num}'">
			<input type="button" value="삭제" id="delete_btn">
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
				delete_btn.onclick=function(){
					let choice = confirm('삭제하시겠습니까?');
					if(choice){
						location.replace('delete.do?event_board_num=${event.event_board_num}');
					}
				};
			</script>             
		</c:if>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>
<!-- 게시글 상세 끝 -->
