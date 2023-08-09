<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시글 상세 시작 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/BsEventList.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/quizEvent.js"></script>

<div class="page-main">
	<h2>${event.event_title}</h2>
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
		<c:if test="${event.event_img_big != null}">
			<img src="imageView.do?event_board_num=${event.event_board_num}&event_board_type=2">
		</c:if>
		<b>${event.event_content}</b>
	</div>
	
	
	<c:if test="${event.event_board_category == 2}">
	<div>
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
				<div class="ev-proeduct-price">
					<a href="/bookstore/product/productDetail.do?store_product_isbn13=${product.store_product_isbn13}">${product.store_product_pricesales}</a>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	
	<hr size="1" width="100%">
	
	<div class="align-right">
		<c:if test="${!empty mem_num && mem_auth== 9}">
			<input type="button" value="수정" onclick="location.href='update.do?board_num=${board.board_num}'">
			<input type="button" value="삭제" id="delete_btn">
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
				delete_btn.onclick=function(){
					let choice = confirm('삭제하시겠습니까?');
					if(choice){
						location.replace('delete.do?board_num=${board.board_num}');
					}
				};
			</script>             
		</c:if>
		<input type="button" value="목록"
		          onclick="location.href='list.do'">
	</div>
</div>
<!-- 게시글 상세 끝 -->
