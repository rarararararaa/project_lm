<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- service 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn" value="공지사항"
			  onclick="location.href='${pageContext.request.contextPath}/bookstore/service/announceList.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="자주 묻는 질문"
			  onclick="location.href='${pageContext.request.contextPath}/bookstore/service/faqList.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="1:1문의"
			  onclick="location.href='${pageContext.request.contextPath}/member/delete.do'">
		</li>
		<li class="call-center">
			<span class="img-cover">고객센터 이용안내</span>
			<img src="${pageContext.request.contextPath}/images/call.png" width="30"><br>
			전화 상담 : 02-0000-0000<br>
			e-mail : lmbs@lmbs.com<br>
			평일 09:00 ~ 18:00 (12:00~13:00 점심시간)
		</li>
		<c:if test="${mem_auth==9}">
		<li>
			<input type="button" class="menu-btn" value="관리자 페이지"
			  onclick="location.href='${pageContext.request.contextPath}/bookstore/adminMain.do'">
		</li>
		</c:if>
	</ul>
</div>
<!-- service 메뉴 끝 -->





