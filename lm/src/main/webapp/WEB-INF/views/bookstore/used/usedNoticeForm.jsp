<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="used-contents">
	<div class="used-info">
		<div class="used-books">
			<img src="${pageContext.request.contextPath}/images/used_book.png">
			<span class="used-titles">중고 책 선정</span>
			<p>판매 책과 중고 책 목록 확인</p>
		</div>
		<div class="used-register">
			<img src="${pageContext.request.contextPath}/images/used_register.png">
			<span class="used-titles">책 등록</span>
			<p>증고 책 판매 사진 및 희망가격 기입</p>
		</div>
		<div class="used-judge">
			<img src="${pageContext.request.contextPath}/images/used_judge.png">
			<span class="used-titles">현장 방문 및 심사</span>
			<p>심사 완료 후 방문하여 판매 후 포인트 지급</p>
		</div>
		 
	</div>
	<a class="used-a-button" href="${pageContext.request.contextPath}/bookstore/used/usedForm.do">등록하기</a>
</div>