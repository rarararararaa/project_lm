<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- service 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn" value="회원관리"
			  onclick="location.href='${pageContext.request.contextPath}/bookstore/admin_list.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="상품관리"
			  onclick="location.href='${pageContext.request.contextPath}/bookstore/adminProductList.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="주문관리"
			  onclick="location.href='${pageContext.request.contextPath}/bookstore/adminOrderList.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="매출내역"
			  onclick="location.href='#'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="쿠폰/이벤트 관리"
			  onclick="location.href='#'">
		</li>
	</ul>
</div>
<!-- service 메뉴 끝 -->





