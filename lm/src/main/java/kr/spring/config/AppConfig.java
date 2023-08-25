package kr.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.intercepter.BsLoginCheckInterceptor;
import kr.spring.intercepter.LibAdminCheckInterceptor;
import kr.spring.intercepter.LibLoginCheckInterceptor;
import kr.spring.intercepter.MyPageHeaderInterceptor;
import kr.spring.intercepter.BsAdminCheckInterceptor;

//자바 코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	private MyPageHeaderInterceptor myPageHeader;
	private BsLoginCheckInterceptor bsLoginCheck;
	private BsAdminCheckInterceptor bsAdminCheck;
	private LibLoginCheckInterceptor libLoginCheck;
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
	public LibLoginCheckInterceptor interceptor4() {
		libLoginCheck = new LibLoginCheckInterceptor();
		return libLoginCheck;
	}
	@Bean
	public LibAdminCheckInterceptor interceptor5() {
		libAdminCheck = new LibAdminCheckInterceptor();
		return libAdminCheck;
	}
	
	//이메일 인증 설정 정보by.favau
	@Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
    	Properties prop = new Properties();
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.transport.protocol", "smtp");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.debug", "true");
    	
    	JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
    	javaMail.setHost("smtp.gmail.com");
    	javaMail.setPort(587);
    	javaMail.setDefaultEncoding("utf-8");
    	javaMail.setUsername("guitiar1296@gmail.com");
    	javaMail.setPassword("lguqjjcrqmlhmxqh");
    	javaMail.setJavaMailProperties(prop);
    	return javaMail;
    }
	
	//마이페이지 헤더 경로 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//MyPageHeaderInterceptor 설정.
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
				.addPathPatterns("/bookstore/service/askWrite.do");
		
		registry.addInterceptor(bsAdminCheck)
		.addPathPatterns("/bookstore/event/write.do")
		.addPathPatterns("/bookstore/event/update.do")
		.addPathPatterns("/bookstore/admin_list.do")
		.addPathPatterns("/bookstore/admin_update.do")
		.addPathPatterns("/bookstore/adminProductList.do")
		.addPathPatterns("/bookstore/adminProductModify.do")
		.addPathPatterns("/bookstore/adminOrderList.do")
		.addPathPatterns("/bookstore/adminOrderDetail.do")
		.addPathPatterns("/bookstore/service/announceWrite.do")
		.addPathPatterns("/bookstore/service/faqWrite.do")
		.addPathPatterns("/bookstore/service/faqModify.do")
		.addPathPatterns("/bookstore/service/answerWrite.do")
		.addPathPatterns("/bookstore/adminOrderTotal.do");

		
		registry.addInterceptor(libLoginCheck)
		.addPathPatterns("/library/bookApplyWrite.do")
		.addPathPatterns("/library/bookApplyUserList.do")
		.addPathPatterns("/library/bookApplyList.do")
		.addPathPatterns("/library/donationApply.do")
		.addPathPatterns("/library/insertFacility.do")
		.addPathPatterns("/library/facApplyWrite.do")
		.addPathPatterns("/library/insertProgram.do")
		.addPathPatterns("/library/insertFacility.do")
		.addPathPatterns("/library/service/user_boardAskList.do")
		.addPathPatterns("/library/service/boardAskWrite.do")
		.addPathPatterns("/library/service/boardAskDetail.do");
		
		registry.addInterceptor(libAdminCheck)
		.addPathPatterns("/library/boardannounce/list.do")
		.addPathPatterns("/library/boardannounce/write.do")
		.addPathPatterns("/library/boardannounce/detail.do")
		.addPathPatterns("/library/boardannounce/update.do")
		.addPathPatterns("/library/boardannounce/delete.do")
		.addPathPatterns("/library/boardannounce/update.do")
		.addPathPatterns("/library/apply/admin_bookApplyWrite.do")
		.addPathPatterns("/library/apply/admin_bookApplyList.do")
		.addPathPatterns("/library/bookproductadmin/admin_booklist.do")
		.addPathPatterns("/library/lib_book/admin_bookDetail.do")
		.addPathPatterns("/library/donationadmin/admin_donationlist.do")
		.addPathPatterns("/library/donationadmin/admin_donationupdate.do")
		.addPathPatterns("/library/donationadmin/admin_donationdelete.do")
		.addPathPatterns("/library/apply/admin_insertAdminFacility.do")
		.addPathPatterns("/library/apply/facilityAdminList.do")
		.addPathPatterns("/library/liblostitem/list.do")
		.addPathPatterns("/library/liblostitem/write.do")
		.addPathPatterns("/library/liblostitem/detail.do")
		.addPathPatterns("/library/liblostitem/update.do")
		.addPathPatterns("/library/liblostitem/delete.do")
		.addPathPatterns("/library/member/admin_list.do")
		.addPathPatterns("/library/member/admin_update.do")
		.addPathPatterns("/library/apply/programAdminList.do")
		.addPathPatterns("/library/apply/programAdminWrite.do")
		.addPathPatterns("/library/apply/insertProgram.do")
		.addPathPatterns("/library/apply/admin_programDetail.do")
		.addPathPatterns("/library/service/admin_boardAnswerWrite.do")
		.addPathPatterns("/library/service/admin_boardAskList.do")
		.addPathPatterns("/library/service/admin_boardAskDetail.do")
		.addPathPatterns("/library/service/boardAnswerWrite.do");
		
	}
}



