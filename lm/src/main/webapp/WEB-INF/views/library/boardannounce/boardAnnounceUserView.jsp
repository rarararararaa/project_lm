<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 공지사항 글상세 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<h2>${boardAnnounce.notice_title}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${!empty boardAnnounce.notice_modify_date}">
				최근 수정일 : ${boardAnnounce.notice_modify_date}
			</c:if>
			<c:if test="${empty boardAnnounce.notice_modify_date}">
				작성일 : ${boardAnnounce.notice_reg_date}
			</c:if>
			조회 : ${boardAnnounce.notice_hit}
		</li>
	</ul>
	<hr size="1" width="100%">
	<div class="detail-content">
		${boardAnnounce.notice_content}
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<input type="button" value="수정"
		 onclick="location.href='update.do?notice_num=${boardAnnounce.notice_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?notice_num=${boardAnnounce.notice_num}');
				}
			};
		</script>             
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>
<!-- 공지사항 글상세 끝 -->








