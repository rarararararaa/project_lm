<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="used-contents">
	<span>메인 상품</span>
	<div class="search-box-byUsed">
		<c:forEach var="list" items="${list}">
			<div class="used-all-contents-div-width">
				<div class="used-all-contents-img">
					<a href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?callNumber=${list.callnumber}"><img src="${list.lib_product_bookimageurl}"></a>
				</div>
				<div class="used-all-contents-column">
					<div class="used-all-contents-box">책 제목 : ${list.lib_product_bookname }</div>
					<div class="used-all-contents-box">저자 : ${list.lib_product_authors} | 출판사 : ${list.lib_product_publisher}</div>
					<div class="used-all-contents-box">${list.lib_product_detail}</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
