package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.intercepter.MyPageHeaderInterceptor;

//자바 코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	private MyPageHeaderInterceptor myPageHeader;
	
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
	//마이페이지 헤더 경로 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//MyPageHeaderInterceptor 설정
		registry.addInterceptor(myPageHeader)
			.addPathPatterns("/lm/mypage/main/myPageMain.do");
	}
}



