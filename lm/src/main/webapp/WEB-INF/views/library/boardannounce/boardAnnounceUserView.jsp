<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사용자 공지사항 글상세 시작 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<div class="box">
		<div class="title">공지사항</div>
		<div class="content-box-U">
			<div class="content-detail">
				<div class="detail-title">제목</div>
				<div class="specific">${boardAnnounce.notice_title}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">작성자</div>
				<div class="specific">
					<div class="answer">
						<b>관리자</b>
					</div>
				</div>
			</div>
			<div class="content-detail">
				<c:if test="${!empty boardAnnounce.notice_modify_date}">
					<div class="detail-title">최근 수정일</div>
					<div class="specific">${boardAnnounce.notice_modify_date}</div>
				</c:if>
				<c:if test="${empty boardAnnounce.notice_modify_date}">
					<div class="detail-title">작성일</div>
					<div class="specific">${boardAnnounce.notice_reg_date}</div>
				</c:if>
			</div>
			<div class="content-detail">
				<div class="detail-title">조회수</div>
				<div class="specific">${boardAnnounce.notice_hit}</div>
			</div>
			<div class="main-content-U">${boardAnnounce.notice_content}</div>
			<div class="button-box">
				<c:if test="${mem_auth == 9}">
					<input class="small-button-B" type="button" value="수정"
						onclick="location.href='update.do?notice_num=${boardAnnounce.notice_num}'">
					<input class="small-button-R" type="button" value="삭제"
						id="delete_btn">
					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						delete_btn.onclick = function() {
							let choice = confirm('삭제하시겠습니까?');
							if (choice) {
								location
										.replace('delete.do?notice_num=${boardAnnounce.notice_num}');
							}
						};
					</script>
				</c:if>
				<input class="small-button-U" type="button" value="목록"
					onclick="location.href='Userlist.do'">
			</div>
		</div>
	</div>
</div>
<!-- 사용자 공지사항 글상세 끝 -->








