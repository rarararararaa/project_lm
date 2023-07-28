<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- service 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn" value="비밀번호변경"
			  onclick="location.href='${pageContext.request.contextPath}/member/changePassword.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="채팅"
			  onclick="location.href='${pageContext.request.contextPath}/talk/talkRoomWrite.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="회원탈퇴"
			  onclick="location.href='${pageContext.request.contextPath}/member/delete.do'">
		</li>
	</ul>
</div>
<!-- service 메뉴 끝 -->





