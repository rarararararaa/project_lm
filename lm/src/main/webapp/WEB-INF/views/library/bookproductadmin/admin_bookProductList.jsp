<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 도서관리 -도서목록 시작 -->
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>도서 목록</h2>
	<input type="button" value="도서목록" onclick="location.href='admin_booklist.do'">
	<input type="button" value="대출목록" onclick="${pageContext.request.contextPath}/library/rent/rentHistoryList.do">
	<input type="button" value="희망도서" onclick="#">
	
	<form action="admin_booklist.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>도서명</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>저자명</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>출판사</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword"
				                     value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="조회">
				<input type="button" value="도서목록" 
				   onclick="location.href='admin_booklist.do'">
			</li>
		</ul>
	</form>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 도서정보가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>도서청구번호</th>
			<th>도서명</th>
			<th>주제(분류)</th>
			<th>작가(저자명)</th>
			<th>출판사</th>
			<th>출판년도</th>
			<th>대출상태</th>
			<th>대출횟수</th>
		</tr>
		<c:forEach var="bookProduct" items="${list}">
		<tr class="align-center">
			<td>${bookProduct.callNumber}</td>
			<td>
				<a href="/library/lib_book/bookDetail.do?callNumber=${bookProduct.callNumber}">${bookProduct.lib_product_bookName}</a>
			</td>
			<td>
				<c:if test="${bookProduct.lib_product_class_no == 0}">총류</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 1}">철학</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 2}">종교</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 3}">사회과학</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 4}">자연과학</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 5}">기술과학</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 6}">예술</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 7}">언어</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 8}">문학</c:if>
				<c:if test="${bookProduct.lib_product_class_no == 9}">역사</c:if>
			</td>
			<td>${bookProduct.lib_product_authors}</td>
			<td>${bookProduct.lib_product_publisher}</td>
			<td>${bookProduct.lib_product_publication_year}</td>
			<td>
				<c:if test="${bookProduct.lib_product_product_status == 0}"><span style="color: blue;">대출 가능</span></c:if>
				<c:if test="${bookProduct.lib_product_product_status == 1}"><span style="color: red;">대출중</span></c:if>
			</td>
			<td>${bookProduct.lib_product_loanCnt}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>	
</div>
<!-- 도서관리 - 도서목록 끝 -->










