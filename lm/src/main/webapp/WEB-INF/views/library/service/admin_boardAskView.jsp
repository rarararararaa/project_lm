<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 관리자페이지 1:1문의 게시글 상세 시작 -->
<div class="page-main">
	<div class="box">
		<div class="title">" 회원번호(${boardAskVO.ask_num})님의 1:1문의 내역 "</div>
		<div class="content-box">
			<div class="content-detail">
				<div class="detail-title">제목</div>
				<div class="specific">${boardAskVO.ask_title}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">내용</div>
				<div class="specific">${boardAskVO.ask_content}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">작성일</div>
				<div class="specific">${boardAskVO.ask_reg_date}</div>
			</div>
		</div>

		<div class="title">" 관리자 답변 "</div>
		<div class="content-box">
			<div class="content-detail">
				<div class="detail-title">답변</div>
				<div class="specific">${boardAnswerVO.answer_content}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">답변 등록일</div>
				<div class="specific">${boardAnswerVO.answer_reg_date}</div>
			</div>

			<div class="button-box">
				<input class="small-button" type="button" value="목록"
					onclick="location.href='admin_boardAskList.do'">
			</div>
		</div>
	</div>
</div>
<!-- 관리자페이지 1:1문의 게시글 상세 끝 -->