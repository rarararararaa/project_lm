$(function(){
	//드롭박스 시작
	$(window).scroll(function () {
                set = $(document).scrollTop()+"px";
                $('#banner').animate({top:set},{duration:1000,queue:false});
            });

            $(".dropdown").hover(function() {

                $(this).find(".lnb_dp2").slideDown("fast");

            }, function(){
                $(this).find(".lnb_dp2").hide();
            });

            $(".sub_dropdown").hover(function(){
                $(this).addClass("on");
            },function(){
                $(this).removeClass("on");
    });
	//드롭박스 끝
});