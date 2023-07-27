<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab_content">
	<!-- 국내도서 상세 -->
	<div style="height: 100%; overflow:hidden scroll;">
		<a><span class="text-black-bold font-big">${ param.title }</span></a>
		<c:if test="${ param.title == '국내도서' }"></c:if>
		<div class="col_box">
			<div class="col_area">
				<!-- 도서분류 상세분류 -->
				<jsp:include page="bsMenu_detail.jsp">
					<jsp:param value="소설" name="detail" />
					<jsp:param value="시/에세이" name="detail" />
					<jsp:param value="인문" name="detail" />
					<jsp:param value="가정/육아" name="detail" />
					<jsp:param value="요리" name="detail" />
					<jsp:param value="건강" name="detail" />
					<jsp:param value="취미/실용/스포츠" name="detail" />
					<jsp:param value="경제/경영" name="detail" />
					<jsp:param value="자기계발" name="detail" />
					<jsp:param value="정치/사회" name="detail" />
					<jsp:param value="역사/문화" name="detail" />
				</jsp:include>
			</div>
			<div class="col_area">
				<!-- 도서분류 상세분류 -->
				<jsp:include page="bsMenu_detail.jsp">
					<jsp:param value="종교" name="detail" />
					<jsp:param value="예술/대중문화" name="detail" />
					<jsp:param value="중/고등참고서" name="detail" />
					<jsp:param value="기술/공학" name="detail" />
					<jsp:param value="외국어" name="detail" />
					<jsp:param value="과학" name="detail" />
					<jsp:param value="취업/수험서" name="detail" />
					<jsp:param value="여행" name="detail" />
					<jsp:param value="컴퓨터/IT" name="detail" />
					<jsp:param value="잡지" name="detail" />
					<jsp:param value="청소년" name="detail" />
				</jsp:include>
			</div>
			<div class="col_area">
				<!-- 도서분류 상세분류 -->
				<jsp:include page="bsMenu_detail.jsp">
					<jsp:param value="초등참고서" name="detail" />
					<jsp:param value="유아(0~7세)" name="detail" />
					<jsp:param value="어린이(초등)" name="detail" />
					<jsp:param value="만화" name="detail" />
					<jsp:param value="대학교제" name="detail" />
					<jsp:param value="한국소개도서" name="detail" />
					<jsp:param value="교보오리지널" name="detail" />
				</jsp:include>
			</div>
		</div>
	</div>
</div>