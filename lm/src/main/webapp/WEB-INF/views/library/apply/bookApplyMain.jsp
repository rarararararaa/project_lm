<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-main">
	<h2>희망도서신청</h2>
	<h3 class="sub-title">신청자격</h3>
	<ul class="text-lixt">
		<li>도서관 회원증을 발급받은 회원만 신청이 가능합니다.</li>
	</ul>
	<h3 class="sub-title">신청방법</h3>
	<ul class="text-lixt">
		<li>하단 신청버튼을 클릭하여 신청양식 작성후 신청</li>
	</ul>
	<h3 class="sub-title">신청가능횟수</h3>
	<ul class="text-lixt">
		<li>1달에 5권 1년에 15권 이하만 신청 가능합니다.</li>
	</ul>
	<h3 class="sub-title">신청 제한자료</h3>
	<ul class="text-lixt">
		<li>문제집, 수험서, 중고등 참고서</li>
		<li>판타지, 로맨스소설, 무협지</li>
		<li>웹툰, 라이트노벨 등 각종 만화류</li>
		<li>연감, 백서, 보고서 등 참고도서류</li>
		<li>영리목적·정치목적 자료</li>
		<li>미풍양속이나 정서 등에 문제를 유발할 수 있는 유해자료, 19세 이상 선정적인 도서</li>
		<li>정기간행물과 전자자료(전자책,DVD 등 비도서) ※ 필요하다고 판단되는 경우에는 별도 비도서 구입 지침에 따름)</li>
		<li>외국도서, 특정분야 전문도서</li>
		<li>출판된 지 5년 이상된 자료 (컴퓨터 과학 신학문 분야는 2년)</li>
		<li>고가도서(5만원 이상), 외국도서, 3권을 초과하는 시리즈 또는 전집도서, 기타 다른 기준을 적용하기 어려운 도서</li>
		<li>스프링 제본 또는 낱장자료(리플릿), 입체도서, 악보, 색칠공부, 필사 등 책 크기가 너무 작거나 소리가 나는 도서 등 이용과 관리가 어려운 형태 자료</li>
		<li>소장자료나 구입중 또는 정리중 도서, 신청 또는 주문중복도서</li>
		<li>서지불명도서나 미간행도서, 비매품, 품절이나 절판 도서</li>
		<li>유사도서가 많이 소장되어 있는 경우</li>
	</ul>
	<input type="button" value="희망도서 신청하기" onclick="location.href='bookApplyWrite.do'">
	<input type="button" value="내 희망도서 신청현황" onclick="location.href='bookApplyUserList.do'">
</div>