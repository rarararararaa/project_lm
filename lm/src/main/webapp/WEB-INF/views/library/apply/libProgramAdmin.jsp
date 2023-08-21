<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 분실물 게시판 목록 시작 -->
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>프로그램 목록</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
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
				<option value="1" <c:if test="${param.order == 1}">selected</c:if>>최신</option>
				<option value="2" <c:if test="${param.order == 2}">selected</c:if>>조회수</option>
			</select>
			<script type="text/javascript">
				$(function(){
					$('#order').change(function(){
						location.href='list.do?keyfield='+$('#keyfield').val()+'&keyword='+$('#keyword').val()+'&order='+$('#order').val();
					});
				});
			</script>
			<input type="button" value="글쓰기" onclick="location.href='write.do'">
		</div>
	</form>
				<button onclick="location.href='programAdminWrite.do'">프로그램 등록</button>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 프로그램이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th >번호</th>
			<th >프로그램명</th>
			<th >등록일</th>
			<th >진행상황</th>
			<th style="text-align: left;">프로그램 수정</th>
		</tr>
		<c:forEach var="lib_program" items="${list}">
		<tr>
			<td class="align-center">${lib_program.program_num}</td>
			<td class="align-center">	
				<a href="${pageContext.request.contextPath }/library/programDetail.do?program_num=${lib_program.program_num}">${lib_program.program_title}</a>
			</td>
			<td class="align-center">${lib_program.program_reg_date}</td>
			<td class="align-center">
				<c:if test="${lib_program.status==0}">진행예정</c:if>
				<c:if test="${lib_program.status==1}"><span style="color: red;">종료</span></c:if>
				<c:if test="${lib_program.status==2}">진행중</c:if>
			</td>
			<td>
				<c:if test="${lib_program.status!=1 }">
					<button>프로그램 수정</button>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 분실물 게시판 목록 끝 -->





