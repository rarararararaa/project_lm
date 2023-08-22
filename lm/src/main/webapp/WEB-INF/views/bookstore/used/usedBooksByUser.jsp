<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="used-contents">
	<span>중고 등록 리스트</span>
	<div class="search-box-byUsed">
		<c:forEach var="list" items="${list}">
			<div class="used-all-contents-div-width">
				<div class="used-all-contents-img">
					<img src="${list.store_product_cover}">
				</div>
				<div class="used-all-contents-column">
					<div class="used-all-contents-box" data-store_product_num="${list.store_product_num}">책 이름 : ${list.store_product_title}</div>
					<div class="used-all-contents-box">등록 날짜 : ${list.used_product_reg_date} | 중고 등록 번호 : ${list.used_product_num }</div>
						
					<div class="used-all-contents-box">
						관리자 승인 여부 : 
						<c:if test="${list.used_product_approve == 0}">처리중
						</c:if>
						<c:if test="${list.used_product_approve == 1}">열람
						</c:if>
						<c:if test="${list.used_product_approve == 2}">완료 (책을 가지고 도서관에 방문하시기 바랍니다.)
						</c:if>
					</div>
						
					<div class="used-all-contents-box">책 보존 상태 : 
						<c:if test="${list.used_product_condition == 1}">하
						</c:if>
						<c:if test="${list.used_product_condition == 2}">중
						</c:if>
						<c:if test="${list.used_product_condition == 3}">상
						</c:if>
					</div>
					<div class="used-all-contents-box">책 판매가 : ${list.store_product_pricesales}원 | 희망 판매가 : ${list.used_product_price}원 |  
						<c:if test="${list.devide_product_by_used > 0}">기존 책 대비 ${list.devide_product_by_used}% 할인</c:if>
						<c:if test="${list.devide_product_by_used <= 0}">기존 대비 가격이 높습니다 (수정 및 관리자 컨택 필요)</c:if>
					</div>
					<div class="used-all-contents-box">요청 사항 : ${list.used_product_info} | 
					<input type="button" class="default-btn" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/bookstore/used/usedUpdate.do?used_product_num=${list.used_product_num}'"></div>
					
				</div>
				
			</div>
		</c:forEach>
		<div class="paging">${page}</div>
	</div>
</div>