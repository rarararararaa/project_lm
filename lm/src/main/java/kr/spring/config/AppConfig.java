package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.intercepter.BsLoginCheckInterceptor;
import kr.spring.intercepter.LibAdminCheckInterceptor;
import kr.spring.intercepter.MyPageHeaderInterceptor;
import kr.spring.intercepter.BsAdminCheckInterceptor;

//자바 코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	private MyPageHeaderInterceptor myPageHeader;
	private BsLoginCheckInterceptor bsLoginCheck;
	private BsAdminCheckInterceptor bsAdminCheck;
	private LibAdminCheckInterceptor libAdminCheck;
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		//해당 경로에 xml 설정 파일을 넣음
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/bookstoreMain.xml",
				"/WEB-INF/tiles-def/libMain.xml",
				"/WEB-INF/tiles-def/jeongho.xml",
				"/WEB-INF/tiles-def/jihye.xml",
				"/WEB-INF/tiles-def/mona.xml",
				"/WEB-INF/tiles-def/msb.xml",
				"/WEB-INF/tiles-def/wjswkdrms.xml",
				"/WEB-INF/tiles-def/favau.xml",
				"/WEB-INF/tiles-def/EESAMSAOH.xml"
				
				
		});
		return configurer;
	}
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = 
				                    new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	//bean 설정
	@Bean
	public MyPageHeaderInterceptor interceptor() {
		myPageHeader = new MyPageHeaderInterceptor();
		return myPageHeader;
	}
	@Bean
	public BsLoginCheckInterceptor interceptor2() {
		bsLoginCheck = new BsLoginCheckInterceptor();
		return bsLoginCheck;
	}
	
	@Bean
	public BsAdminCheckInterceptor interceptor3() {
		bsAdminCheck = new BsAdminCheckInterceptor();
		return bsAdminCheck;
	}
	@Bean
	public LibAdminCheckInterceptor interceptor4() {
		libAdminCheck = new LibAdminCheckInterceptor();
		return libAdminCheck;
	}
	//마이페이지 헤더 경로 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//MyPageHeaderInterceptor 설정
		registry.addInterceptor(myPageHeader)
		.addPathPatterns("/lm/mypage/main/myPageMain.do")
		.addPathPatterns("/lm/mypage/asklist/askListMain.do")
		.addPathPatterns("/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do")
		.addPathPatterns("/lm/mypage/wantbooklist/wantBookListMain.do")
		.addPathPatterns("/lm/mypage/programapplylist/programApplyListMain.do")
		.addPathPatterns("/lm/mypage/facilityapplylist/facilityApplyListMain.do")
		.addPathPatterns("/lm/mypage/bookreservationlist/bookReservationListMain.do")
		.addPathPatterns("/lm/mypage/booklostlist/bookLostListMain.do")
		.addPathPatterns("/lm/mypage/eventparticipatelist/eventParticipateListMain.do")
		.addPathPatterns("/lm/mypage/usedbookapplylist/usedBookApplyListMain.do")
		.addPathPatterns("/lm/mypage/zzimbooklist/zzimBookListMain.do")
		.addPathPatterns("/lm/mypage/zzimbooklist/zzimBookListMain.do")
		.addPathPatterns("/lm/mypage/bookwritelist/bookWriteListMain.do")
		.addPathPatterns("/lm/mypage/bookreportlist/bookReportListMain.do")
		.addPathPatterns("/lm/mypage/couponlist/couponListMain.do")
		.addPathPatterns("/lm/mypage/myedit/myEditMain.do")
		.addPathPatterns("/lm/mypage/pointinfo/pointInfoMain.do")
		.addPathPatterns("/lm/mypage/gradeinfo/gradeInfoMain.do")
		.addPathPatterns("/lm/mypage/memberout/memberOutMain.do")
		.addPathPatterns("/lm/mypage/orderlist/orderListMain.do");
		
		registry.addInterceptor(bsLoginCheck)
				.addPathPatterns("/bookstore/service/askWrite.do")
				.addPathPatterns("/bookstore/payment/cart.do")
				.addPathPatterns("/bookstore/payment/cartAction.do")
				.addPathPatterns("/bookstore/payment/order.do")
				.addPathPatterns("/bookstore/review/reviewWrite.do")
				.addPathPatterns("/bookstore/review/reviewModify.do")
				.addPathPatterns("/bookstore/review/reviewDelete.do")
				.addPathPatterns("/library/rent/updateRentDeadline.do")
				.addPathPatterns("/library/rent/rentReservationBook.do")
				.addPathPatterns("/library/rent/cancelReservationBook.do");
		
		registry.addInterceptor(bsAdminCheck)
		.addPathPatterns("/bookstore/event/write.do")
		.addPathPatterns("/bookstore/event/update.do")
		.addPathPatterns("/bookstore/event/eventAnnounceWrite.do");
		
		//도서관 관리자 체크
		registry.addInterceptor(libAdminCheck)
		.addPathPatterns("/library/template/libAdmin.do")
		.addPathPatterns("/library/member/admin_list.do")
		.addPathPatterns("/library/bookproductadmin/admin_booklist.do")
		.addPathPatterns("/library/donationadmin/admin_donationlist.do")
		.addPathPatterns("/library/boardannounce/list.do")
		.addPathPatterns("/library/liblostitem/list.do")
		.addPathPatterns("/library/service/admin_boardAskList.do")
		.addPathPatterns("/library/apply/programAdminList.do")
		.addPathPatterns("/library/rent/rentHistoryList.do")
		.addPathPatterns("/library/rent/updateRentHistory.do");
		
	}
}



