<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사용자 분실물 게시판 목록 시작 -->
<script type="text/javascript">
	$(function() {
		//검색 유효성 체크
		$('#search_form').submit(function() {
			if ($('#keyword').val().trim() == '') {
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<div class="box">
		<div class="title">전체 분실물</div>
		<div class="content-box-U">
			<form action="listUser.do" id="search_form" method="get">
				<div class="button-box">
					<ul class="main-content">
						<li><select name="keyfield" id="keyfield">
								<option value="1"
									<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
								<option value="2"
									<c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
								<option value="3"
									<c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
						</select></li>
						<li><input type="search" name="keyword" id="keyword"
							value="${param.keyword}"></li>
						<li><input class="small-button" type="submit" value="찾기">
							<input class="small-button-U" type="button" value="목록"
							onclick="location.href='listUser.do'">
							<c:if test="${mem_auth == 9}">
									<input class="small-button-B" type="button" value="글쓰기"
										onclick="location.href='write.do'">
							</c:if>
						</li>
						<li>
							<div class="align-right">
								<select id="order" name="order">
									<option value="1"
										<c:if test="${param.order == 1}">selected</c:if>>최신</option>
									<option value="2"
										<c:if test="${param.order == 2}">selected</c:if>>조회수</option>
								</select>
								<script type="text/javascript">
									$(function() {
										$('#order')
												.change(
														function() {
															location.href = 'listUser.do?keyfield='
																	+ $(
																			'#keyfield')
																			.val()
																	+ '&keyword='
																	+ $(
																			'#keyword')
																			.val()
																	+ '&order='
																	+ $(
																			'#order')
																			.val();
														});
									});
								</script>
							</div>
						</li>
					</ul>
				</div>
			</form>
			<c:if test="${count == 0}">
				<div class="row-content">표시할 분실물이 없습니다.</div>
			</c:if>
			<c:if test="${count > 0}">
				<table class="form-box-U">
					<tr>
						<th class="row-title"><b>No</b></th>
						<th class="row-title"><b>제목</b></th>
						<th class="row-title"><b>작성자</b></th>
						<th class="row-title"><b>작성일</b></th>
						<th class="row-title"><b>조회수</b></th>
					</tr>
					<c:forEach var="lib_lost_item" items="${list}">
						<tr class="align-center">
							<td class="row-title">${lib_lost_item.item_num}</td>
							<td class="row-title"><a
								href="detailUser.do?item_num=${lib_lost_item.item_num}">${lib_lost_item.item_title}</a>
							</td>
							<td class="row-title">
								<div class="answer">
									<b>관리자</b>
								</div>
							</td>
							<td class="row-title">${lib_lost_item.item_reg_date}</td>
							<td class="row-title">${lib_lost_item.item_hit}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					<b>${page}</b>
				</div>
			</c:if>
		</div>
	</div>
</div>
<!-- 사용자 분실물 게시판 목록 끝 -->





