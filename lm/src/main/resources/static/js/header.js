$(function(){
    let selectedValue = 1;

    $("#categoryNum").change(function() {
        selectedValue = $(this).val();
        console.log("선택된 값: " + selectedValue);
    });
	
	
    function performSearch() {
        let keyword = $('.ip_search').val();
        let categoryNum = selectedValue;
        // 검색 페이지로 이동
        window.location.href ='/bookstore/search/searchMain.do?categoryNum='+categoryNum+'&orderByNum=1&keyword='+keyword;
    }

    $('.btn_search').click(function() {
        performSearch();
    });
	
	
    // 검색 입력 상자에서 엔터 키 눌렀을 때도 performSearch() 호출
    $('.ip_search').keypress(function(event) {
        if (event.which === 13) {
            event.preventDefault();  // 기본 동작 방지
            performSearch();
        }
    });
    
    
});