<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/BsEventStyle.css">
<!-- 이벤트 글 등록 시작 -->
<div class="event-form-main">
	<form:form modelAttribute="bsEventVO" action="write.do" id="register_form" class="event-form">
		<div class="event_form_div1">
			<ul>
				<li>
				<ul class="li-left">
					<li class="box2-left">
						<form:label path="event_board_status">이벤트 상태</form:label>
						<form:select path="event_board_status">
							<form:option value="1">진행중</form:option>
							<form:option value="2">이벤트종료</form:option>
						</form:select>
					</li>
					<li>
						<form:label path="event_board_category">이벤트 종류</form:label> 
						<form:select path="event_board_category">
							<form:option value="1">댓글</form:option>
							<form:option value="2">퀴즈</form:option>
						</form:select>
					</li>
				</ul>
				</li>
				<li>
					<form:label path="store_product_num" >연관 상품</form:label> 
					<form:input path="store_product_num"/>
				</li>
			</ul>
		</div>
		<hr size="1" noshade>
		<div class="event_form_div2">
			<ul>
				<li>
					<form:label path="event_title">제목</form:label>
					<form:input path="event_title" class="box1-right"/>
				</li>
				
				<li>
					<form:label path="event_short_content">짧은 소개</form:label>
					<form:textarea path="event_short_content" class="box1-right"/>
				</li>
				<li>사진첨부 자리</li>
			</ul>
		</div>
		<hr size="1" noshade>
		<div class="event_form_div3">
			<ul>
				<li>
				<ul class="li-left">
					<li class="box2-left">
						<form:label path="event_date_start">시작일</form:label>
						<form:input type="date" path="event_date_start"/>
					</li>
					<li>
						<form:label path="event_date_end">종료일</form:label>
						<form:input type="date" path="event_date_end"/>
					</li>
				</ul>
				</li>
				<li class="box1">
					<form:label path="event_content">내용</form:label>
					<form:textarea path="event_content" class="box1-right"/>
				</li>
			</ul>
		</div>
		<hr size="1" noshade>
		<div class="event_form_div4">
			<ul>
				<li>
				<ul class="li-left">
					<li class="box2-left">
						<form:label path="event_quiz_sel1">문제 1</form:label>
						<form:input path="event_quiz_sel1"/>
					</li>
					<li >
						<form:label path="event_quiz_sel2">문제 2</form:label>
						<form:input path="event_quiz_sel2"/>
					</li>
				</ul>
				</li>
				<li>
					<form:label path="event_quiz_an">정답</form:label>
					<form:input path="event_quiz_an"/>
				</li>
			</ul>
		</div>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>	 
	</form:form>
</div>
<!-- 이벤트 글 등록 끝 -->