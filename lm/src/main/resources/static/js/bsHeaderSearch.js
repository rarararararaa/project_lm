/*
$(function() {

	function performSearch() {
		let keyword = $('.ip_search').val(); //박스에 입력한 값 가져오기
		let url = "${pageContext.request.contextPath}";
		let categoryNum = $('.').val();
		// 검색 페이지로 이동
		window.location.href = url + '/library/template/libSearchMain.do?categoryNum='+categoryNum+'&orderByNum=1&keyword=' + keyword;
	}

	$('.btn_search').click(function(event) {
		event.preventDefault();
		performSearch();
	});

	$('.search-text').keypress(function(event) {
		if (event.which === 13) {  // Enter 키의 keyCode는 13입니다.
			event.preventDefault();
			performSearch();
		}
	});
});
*/