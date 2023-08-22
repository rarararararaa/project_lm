<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LM문고 | 검색 화면</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bookstoreStyle.css">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	let categoryNum = $('.categoryNum').val();
	let orderByNum = $('.orderByNum').val();
	let keyword = $('.keyword').val();
	let totalCount = $('.totalCount').val();
	let currentPage;	//현재 보고 있는 화면
	let pageSize = 10;	//화면에 보여줄 게시판 갯수
	let pageBlock = 10;	//페이지 표시 단위
	
	if (keyword === "") {
        $('.output-search-result').append('<span>검색 결과가 없습니다.</span>');
    } else {
    	
    	setPage(totalCount);
    }
	
	
	function setPage(totalCount){
		
		console.log('페이지 처리 시작');
	  	console.log('개수:'+totalCount);
		
		$('.paging-btn').empty();
		if(totalCount == 0){
			return;
		}
		
		let totalPage = Math.ceil(totalCount/pageSize);
		
		if(currentPage == undefined || currentPage == ''){
			currentPage = 1;
		}
		//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
		
		let startRow = (currentPage - 1) * pageSize + 1;
		let endRow = currentPage * pageSize;
		
		//시작 페이지와 마지막 페이지 값을 구하기
		let startPage = Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;
		let endPage = startPage + pageBlock - 1;
		
		//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if(endPage > totalPage){
			endPage = totalPage;
		}
		
		let add='';
		if(startPage>pageBlock){
			add += '<li data-page='+(startPage-1)+'>&lt;</li>';
		}
		
		for(var i=startPage;i<=endPage;i++){
			add += '<li data-page='+i+'>'+i+'</li>';
		}
		if(endPage < totalPage){
			add += '<li data-page='+(startPage+pageBlock)+'>&gt;</li>';;
		}
		//ul 태그에 생성한 li를 추가
		$('.paging-btn').append(add);
		console.log("============function 안===========");
		console.log("totalPage = "+totalPage);
		console.log("startPage = " +startPage);
		console.log("endPage = "+endPage);
		console.log("currentPage = "+currentPage);
		console.log("pageSize = "+pageSize);
		console.log("pageBlock = "+pageBlock);
		console.log("startRow = "+startRow);
		console.log("endRow = "+endRow);
		console.log("============function 끝===========")
		//ajax 시작
		$('.output-search-result').empty();
		$.ajax({
			type:'get',
			url:'/searchProducts/'+ categoryNum + '/' + orderByNum + '/' + keyword+'/'+startRow+'/'+endRow,
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(param){
			//댓글 목록 작업
				let headoutput = '<span>"'+param.keywordResult+'"에 대한 검색결과 총 '+param.count+'개</span>';
				headoutput += '<input type="hidden" value="'+param.count+'" id="result-totalCount">';
				$('.output-search-result').append(headoutput);
				$(param.list).each(function(index,item){
					let output = '<div class="search-items">';
					output += '<div class="search-imgs"><a href="${pageContext.request.contextPath}/bookstore/product/productDetail.do?store_product_isbn13='+item.store_product_isbn13+'"><img src="'+item.store_product_cover+'"></a></div>';
					output += '<div class="search-textes">';
					output += '<div>제목 : ' + item.store_product_title +'</div>';
					output += '<div>저자 : ' + item.store_product_author + ' | 출판사 : ' + item.store_product_publisher + '</div>';				
					output += '<div>내용 : ' + item.store_product_description + '</div>';
					output += '<div>등록 날짜 : '+ item.store_product_pubdate+' | 원가 : '+item.store_product_pricestandard+' | 할인가 : '+item.store_product_pricesales +'</div>';
					output += '<div>후기 : '+ item.store_product_ratingcount+' 개 | 점수 : '+item.store_product_ratingscore+'</div>';
					output += '</div>';
					output += '</div>';
					//문서 객체에 추가
					$('.output-search-result').append(output);
				});
			},
			error:function(){
				//로딩 이미지 감추기
				alert('네트워크 오류');
			}
		});
	}
	
	$(document).on('click','.paging-btn li',function(){
		//페이지 번호를 읽어들임
		currentPage = $(this).attr('data-page');
		//목록 호출
		setPage(totalCount);
	});
});
</script>
<style>
.search-main-box {
	width:1200px;
	margin: 0 auto;
}

.search-items {
	display:flex;
	justify-content:flext-start;
	align-items:center;
}
.search-imgs {
	display:flex;
	justify-content:flext-start;
	align-items:center;
	min-width:200px;
	min-height:300px;
	max-height:300px;
	overflow: hidden;
	margin-right:20px;
	}
.search-textes div {
	padding:10px;
}

.output-search-result span{
	display:block;
	margin:0 auto;
	font-size:25px;
	text-align: center;
	padding:20px;
	
}

.paging-btn {
	width:800px;
	margin:0 auto;
	display:flex;
	flex-direction:row;
	justify-content:center;
	align-items:center;
	list-style:none;
}
.paging-btn li {
	font-size:20px;
	padding:20px;
}
.paging-btn li:hover {
	color:green;
	cursor:pointer;
}
</style>
</head>
<body>
<jsp:include page="../template/bsHeader.jsp"/>
	<div class="search-main-box">
		<div id="used_div_box">
			<input type="hidden" value="${categoryNum}" name="categoryNum" class="categoryNum">
			<input type="hidden" value="${orderByNum}" name="orderByNum" class="orderByNum">
			<input type="hidden" value="${keyword}" name="keyword" class="keyword">
			<input type="hidden" value="${totalCount}" class="totalCount">
		</div>
		<div class="output-search-result"></div>
		<div class="paging-good"><ul class="paging-btn"></ul></div>
	</div>
<jsp:include page="../template/bsFooter.jsp"/>
</body>
</html>