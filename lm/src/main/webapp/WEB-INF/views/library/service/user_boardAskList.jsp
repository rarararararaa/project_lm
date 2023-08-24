<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사용자 1:1문의 목록 시작 -->
<div class="page-main">
	<div class="box">
		<c:if test="${mem_auth==9}">
			<div class="title">전체 문의 내역</div>
			<div class="content-box-U">
				<table class="form-box-U">
					<tr>
						<th class="row-title"><b>문의번호</b></th>
						<th class="row-title"><b>제목</b></th>
						<th class="row-title"><b>작성일</b></th>
						<th class="row-title"><b>답변상태</b></th>
					</tr>
					<c:if test="${count == 0}">
						<div class="row-content">표시할 1:1문의내역이 없습니다.</div>
					</c:if>
					<c:if test="${count > 0}">
						<c:forEach var="boardAsk" items="${list}">
							<tr class="align-center">
								<td class="row-title">${boardAsk.ask_num}</td>
								<td class="row-title" width="400"><a
									href="admin_boardAskDetail.do?ask_num=${boardAsk.ask_num}">${boardAsk.ask_title}</a>
								</td>
								<td class="row-title">${boardAsk.ask_reg_date}</td>
								<td class="row-title">
									<c:if test="${boardAsk.ask_status==0}">
										<input class="small-button-R" type="button" value="답변하기"
											onclick="location.href='boardAnswerWrite.do?ask_num=${boardAsk.ask_num}'">
									</c:if> 
									<c:if test="${boardAsk.ask_status==1}">
										<input class="small-button-B" type="button" value="답변완료"
											onclick="location.href='admin_boardAskDetail.do?ask_num=${boardAsk.ask_num}'">
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</c:if>

		<c:if test="${mem_auth<9}">
			<div class="title">최근 나의 문의 내역</div>
			<div class="content-box-U">
				<table class="form-box-U">
					<tr>
						<th class="row-title"><b>문의번호</b></th>
						<th class="row-title"><b>제목</b></th>
						<th class="row-title"><b>작성일</b></th>
						<th class="row-title"><b>답변상태</b></th>
					</tr>
					<c:if test="${count == 0}">
						<div class="row-content">표시할 1:1문의내역이 없습니다.</div>
					</c:if>
					<c:if test="${count > 0}">
						<c:forEach var="boardAsk" items="${list}">
							<tr class="align-center">
								<td class="row-title">${boardAsk.ask_num}</td>
								<td class="row-title" width="400"><a
									href="boardAskDetail.do?ask_num=${boardAsk.ask_num}">${boardAsk.ask_title}</a>
								</td>
								<td class="row-title">${boardAsk.ask_reg_date}</td>
								<td class="row-title">
									<c:if test="${boardAsk.ask_status==0}">
										<input class="small-button-R" type="button" value="대기중"
											onclick="location.href='boardAskDetail.do?ask_num=${boardAsk.ask_num}'">
									</c:if> 
									<c:if test="${boardAsk.ask_status==1}">
										<input class="small-button-B" type="button" value="답변완료"
											onclick="location.href='boardAskDetail.do?ask_num=${boardAsk.ask_num}'">
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<div class="button-box">
					<input class="small-button-U" type="button" value="1:1문의하기"
						onclick="location.href='${pageContext.request.contextPath}/library/service/boardAskWrite.do'">
				</div>
			</div>
		</c:if>
	</div>
</div>
<!-- 사용자 1:1문의 목록 끝 -->

