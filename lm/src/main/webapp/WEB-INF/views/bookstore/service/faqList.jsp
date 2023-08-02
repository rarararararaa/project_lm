<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

$(function(){
	$('.answer').hide();
	$('.question').click(function(){
		$(this).find('.answer').toggle();
	});
});

</script>
<div class="page-main">
	<h2>자주 묻는 질문</h2>
	<ul>
		<li>
		<select id="faq_category" name="faq_category">
				<option value="1"
					<c:if test="${param.faq_category == 1}">selected</c:if>>배송</option>
				<option value="2"
					<c:if test="${param.faq_category == 2}">selected</c:if>>취소/교환</option>
				<option value="3"
					<c:if test="${param.faq_category == 3}">selected</c:if>>포인트/쿠폰</option>
				<option value="4"
					<c:if test="${param.faq_category == 4}">selected</c:if>>중고</option>
				<option value="5"
					<c:if test="${param.faq_category == 5}">selected</c:if>>상품</option>
				<option value="6"
					<c:if test="${param.faq_category == 6}">selected</c:if>>회원관리</option>
				<option value="7"
					<c:if test="${param.faq_category == 7}">selected</c:if>>주문/결제</option>
		</select>
		<script type="text/javascript">
				$(function(){
					$('#faq_category').change(function(){
						location.href='faqList.do?faq_category='+$('#faq_category').val();
					});
				});
			</script>
		</li>
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