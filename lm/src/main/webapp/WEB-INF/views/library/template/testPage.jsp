<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TestPage</title>
<style>
body {
	padding: 0px;
	margin: 0px;
}
.title-box {
	color:white;
}
.video-box {
	width: 100%;
	height: 100vh;
	overflow: hidden;
	margin: 0px auto;
	position: relative;
	z-index: 0;
}

video {
	width: 100%;
}

.title {
	position: absolute;
	top: 50%;
	left: 50%;
	font-size: 50px;
}

.fade-in-box {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 90%;
	height: 90vh; 
	font-size : 50px;
	display: inline-block;
	background: rgba(0, 0, 0, 0.9);
	padding: 10px;
	animation: fadein 3s;
	-webkit-animation: fadein 3s;
	
}

@-webkit-keyframes fadein { /* Safari and Chrome */ 

from { opacity:0;
	
}

to {
	opacity: 1;
}

}

.bookstore-link, .library-link {
	border-radius: 4px;
	background: rgba(127,255,0,0.4);
	border:none;
	color:white;
	text-align:center;
	font-size:28px;
	padding:20px;
	width:230px;
	transition: all 0.5s;
	cursor: pointer;
	margin:5px;
}

.bookstore-link span, .library-link span {
	cursor:pointer;
	display:inline-block;
	position:relative;
	transition: 0.5s;
	
}

.bookstore-link span:after , .library-link span:after {
	content: '\00bb';
	position:absolute;
	opacitiy:0;
	top:0;
	right:-20px;
	transition:0.5s;
	display:flex;
	justify-content:center;
	align-items:center;
	width:10px;
	height:100%;
}

.bookstore-link:hover span, .library-link:hover span {
	padding-right:25px;
}

.bookstore-link:hover span:after, .library-link:hover span:after {
	opacity: 1;
	right: 0;
}

.first-box {
	width:100%;
	display:flex;
	
	justify-content: flex-start;
	align-items:center;
	margin-left:50px;
	margin-top:30px;
}

.first-box button {
	margin-left:50px;
}

hr {
	width:100%;
	height:3px;
	background:rgba(127,255,0,0.4);
	border:none;
}
.second-box {
	width:100%;
	height:70vh;
	display:flex;
	flex-direction:column;
	align-items:center;
}
.lists {
	
}
.lists ul{
	display:flex;
	justify-content:flex-start;
	width:100%;
	list-style:none;
	font-size:30px;
	
}

.lists ul li {
	color:white;
	margin-left:50px;
	margin-right: 50px;
	
}


/* 이미지 및 이름 가운데 정렬 */
.third-box {
    display: none;
    text-align: center;
}

.profile-box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 50px;
}

.profile-box img {
    width: 50%;
    border-radius: 50%;
    margin-bottom: 20px;
}

.result-int span{
	font-size:25px;
	padding:20px;
}

.result-int p {
	font-size:18px;
	padding:20px;
}

.result-int {
	width:1400px;
	background-color:white;
	color:black;
	border-radius:25px;
	display:flex;
	justify-content: center;
	
}
span {
	display:flex;
	flex-direction:column;
	align-items:center;
}

span img {
	width:200px;
	height:200px;
	padding:30px;
}
#main-result {
	
}

li:hover {
	color:green;
	cursor: pointer;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	
	/*$('#introduce').click(function(){
		$('#main-result').empty();
		let output = '<div class="result-int">';
	    output += '<p>LM 그룹은 이과와 문과를 합친 학습을 지원하는 학원으로, LM 문고와 LM 도서관으로 구성되어 있습니다.<br><br>';
	    output += 'LM 문고는 다양한 분야의 책을 판매하는 온라인 플랫폼으로, 독서를 촉진하고 지식을 널리 퍼뜨리기 위해 노력하고 있습니다. 여기서는 최신 도서부터 고전까지 폭넓은 선택지를 제공하여 사용자가 자신의 관심사와 요구에 맞는 도서를 찾을 수 있습니다.<br><br>';
		output += '반면 LM 도서관은 독서 문화를 촉진하며, 사용자들에게 책을 빌려주는 서비스를 제공합니다. 다양한 장르와 주제의 도서들을 빌려가며 지식 습득을 지원하고,';
		output += '학습 환경을 개선하기 위해 공간 예약과 공공 서비스를 제공합니다. 그렇게 함으로써 지식을 공유하고, 사람들의 학습과 자기계발을 지원하며, 사회 전반에 긍정적인 영향을 미치는 것을 목표로 하고 있습니다.<br><br>';
		output += 'LM 그룹은 다양한 도서와 자료를 통해 개인의 호기심과 교양을 향상시키는 데 기여하며, 지식과 정보의 접근성을 높이는 역할을 수행하고 있습니다. 이와 함께 LM 도서관은 학습 공간을 제공하여 사용자들이 소통하고 협력하며 지식을 나누는 커뮤니티의 역할도 수행하고 있습니다.</p>';
	    output += '</div>';
		$('#main-result').append(output);
	});
	*/
	$('#devstack').click(function(){
		$('#main-result').empty();
		let output = '<div class="result-int">';
	    output += '<span>';
	    output += '<img src="${pageContext.request.contextPath}/images/front_end_logo.png">';
	    output += 'HTML, CSS<br> JavaScript, jQuery</span>';
	    output += '<span>';
	    output += '<img src="${pageContext.request.contextPath}/images/mybatis_logo.png">';
	    output += 'Spring MyBatis</span>';
	    output += '<span>';
	    output += '<img src="${pageContext.request.contextPath}/images/oracle_xe_logo.svg">';
	    output += 'RDBMS Oracle XE</span>';
	    output += '<span>';
	    output += '<img src="${pageContext.request.contextPath}/images/spring_logo.svg">';
	    output += 'Spring Boot<br>FrameWork</span>';
	    output += '</div>';
		$('#main-result').append(output);
	});
	
	$('#devstack').click();
});

</script>
</head>
<body>
	<div class="video-box">
		<video muted autoplay loop>
			<source src="/videos/seoul.mp4" type="video/mp4">
		</video>
	</div>
	<div class="fade-in-box">
		<div class="first-box">
			<div class="title-box">LM 프로젝트</div>
			<button onclick="location.href='${pageContext.request.contextPath}/bookstore/template/bsMain.do';" class="bookstore-link">
				<span>LM 문고 </span>
			</button>
			<button onclick="location.href='${pageContext.request.contextPath}/library/template/libMain.do';" class="library-link">
				<span>LM 도서관 </span>
			</button>
		</div>
		<hr>
		<div class="second-box">
			<div class="lists">
				<ul>
					
					<li id="devstack">사용 기술 스택</li>
					
				</ul>
			</div>
			<div id="main-result"></div>
			
			
		</div>
		<div class="third-box">
			
		</div>
	</div>
</body>
</html>