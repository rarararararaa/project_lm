<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Admin 메뉴 시작 -->
<style>
	.side-bar {
		display:flex;
		margin-top:50px;
		border : solid 2px green;
		border-radius: 20px;
	}
</style>
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn" value="시설 이용"
			  onclick="location.href='${pageContext.request.contextPath}/library/facilityList.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="도서 기증"
			  onclick="location.href='${pageContext.request.contextPath}/library/donationMain.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="프로그램"
			  onclick="location.href='${pageContext.request.contextPath}/library/insertProgram.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="분실물"
			  onclick="location.href='${pageContext.request.contextPath}/library/liblostitem/list.do'">
		</li>
	</ul>
</div>
<!-- Admin 메뉴 끝 -->





