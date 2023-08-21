<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/libPageNav.js"></script>
<!-- 고객센터 사이드바 시작 -->
<div class="sb-page-nav">
	<h2 class="sb-title">고객센터</h2>
	<ul class="sb-menu">
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/boardannounce/list.do" class="sub-items">공지사항</a>
		</li>
		<li class="sb-menu-item">
			<a href="${pageContext.request.contextPath}/library/service/user_boardAskList.do" class="sub-items">1:1문의</a>
		</li>
		<li class="sb-menu-item">
			<a href="" class="sub-items">도서관 일정</a>
		</li>
		<li class="sb-menu-item">
			<a href="" class="sub-items">분실물 찾기</a>
		</li>
	</ul>
</div>
<!-- 고객센터 사이드바 끝 -->