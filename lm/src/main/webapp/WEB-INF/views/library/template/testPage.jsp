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
	background: rgba(255, 255, 255, 0.9);
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
	border:solid 1px red;
	display:flex;
	justify-content:center;
	align-items:center;
}

.second-box .workers {
	border:solid 1px red;
	width:13%;
	height:50%;
	padding:50px;
	display:flex;
	flex-direction:column;
	justify-content:center;
	align-items:center;
}

.workers span {
	padding:10px;
	font-size:30px;
}

.workers img {
	width:100%;
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

/* 나머지 워커들 숨기는 애니메이션 */
.workers {
    display: flex;
    flex-direction: column;
    align-items: center;
    opacity: 1;
    transition: opacity 0.5s;
}

.workers.hidden {
    opacity: 0;
    pointer-events: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	
})
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
			<div class="workers">
				<img src="/videos/pjh.jpg">
				<span>박정호</span>
			</div>
			<div class="workers">
				<img src="/videos/a.jpg">
				<span>김정연</span>
			</div>
			<div class="workers">
				<img src="/videos/a.jpg">
				<span>전장근</span>
			</div>
			<div class="workers">
				<img src="/videos/a.jpg">
				<span>문슬범</span>
			</div>
			<div class="workers">
				<img src="/videos/a.jpg">
				<span>김체은</span>
			</div>
			<div class="workers">
				<img src="/videos/a.jpg">
				<span>최지혜</span>
			</div>
			<div class="workers">
				<img src="/videos/a.jpg">
				<span>김정은</span>
			</div>
		</div>
		<div class="third-box">
			
		</div>
	</div>
</body>
</html>