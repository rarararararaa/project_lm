<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-main">
	<div class="box">
		<div class="title">프로그램 상세</div>
		<div class="content-box">
			<div class="content-detail">
				<div class="detail-title">프로그램 제목</div>
				<div class="specific">${program.program_title}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">등록일</div>
				<div class="specific">${program.program_reg_date}</div>
			</div>
			<div class="content-detail">
				<div class="detail-title">조회수</div>
				<div class="specific">${program.program_hit}</div>
			</div>
			<div class="main-content">${program.program_content}</div>
			<form:form modelAttribute="programTimesVO"
				action="admin_programDetail.do">
				<select name="program_times_num">
					<c:forEach var="time" items="${times}">
						<option value="${time.program_times_num}">${time.program_start}
							${time.start}:00 ~ ${time.end}:00 신청 가능 인원 :
							${time.program_admit}</option>
					</c:forEach>
				</select>
				<div class="button-box">
				<form:button class="small-button-B" id="submit">신청</form:button>
				<input class="small-button" type="button" value="목록"
					onclick="location.href='programAdminList.do'">
				</div>
			</form:form>
		</div>
	</div>
</div>