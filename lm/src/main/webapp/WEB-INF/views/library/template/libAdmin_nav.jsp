<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Admin 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn" value="회원관리"
			  onclick="location.href='${pageContext.request.contextPath}/library/member/admin_list.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="도서관리"
			  onclick="location.href='${pageContext.request.contextPath}/library/bookproductadmin/admin_booklist.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="기증승인"
			  onclick="location.href='${pageContext.request.contextPath}/library/donationadmin/admin_donationlist.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="공지사항"
			  onclick="location.href='${pageContext.request.contextPath}/library/boardannounce/list.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="분실물"
			  onclick="location.href='${pageContext.request.contextPath}/library/liblostitem/list.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="1:1문의"
			  onclick="location.href='${pageContext.request.contextPath}/library/service/admin_boardAskList.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="일정 등록"
			  onclick="location.href='#'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="프로그램 등록"
			  onclick="location.href='#'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="시설 등록"
			  onclick="location.href='#'">
		</li>
	</ul>
</div>
<!-- Admin 메뉴 끝 -->