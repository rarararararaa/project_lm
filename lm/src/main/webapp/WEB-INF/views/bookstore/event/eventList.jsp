<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/BsEventList.css">
<!-- 이벤트 글 list 시작!-->
<div class="listPageMain">
	<h2>EVENT</h2>
	<div class="bann-con">
		<div class="attendB">
			<a href="${pageContext.request.contextPath}/bookstore/event/attendanceEvent.do"><img src="${pageContext.request.contextPath}/images/attendanceEvent_banner.jpg"></a>
		</div>
		<div class="bann">
			<a  href="${pageContext.request.contextPath}/bookstore/event/eventAnnounceList.do"><img src="${pageContext.request.contextPath}/images/winBanner.jpg" width="140"></a>
		</div>
	</div>
	<!-- 이벤트 검색 및 정렬 시작 -->
	<!-- <b>${fn:length(list)}</b> -->
	
	<form action="list.do" id="search_form" method="get">
		<div class="align-right">
		<ul class="search" >
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword"
				       id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" 
				    onclick="location.href='list.do'">
			</li>
		</ul>
		</div>
		<div class="align-right">
			<select id="order" name="order">
				<option value="1" <c:if test="${param.order == 1}">selected</c:if>>전체보기</option>
				<option value="2" <c:if test="${param.order == 2}">selected</c:if>>진행중인 이벤트</option>
				<option value="3" <c:if test="${param.order == 3}">selected</c:if>>종료된 이벤트</option>
			</select>
			<script type="text/javascript">
				$(function(){
					$('#order').change(function(){
						location.href='list.do?'+'order='+$('#order').val()+'&keyfield='+$('#keyfield').val()+'&keyword='+$('#keyword').val();
					});
				});
			</script>
			<c:if test="${mem_num == 9}">
				<input type="button" value="글쓰기" 
			                     onclick="location.href='write.do'">
			</c:if>
		</div>
	</form>
	<hr size="1" width="100%">
	<c:set var="itemsPerRow" value="2" />
	<c:set var="rowCount"
		value="${(fn:length(list) + itemsPerRow - 1) / itemsPerRow}" />
	<c:set var="inNum" value="0" />
	
	<div class="event-list">
		<c:forEach var="row" begin="1" end="${rowCount}">
			<div class="event-row">
				<c:forEach var="event" items="${list}" varStatus="status"
					begin="${inNum}"
					end="${(fn:length(list) % 2) == 1 && inNum == (fn:length(list) -1) ? inNum : inNum+1}">
					<div class="event-column col${status.index}">

						<div class="event-simg">
						<a class="wid" href="detail.do?event_board_num=${event.event_board_num}">
							<img src="imageView.do?event_board_num=${event.event_board_num}&event_board_type=1" width="170" height="100">
						</a>
						</div>
						
						<div class="event-content">
							<div class="event-status">
								<ul>
									<c:if test="${event.event_board_status == 2}">
										<li class="status-ing">진행중</li>
									</c:if>
									<c:if test="${event.event_board_status == 3}">
										<li class="status-end">종료</li>
									</c:if>
									
									<c:if test="${event.event_board_category == 1}">
										<li class="cate-quiz">댓글</li>
									</c:if>
									<c:if test="${event.event_board_category == 2}">
										<li class="cate-reply">퀴즈</li>
									</c:if>
									<c:if test="${event.event_board_category == 3}">
										<li class="cate-reply">사은품</li>
									</c:if>
								</ul>
							</div>
							
							<div class="event-title">
								<a href="detail.do?event_board_num=${event.event_board_num}">${event.event_title}</a></div>
							<div class="event-short-content">${event.event_short_content}</div>
							<div class="event-date">
								기한: ${event.event_date_start} ~ ${event.event_date_end}
							</div>
						</div>
						
					</div>
				</c:forEach>
				<c:set var="inNum" value="${inNum + itemsPerRow}" />
			</div>
			<hr size="1" width=" 95%" color="#E9E9E9">
		</c:forEach>
	</div>
	<div class="align-right">
		<c:if test="${mem_auth == 9}">
			<input type="button" value="글쓰기"
				onclick="location.href='write.do'">
		</c:if>
	</div>
	<div class="align-center">${page}</div>
</div>
