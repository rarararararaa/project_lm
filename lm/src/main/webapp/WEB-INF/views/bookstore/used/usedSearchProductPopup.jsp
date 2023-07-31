<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 이름 찾기</title>
</head>
<body>
	<!-- 검색창을 만들고, 찾기를 클릭 했을 때 List로 목록들을 보여주고, 그걸 클릭했을 때 그 값으로 고정되게 해야함-->
	<form action="/bookstore/used/selectProductNameByUsed.do}" name="usedPopup" >
		<input type="text" name="keyword">
		<button type="submit">찾기</button>
	</form>
	<c:if test="${ success == 1 }">
		<div>
			<ul>
				<li>사진</li>
				<li>책이름</li>
				<li>분류</li>
				<li>저자</li>
				<li>출판사</li>
			</ul>
			<c:forEach var="list" items="${list}">
				<ul>
					<li>${list.store_product_cover }</li>
					<li>${list.store_product_title }</li>
					<li>${list.store_product_category }</li>
					<li>${list.store_product_author }</li>
					<li>${list.store_product_publisher }</li>
				</ul>
			</c:forEach>
		</div>
	</c:if>
</body>
</html>