<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="box">
		<div class="title">전체 도서 목록</div>

		<div class="button-box">
			<input type="button" class="small-button" value="도서목록"
				onclick="location.href='admin_booklist.do'"> <input
				type="button" class="small-button" value="대출목록"
				onclick="location.href='/library/rent/rentHistoryList.do'">
			<input type="button" class="small-button" value="희망도서" onclick="#">
		</div>

		<div class="content-box">
			<form action="admin_booklist.do" id="search_form" method="get">
				<div class="button-box">
					<ul class="main-content">
						<li><select name="keyfield">
								<option value="1"
									<c:if test="${param.keyfield == 1}">selected</c:if>>도서명</option>
								<option value="2"
									<c:if test="${param.keyfield == 2}">selected</c:if>>저자명</option>
								<option value="3"
									<c:if test="${param.keyfield == 3}">selected</c:if>>출판사</option>
						</select></li>
						<li><input type="search" name="keyword" id="keyword"
							value="${param.keyword}"></li>
						<li><input class="small-button" type="submit" value="조회">
						</li>
						<li><input class="small-button" type="button" value="목록"
							onclick="location.href='/library/bookproductadmin/admin_booklist.do'">
						</li>
					</ul>
				</div>
			</form>
			<c:if test="${count == 0}">
				<div class="row-content">표시할 도서정보가 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>도서청구번호</b></th>
						<th class="row-title"><b>분류</b></th>
						<th class="row-title" colspan="4"><b>도서명/작가(저자명)</b></th>
						<th class="row-title"><b>출판사(출판년도)</b></th>
						<th class="row-title"><b>대출상태(대여횟수)</b></th>
					</tr>
					<c:forEach var="bookProduct" items="${list}">
						<tr class="align-center">
							<td class="row-title">${bookProduct.callNumber}</td>
							<td class="row-title"><c:if
									test="${bookProduct.lib_product_class_no == 0}">총류</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 1}">철학</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 2}">종교</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 3}">사회과학</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 4}">자연과학</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 5}">기술과학</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 6}">예술</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 7}">언어</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 8}">문학</c:if> <c:if
									test="${bookProduct.lib_product_class_no == 9}">역사</c:if></td>
							<td class="row-content"><a
								href="/library/lib_book/admin_bookDetail.do?callNumber=${bookProduct.callNumber}">
									<img src="${bookProduct.lib_product_bookImageUrl }" width="100">
							</a></td>
							<td class="row-title" colspan="3"><a
								href="/library/lib_book/admin_bookDetail.do?callNumber=${bookProduct.callNumber}">${bookProduct.lib_product_bookName}/${bookProduct.lib_product_authors}</a>
							</td>
							<td class="row-title">${bookProduct.lib_product_publisher}(${bookProduct.lib_product_publication_year})</td>
							<td class="row-title"><c:if
									test="${bookProduct.lib_product_product_status == 0}">
									<span style="color: blue;">대출가능(${bookProduct.lib_product_loanCnt})</span>
								</c:if> <c:if test="${bookProduct.lib_product_product_status == 1}">
									<span style="color: red;">대출중(${bookProduct.lib_product_loanCnt})</span>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					<b>${page}</b>
				</div>
			</c:if>
		</div>
	</div>
</div>
<!-- 도서관리 - 도서목록 끝 -->










