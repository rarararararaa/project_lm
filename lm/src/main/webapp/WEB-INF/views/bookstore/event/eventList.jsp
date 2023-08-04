<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/eventList.css">
<!-- 이벤트 글 list 시작-->
<div class="listPageMain">
	<h2>EVENT</h2>
	<div class="bann-con">
		<div class="attendB">
			<a href="${pageContext.request.contextPath}/bookstore/event/attendanceEvent.do"><img src="${pageContext.request.contextPath}/images/attendanceEvent_banner.jpg"></a>
		</div>
		<div class="bann">당첨자 발표</div>
	</div>
	<!-- 이벤트 검색 및 정렬 시작 -->
	<b>${fn:length(list)}</b>
	
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
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
		<div class="align-right">
			<select id="order" name="order">
				<option value="1" <c:if test="${param.order == 1}">selected</c:if>>전체보기</option>
				<option value="2" <c:if test="${param.order == 2}">selected</c:if>>진행중인 이벤트</option>
				<option value="3" <c:if test="${param.order == 3}">selected</c:if>>종료된 이벤트</option>
			</select>
			<script type="text/javascript">
				$(function(){
					$('#order').change(function(){
						location.href='list.do?keyfield='+$('#keyfield').val()+'&keyword='+$('#keyword').val()+'&order='+$('#order').val();
					});
				});
			</script>
			<c:if test="${mem_num == 9}">
				<input type="button" value="글쓰기" 
			                     onclick="location.href='write.do'">
			</c:if>
		</div>
	</form>
	<!-- 이벤트 검색 및 정렬 끝 -->
	<!-- 
	<c:if test="${fn:length(list)%2 == 1}">
		<c:set var="rowCount" value="${(fn:length(list)/2)+1}"/>
	</c:if>
	<c:if test="${fn:length(list)%2 == 0}">
		<c:set var="rowCount" value="${fn:length(list)/2}"/>
	</c:if>
	
	<c:set var="inNum" value="0"/>
	

	<div class="event-list">
	<c:forEach begin="1" end="${rowCount}" var="i">
	<div class="event-row">
		<c:forEach var="event" items="${list}" varStatus="status" 
					begin="${inNum}" end="${fn:length(list) % 2 == 1 ? inNum : inNum + 1}" >
			<div class="event-column">
				<div class="event-simg"></div>
				<div class="event-content"></div>
			</div>
			<c:set var="inNum" value="${status.index}"/>
		</c:forEach>
	</div>	
	</c:forEach>
	</div>
	<c:set var="itemsPerRow" value="2" />-->

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
						<div class="event-simg"></div>
						<div class="event-content"></div>
					</div>
				</c:forEach>
				<c:set var="inNum" value="${inNum + itemsPerRow}" />
			</div>
		</c:forEach>
	</div>

	<!-- 이벤트 List 출력 -->
		<!-- for문으로 이벤트 리스트 반복 출력 -->
	<!-- 
	<div class="event-list">
		<div class="event-row">
			<div class="event-column">
				<div class="event-simg"></div>
				<div class="event-content"></div>
			</div>
			<div class="event-column">
				<div class="event-simg"></div>
				<div class="event-content"></div>
			</div>
		</div>
	</div>-->
	
	

</div>
