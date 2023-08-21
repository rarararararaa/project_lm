<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서관 이용시간</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/libBasicPage_style.css">

</head>
<body>
	<div id="libBasicPage-1">
	<h2>도서관 이용시간</h2>
	<hr size="1" noshade="noshade">
	<div>
		<h3>이용 대상</h3>
		<ul>
			<li>lm 도서관은 도서관 자료 열람을 원하는 누구나 이용이 가능합니다.</li>
			<li>단, 대출/예약/시설 이용 등 일부 서비스는 대출증을 발급받은 회원만 이용이 가능합니다.</li>
		</ul>
	</div>
	<div>
		<h3>이용시간 안내</h3>
		<div class="div-tb">
		<table class="tg">
			<thead>
				<tr>
					<th class="tg-nrix">자료실</th>
					<th class="tg-nrix">화, 목, 금요일</th>
					<th class="tg-nrix">수요일</th>
					<th class="tg-nrix">토, 일요일</th>
					<th class="tg-nrix">비고</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="tg-nrix">일반자료실 1, 2</td>
					<td class="tg-nrix" rowspan="2">09:00 ~ 18:00</td>
					<td class="tg-nrix" rowspan="2">09:00 ~ 21:00<br>(야간 도서관
						운영)
					</td>
					<td class="tg-nrix" rowspan="2">09:00 ~ 18:00</td>
					<td class="tg-nrix"></td>
				</tr>
				<tr>
					<td class="tg-nrix">디지털자료실</td>
					<td class="tg-nrix"></td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div>
		<h3>휴관일 안내</h3>
		<ul>
			<li>매주 월요일</li>
			<li>일요일을 제외한 법정공휴일 및 국가가 정한 임시 휴일</li>
			<li>기타 관장이 필요하다고 인정하는 날 미리 공지 후 휴관</li>
		</ul>
	</div>
	</div>
</body>
</html>