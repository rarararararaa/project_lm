<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시판 글 등록 시작 -->
<div class="page-main">
	<form:form modelAttribute="faqVO" action="faqModify.do" id="update_form">
		<form:errors element="div" cssClass="error-color"/>
		<form:hidden path="faq_num"/>
		<ul>
			<li>
				<select id="faq_category" name="faq_category">
					<option value="1" <c:if test="${param.faq_category == 1}">selected</c:if>>배송</option>
					<option value="2" <c:if test="${param.faq_category == 2}">selected</c:if>>취소/교환</option>
					<option value="3" <c:if test="${param.faq_category == 3}">selected</c:if>>포인트/쿠폰</option>
					<option value="4" <c:if test="${param.faq_category == 4}">selected</c:if>>중고</option>
					<option value="5" <c:if test="${param.faq_category == 5}">selected</c:if>>상품</option>
					<option value="6" <c:if test="${param.faq_category == 6}">selected</c:if>>회원관리</option>
					<option value="7" <c:if test="${param.faq_category == 7}">selected</c:if>>주문/결제</option>
					<option value="8" <c:if test="${param.faq_category == 8}">selected</c:if>>세금/계산서</option>
				</select>
			</li>
		    <li>
				<form:label path="faq_title">질문 내용</form:label>
				<form:input path="faq_title"/>
				<form:errors path="faq_title" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="faq_content"/>
				<form:errors path="faq_content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
			<input type="button" value="목록">
		</div>	                               
	</form:form>
</div>
<!-- 게시판 글 등록 끝 -->




