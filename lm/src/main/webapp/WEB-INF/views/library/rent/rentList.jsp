<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#dialog').dialog({
			width: '700px',
			height: 'auto',
			autoOpen: false,
			modal: true
		});
	});
	$(function(){
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});	
</script>    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rent.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EESAMSAOH.css">
<div class="product-main">
	<h2 class="align-center">대출/반납 관리</h2>
		<input class="small-button" type="button" value="도서목록" onclick="location.href='/library/bookproductadmin/admin_booklist.do'">
		<input class="small-button" type="button" value="대출목록" onclick="location.href='/library/rent/rentHistoryList.do'">
		<input class="small-button" type="button" value="희망도서" onclick="#">
	<div class="button-box">
		<input type="button" id="opener" value="대출 등록" class="small-button">
	</div>

		<!-- 대출 등록 다이얼로그 시작 -->
	<div id="dialog">
		<form id="new_form">
			<ul>
				<li>
					<label for="book_search">대출할 도서</label>
					<input type="text" id="book_search" autocomplete="off">
					<ul id="search_area1"></ul>
					<div id="book_list"></div>
				</li>
			</ul>
			<ul>
				<li>
					<label for="member_search">대출할 회원</label>
					<input type="text" id="member_search" autocomplete="off">
					<ul id="search_area"></ul>
					<div id="member_list"></div>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="전송">
			</div>
		</form>
	</div>
		<!-- 대출 등록 다이얼로그 끝 -->
	<!-- 내용 시작 -->
		<div class="content-box">			
			<%-- 검색은 링크 가능하기 때문에 get방식으로 --%>
			<!-- 검색창 시작 -->
			<form id="search_form" action="/library/rent/rentHistoryList.do"
			                          method="get">
				<div class="button-box">					                          
						<ul class="main-content">
								<li>
										<select name="keyfield" class="select">
											<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>도서번호</option>
											<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>회원ID</option>
											<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>도서명</option>
										</select>
								</li>
								<li>
									<input type="search" size="16" 
									   name="keyword" id="keyword"
									                    value="${param.keyword}">
								</li>
								<li>
									<input class="small-button" type="submit" value="검색">
								</li>
								<li>
									<input class="small-button" type="button" value="목록" onclick="location.href='/library/rent/rentHistoryList.do'">
								</li>
						</ul>
				</div>                          
			</form>
			<!-- 검색창 끝 -->
			<c:if test="${count == 0}">
			<div class="row-content">
				표시할 대출정보가 없습니다.
			</div>
			</c:if>
			<c:if test="${count > 0}">
			<table class="form-box">
				<tr>
					<th class="row-title">대출번호</th>
					<th class="row-title">도서번호</th>
					<th class="row-title" colspan="4">도서명</th>
					<th class="row-title">회원아이디</th>
					<th class="row-title">대출일</th>
					<th class="row-title">반납예정일</th>
					<th class="row-title">반납일</th>
					<th class="row-title">대출상태</th>
					<th class="row-title">연장여부</th>
					<th class="row-title">반납/연장</th>
				</tr>
				<c:forEach var="list" items="${list}">
				<tr>
					<td class="row-title"><b>${list.rent_num}</b></td>
					<td class="row-title">
						<b>
						<a href="/library/lib_book/bookDetail.do?callNumber=${list.callNumber}">${list.callNumber}</a>
						</b>
					</td>
					<td class="row-content">
						<a href="/library/lib_book/bookDetail.do?callNumber=${list.callNumber}">
						<img src="${list.bookVO.lib_product_bookImageUrl }" width="100">
						</a>
					</td>
					<td class="row-content" colspan="3">
						<a href="/library/lib_book/bookDetail.do?callNumber=${list.callNumber}">
							${list.bookVO.lib_product_bookName }
						</a>
					</td>
					<td class="row-content">${list.mem_id }</td>
					<td class="row-content">${list.rent_reg_date }</td>
					<td class="row-content">${list.return_reg_deadline }</td>
					<td class="row-content">${list.return_reg_date }</td>
					<td class="row-title">
						<strong>
						<c:if test="${list.lib_product_status >1}">
						<div style="color:#cc0619;">
							대출중
						</div>	
						</c:if>
						<c:if test="${list.lib_product_status ==1}">
						<div>
							반납
						</div>	
						</c:if>
						</strong>
					</td>
					<td class="row-title">
						<c:if test="${list.lib_product_status ==3}">
							O
						</c:if>
						<c:if test="${list.lib_product_status ==2}">
							X
						</c:if>
					</td>
					<td class="row-title">
						<c:if test="${list.lib_product_status >1}">
							<input class="sm-button" type="button" value="반납" onclick="location.href='/library/rent/updateRentHistory.do?rent_num=${list.rent_num}'">
						</c:if>
						<c:if test="${list.lib_product_status ==2}">
							<input class="sm-button" type="button" value="연장" onclick="location.href='/library/rent/updateRentDeadline.do?rent_num=${list.rent_num}'">
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
			<div class="align-center">${page}</div>
			</c:if>
		<!-- 내용 끝 -->		
</div>
</div>