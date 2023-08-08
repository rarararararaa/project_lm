<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.used-book-nav {
		display:flex;
		margin-top:50px;
		border : solid 2px green;
		border-radius: 20px;
	}
	.lib-main-nav-filter-text {
		font-size : 20px;
	}
</style>
<div class="used-book-nav">
	<div class="lib-main-nav-filter-text">필터</div>
	<div class="lib-main-nav-contents">
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=10&orderByNum=1">전체</a>
	</div>
	<div class="lib-main-nav-contents">
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=0&orderByNum=1&keyword=${resultSearch}">총류 |&nbsp;</a>
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=1&orderByNum=1&keyword=${resultSearch}">철학 |&nbsp;</a>
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=2&orderByNum=1&keyword=${resultSearch}">종교</a>
	</div>
	<div class="lib-main-nav-contents">
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=3&orderByNum=1&keyword=${resultSearch}">사회과학 |&nbsp;</a>
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=4&orderByNum=1&keyword=${resultSearch}">자연과학</a>
	</div>
	<div class="lib-main-nav-contents">
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=5&orderByNum=1&keyword=${resultSearch}">기술과학 |&nbsp;</a>
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=6&orderByNum=1&keyword=${resultSearch}">예술 |&nbsp;</a>
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=7&orderByNum=1&keyword=${resultSearch}">언어</a>
	</div>
	<div class="lib-main-nav-contents">
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=8&orderByNum=1&keyword=${resultSearch}">문학 |&nbsp;</a>
		<a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=9&orderByNum=1&keyword=${resultSearch}">역사</a>
	</div>
</div>
