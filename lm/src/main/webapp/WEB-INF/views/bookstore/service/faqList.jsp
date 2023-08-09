<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

$(function(){
	$('.answer').val($(this).val().replace(/\r\n/g,'<br>').replace(/\r/g,'<br>').replace(/\n/g,'<br>'));
	$('.answer').hide();
	$('.question').click(function(){
		$(this).find('.answer').toggle();
	});
	
	$('#category_first').click(function(){
		location.href='faqList.do?faq_category=1';
	})
	$('#category_second').click(function(){
		location.href='faqList.do?faq_category=2';
	})
	$('#category_third').click(function(){
		location.href='faqList.do?faq_category=3';
	})
	$('#category_fourth').click(function(){
		location.href='faqList.do?faq_category=4';
	})
	$('#category_fifth').click(function(){
		location.href='faqList.do?faq_category=5';
	})
	$('#category_sixth').click(function(){
		location.href='faqList.do?faq_category=6';
	})
	$('#category_seventh').click(function(){
		location.href='faqList.do?faq_category=7';
	})
	$('#category_eightth').click(function(){
		location.href='faqList.do?faq_category=8';
	})
});
</script>
<div class="page-main">
	<h2>자주 묻는 질문</h2>
	<ul class="view-category" id="view_category">
			<li id="category_first">배송</li>
			<li id="category_second">취소/교환</li>
			<li id="category_third">포인트/쿠폰</li>
			<li id="category_fourth">중고</li>
			<li id="category_fifth">상품</li>
			<li id="category_sixth">회원관리</li>
			<li id="category_seventh">주문/결제</li>
			<li id="category_eightth">세금/계산서</li>
		</ul>
	<ul>
		<c:if test="${count>0}">
		<c:forEach var="faq" items="${list}">
		<li>
			<div class="question">
				Q. ${faq.faq_title}
				<div class="answer">A. ${faq.faq_content}</div>
				
				<c:if test="${mem_auth==9}">
					<input type="button" value="수정" onclick="location.href='faqModify.do?faq_num=${faq.faq_num}">
				</c:if>
			</div>
		</li>
		</c:forEach>
		</c:if>
	</ul>
	<c:if test="${mem_auth==9}">
	<div class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='faqWrite.do'">
	</div>
	</c:if>
</div>