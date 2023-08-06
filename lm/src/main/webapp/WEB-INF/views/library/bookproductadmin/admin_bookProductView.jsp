<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 도서 글상세 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<h2>${bookProduct.lib_product_bookname}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${bookProduct.lib_product_product_status == 0}">
				<span style="color: blue;">대출 가능</span>
			</c:if>
			<c:if test="${bookProduct.lib_product_product_status == 1}">
				<span style="color: red;">대출중</span>
			</c:if>
			대출횟수 : ${bookProduct.lib_product_loanCnt}
		</li>
	</ul>
	<hr size="1" width="100%">
	<div class="detail-content">
		${bookProduct.lib_product_isbn}
		${bookProduct.lib_product_class_no}
		${bookProduct.lib_product_authors}
		${bookProduct.lib_product_bookImageURL}
		${bookProduct.lib_product_publication_year}
		${bookProduct.lib_product_description}
		${bookProduct.lib_product_detail}
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<input type="button" value="수정"
		 onclick="location.href='admin_update.do?callNumber=${bookProduct.callNumber}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('admin_delete.do?callNumber=${bookProduct.callNumber}');
				}
			};
		</script>             
		<input type="button" value="도서목록" onclick="location.href='admin_booklist.do'">
	</div>
</div>
<!-- 공지사항 글상세 끝 -->








