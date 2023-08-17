$(function() {
	var currentUrl = window.location.pathname;
	//window.location 시 localhost 부터 시작해 주소비교 어려움,현재 페이지의 경로 부분만 가져오도록
	//console.log(currentUrl);
	
	$('.sb-page-nav li.sb-menu-item > a').each(function() {
		var menuLink = $(this).attr('href');
		var subItemExists = $(this).siblings('.sb-menu-sub').find('.sb-menu-sub-item').length > 0;
		//console.log(subItemExists);
		var menu = $(this).closest('.sb-menu-item');
		var sub = $(this).siblings('.sb-menu-sub');

		if (subItemExists) {
			var subMenuItems = $(this).siblings('.sb-menu-sub').find('.sb-menu-sub-item a');
			subMenuItems.each(function() {
				console.log('비교'+$(this).attr('href'))
				if ($(this).attr('href') === currentUrl) {
					// 추가 처리 로직
					//console.log('하위 요소가 있는 메뉴 중 현재 페이지의 링크와 일치합니다.');
					menu.addClass('is_menu1--opened');
					$(this).closest('.sb-menu-sub-item').addClass('is_menu2--opened')
					sub.slideDown();
				}
			});
		}
		else {
			if (menuLink === currentUrl) {
				// 추가 처리 로직
				//console.log('하위 요소가 없는 메뉴 중 현재 페이지의 링크와 일치합니다.');
				menu.addClass('is_menu1--opened');
			}
		}
		
	});

	
	var currentmenu = $('.is_menu1--opened');
	currentmenu.addClass('is_current_page');
	currentmenu.find('.is_menu2--opened').attr("aria-label", "현재 페이지");
	if ($('.is_current_page .is-sub__items').length > 0) {
		$('.is_current_page .is-sub__items')[0].title = '접기';
	}

	/*************************************************************************************
  	lnb
	**************************************************************************************/
	$('.sb-page-nav li.sb-menu-item > a').click(function(e) {
		var menu = $(this).closest('.sb-menu-item');
		var sub = $(this).siblings('.sb-menu-sub');
		var subSi = $(this).closest('.sb-menu-item').siblings().not('.is_menu1--opened').find('.sb-menu-sub');

		//class
		var currentActive = 'is_menu1--opened';
		var currentSlide = 'is_menu2--slideUp';
		var active = 'is_menu--opened';


		if (!sub.length) {
			return;
		}
		e.preventDefault();

		if (menu.hasClass(currentActive)) {//현재 페이지 일 때,
			if (menu.hasClass(currentSlide)) {
				menu.removeClass(currentSlide);
				menu.addClass(active);
				menu[0].children[0].title = '접기';
				sub.slideDown();
			} else {
				menu.addClass(currentSlide);
				menu.removeClass(active);
				sub.slideUp();
				menu[0].children[0].title = '펼치기';
			}
		} else {//현재 페이지 외
			if (menu.hasClass(active)) {
				menu.removeClass(active);
				menu[0].children[0].title = '펼치기';
				sub.slideUp();
			} else {
				menu.siblings('li').removeClass(active);
				subSi.slideUp();
				menu.addClass(active);
				menu[0].children[0].title = '접기';
				sub.slideDown();
			}
		}

	});
	
	
	
});