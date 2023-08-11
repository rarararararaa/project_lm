<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- 파라미터 세팅 -->
	<% pageContext.setAttribute("lo",request.getParameter("lo")); %>
	<!-- 쿼리스트링으로 받아온 값(lo=1 or lo=2)을 hidden 값으로 저장하여 최종 redirect 주소 지정 -->
	<input type="hidden" name="lo" value="${lo}"/>
<div class="whole">
  <div class="top-div">
  	<div class="top-left-div">
  		<div class="photo">
  		<img src="${pageContext.request.contextPath}/lm/mypage/myedit/photoView.do?mem_num=${mem_num}" class="view-photo">
  		</div>
  	</div>
  	
  	<div class="top-right-div">
  		<div class="top-right-left-div">
  			<div class="fir-content">
  				<div class="content-id">
  				${mem_id}
  				</div>
  				<div class="content-change">
  				<a href="${pageContext.request.contextPath}/lm/mypage/myedit/myEditMain.do?lo=${lo}">회원정보 수정</a>
  				</div>
  			</div>
  			<div class="sec-content">
  				<div class="sec-content-top">
  					<div class="content-grade">
  					<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">등급<br>${mem_grade_name}</a>
  					</div>
  					<div class="content-reg-date">
  					가입일<br>${mem_reg_date}
  					</div>
  					<div class="content-edit-date">
  					회원정보 수정일<br>${mem_modify_date}
  					</div>
  				</div>
  				<div class="content-remain-grade">
  				<a href="${pageContext.request.contextPath}/lm/mypage/gradeinfo/gradeInfoMain.do?lo=${lo}">다음 등급까지 ${mem_grade_remain}</a>
  				</div>
  			</div>
  			<div class="thi-content">
  				<div class="content-point">
  				<a href="${pageContext.request.contextPath}/lm/mypage/pointinfo/pointInfoMain.do?lo=${lo}">사용 가능 포인트 : ${mem_point} 포인트</a>
  				</div>
  			</div>
  		</div>
  		<div class="top-right-right-div">
  			<div class="sub-button">
  				<a href="${pageContext.request.contextPath}/lm/mypage/zzimbooklist/zzimBookListMain.do?lo=${lo}" class="sub-title">찜한 도서</a>
  				<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/zzimbooklist/zzimBookListMain.do?lo=${lo}">${zzim_num_count}개</a></strong>
  			</div>
  			<div class="sub-button">
  				<a href="${pageContext.request.contextPath}/lm/mypage/bookwritelist/bookWriteListMain.do?lo=${lo}" class="sub-title">도서 후기</a>
  				<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/bookwritelist/bookWriteListMain.do?lo=${lo}">${review_num_count}개</a></strong>
  			</div>
  			<div class="sub-button">
  				<a href="${pageContext.request.contextPath}/lm/mypage/bookreportlist/bookReportListMain.do?lo=${lo}" class="sub-title">독후감 작성</a>
  				<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/bookreportlist/bookReportListMain.do?lo=${lo}">${rep_num_count}개</a></strong>
  			</div>
  			<div class="sub-button">
	  			<a href="${pageContext.request.contextPath}/lm/mypage/couponlist/couponListMain.do?lo=${lo}" class="sub-title">보유 쿠폰</a>
	  			<strong id="sub-content"><a href="${pageContext.request.contextPath}/lm/mypage/couponlist/couponListMain.do?lo=${lo}">${coupon_num_count}개</a></strong>
  			</div>
  		</div>
  	</div>
  </div>
  <hr>
  </div>
 