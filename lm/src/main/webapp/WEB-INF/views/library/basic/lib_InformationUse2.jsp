<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출·반납·연장·예약</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/libBasicPage_style.css">

</head>
<body>
	<div id="libBasicPage-1">
		<h2>대출·반납·연장·예약</h2>
		<hr size="1" noshade="noshade">
		<div>
			<h3>도서 대출</h3>
			<ul>
				<li>대출자격 : lm 대출증 발급 회원</li>
				<li>자료대출은 본인만 할 수 있습니다. 단, 만14세 미만 회원의 경우 사전에 지정된 보호자(법정대리인)가
					대리로 할 수 있습니다.</li>
				<li>대출권수 및 기간<br>
					<table class="tg">
						<thead>
							<tr>
								<th class="tg-9wq8">회원구분</th>
								<th class="tg-9wq9">도서</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tg-9wq8">일반</td>
								<td class="tg-9wq9">3권 15일（대출일 포함）</td>
							</tr>
						</tbody>
					</table>
				</li>
				<li>대출이 불가능한 자료</li>
				<li class="li-tab"> - lm도서관 소장자료, 참고도서, 연속간행물, 시청각자료</li>
			</ul>
		</div>
		<div>
			<h3>도서 반납</h3>
			<ul>
				<li>반납 장소</li>
				<li class="li-tab"> - 자료실 대출반납 데스크(일반열람실 1,2)</li>
			</ul>
		</div>
		<div>
			<h3>대출 연장</h3>
			<ul>
				<li>도서 대출 기간은 기본 15일(대출일 포함)이나, 예약이 없는 책은 1회(7일)에 한해 대출일 당일부터
					연장 신청이 가능합니다. (비도서 연장 불가)</li>
				<li>연체도서가 있거나 타인이 예약한 도서는 연장 불가합니다.</li>
			</ul>
		</div>
		<div>
			<h3>도서 예약</h3>
			<ul>
			<li>예약 가능권수 : 1인당 3권</li>
			<li>도서 1권당 3명씩 예약 가능합니다.</li>
			<li>'대출중'인 도서를 예약하면, 예약 순서에 따라 개별통보 합니다.</li>
			<li>예약자가 대출가능 통보를 받은 후 3일 이내에 대출하지 않으면 자동 취소됩니다.</li>
			</ul>
		</div>
		<div>
			<h3>연체 반납</h3>
			<ul>
				<li>도서를 연체반납 시 대출권수 X 연체일수 만큼 대출이 중지됩니다.</li>
			</ul>
		</div>
		<div>
			<h3>자료 분실(훼손)</h3>
			<ul>
				<li>자료의 분실이나 훼손시 동일 도서의 판매가격으로 변상해야합니다.</li>
				<li>자료 분실(훼손) 관련은 도서분실 신고 페이지를 참고해주세요</li>
			</ul>
		</div>
	</div>
</body>
</html>