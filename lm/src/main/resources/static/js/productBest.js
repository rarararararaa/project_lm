$(function(){
	$('.9788954695053').addClass('point');
	$('.9788932923413').addClass('point');
	//월간
	new Swiper('.month', {
	  //initialSlide: -2,//슬라이드 시작 지점
	  autoplay: {//자동 슬라이드
	    delay: 3000,
		disableOnInteraction: false
	  },
	  loop: true,//슬라이드 반복 여부
	//  loopAdditionalSlides : 1,// 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
	  slidesPerView: 5,//한 슬라이드에 보여줄 갯수
	  spaceBetween: 1,//슬라이드 사이의 공맥
	  centeredSlides: false, //센터모드
/*	  pagination: {//호출(pager)여부
	    el: '.swiper-pagination',//버튼을 담을 태그 설정
	    clickable: true //버튼 클릭 여부
	  },*/
	  navigation: {//버튼
	    prevEl: '.month-prev',
	    nextEl: '.month-next'
	  },
		autoHeight:false, //현재 활성 슬라이드 뫂이에 맞게 조정
		on: {//이벤트
			 slideChangeTransitionStart: function(){
				//alert(this.activeIndex);
				//활성화된 슬라이드가 바뀔때마다 호출
				$('.swiper-slide').removeClass('point');
				$('.swiper-slide-active').next().next().addClass('point');
			}
		}
	})
	//연간
	new Swiper('.year', {
	  //initialSlide: -2,//슬라이드 시작 지점
	  autoplay: {//자동 슬라이드
	    delay: 3500,
		disableOnInteraction: false
	  },
	  loop: true,//슬라이드 반복 여부
	//  loopAdditionalSlides : 1,// 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
	  slidesPerView: 5,//한 슬라이드에 보여줄 갯수
	  spaceBetween: 1,//슬라이드 사이의 공맥
	  centeredSlides: false, //센터모드
/*	  pagination: {//호출(pager)여부
	    el: '.swiper-pagination',//버튼을 담을 태그 설정
	    clickable: true //버튼 클릭 여부
	  },*/
	  navigation: {//버튼
	    prevEl: '.year-prev',
	    nextEl: '.year-next'
	  },
		autoHeight:false, //현재 활성 슬라이드 뫂이에 맞게 조정
		on: {//이벤트
			 slideChangeTransitionStart: function(){
				//alert(this.activeIndex);
				//활성화된 슬라이드가 바뀔때마다 호출
				$('.swiper-slide').removeClass('point');
				$('.swiper-slide-active').next().next().addClass('point');
			}
		}
	})
	//분야별
	new Swiper('.cate-best-swiper', {
	  //initialSlide: -2,//슬라이드 시작 지점
	  direction: 'vertical',//수직
	  autoplay: {//자동 슬라이드
	    delay: 4000,
		disableOnInteraction: false
	  },
	  loop: true,//슬라이드 반복 여부
	  //loopAdditionalSlides : 1,// 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
	  //spaceBetween: 10,//슬라이드 사이의 공맥
	  //centeredSlides: true, //센터모드
/*	  pagination: {//호출(pager)여부
	    el: '.swiper-pagination',//버튼을 담을 태그 설정
	    clickable: true //버튼 클릭 여부
	  },*/
	  navigation: {//버튼
	    prevEl: '.cate-prev',
	    nextEl: '.cate-next'
	  },
		//autoHeight:false, //현재 활성 슬라이드 뫂이에 맞게 조정
	})

})