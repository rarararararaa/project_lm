<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/service.css">
<div class="page-main">
	<div class="box">
		<div class="title">전체 희망도서 신청목록</div>
		<div class="button-box">
			<input type="button" class="small-button" value="도서목록"
				onclick="location.href='/library/bookproductadmin/admin_booklist.do'"> <input
				type="button" class="small-button" value="대출목록"
				onclick="location.href='/library/rent/rentHistoryList.do'">
			<input type="button" class="small-button" value="희망도서" onclick="location.href='/library/apply/admin_bookApplyList.do'">
		</div>
		<c:if test="${count == 0}">
			<div class="row-content">신청한 희망도서가 없습니다.</div>
		</c:if>
		<c:if test="${count > 0}">
			<%-- <div class="content-box">
				<div class="content-detail">
					<div class="detail-title">1개월동안 신청한 희망도서수</div>
					<div class="specific">${month}</div>
				</div>
				<div class="content-detail">
					<div class="detail-title">1년동안 신청한 희망도서수</div>
					<div class="specific">${year}</div>
				</div>
			</div> --%>
			<table class="form-box">
				<tr>
					<th class="row-title"><b>No</b></th>
					<th class="row-title"><b>제목</b></th>
					<th class="row-title"><b>신청일</b></th>
					<th class="row-title"><b>신청현황</b></th>
				</tr>
				<c:forEach var="apply" items="${list}">
					<tr class="align-center">
						<td class="row-title">${apply.book_apply_num}</td>
						<td class="row-title">${apply.book_apply_title}</td>
						<td class="row-title">${apply.book_apply_reg_date}</td>
						<td class="row-title">
							<c:if test="${apply.book_apply_status==0}">대기중</c:if> 
							<c:if test="${apply.book_apply_status==1}">확인완료</c:if> 
							<c:if test="${apply.book_apply_status==2}">등록완료</c:if> 
							<c:if test="${apply.book_apply_status==3}">반려</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="button-box">
				<input class="small-button-B" type="button" value="추가 신청하기"
					onclick="location.href='admin_bookApplyWrite.do'">
			</div>
			<div class="align-center"><b>${page}</b></div>
		</c:if>
	</div>
</div>