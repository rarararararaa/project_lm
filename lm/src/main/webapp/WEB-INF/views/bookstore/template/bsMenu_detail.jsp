<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 도서분류 상세분류 -->
<ul class="fold_box_list">
	<c:forEach var="book" items="${ paramValues.detail }">
		<li>
			<div class="fold_box_header">
				<a>${ book }</a>
				<button type="button" class="btn_fold"></button>
			</div> <!-- 상세 -->
			<div></div>
		</li>
	</c:forEach>
</ul>
