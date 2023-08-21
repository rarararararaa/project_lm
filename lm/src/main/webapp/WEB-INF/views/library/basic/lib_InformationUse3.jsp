<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반자료실</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/libBasicPage_style.css">

</head>
<body>
	<div id="libBasicPage-3">
		<h2>일반자료실</h2>
		<hr size="1" noshade="noshade">
		<div>
			<h3>자료실 개요</h3>
			<ul>
				<li>
					<table class="tg">
						<thead>
							<tr>
								<th class="tg-9wq8">위치</th>
								<th class="tg-9wq9">운영시간</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tg-9wq8">도서관 1~2층</td>
								<td class="tg-9wq9">평일(화, 목, 금) : 09:00 ~ 18:00<br>평일(수)
									: 09:00 ~ 21:00 <br>주말(토, 일) : 09:00 ~ 18:00
								</td>
							</tr>
						</tbody>
					</table>
				</li>
				<li>도서 대출은 lm도서관 대출증을 발급받은 회원만 가능합니다.</li>

			</ul>
		</div>

		<div>
			<h3>보유 자료</h3>
			<ul>
				<li>
					<table class="tg">
						<thead>
							<tr>
								<th class="tg-9wq8">구분</th>
								<th class="tg-9wq7">보유자료</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tg-9wq8">일반자료실 1층</td>
								<td class="tg-9wq7">어린이(C), 총류(000), 철학(100), 종교(200), 사회과학(300), 자연과학(400), 기술과학(500)</td>
							</tr>
							<tr>
								<td class="tg-9wq8">일반자료실 2층</td>
								<td class="tg-9wq7">어린이_원서(CF), 예술(600), 언어(700), 문학(800), 역사(900)</td>
							</tr>
						</tbody>
					</table>
				</li>
			</ul>
		</div>

	</div>
</body>
</html>