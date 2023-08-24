package kr.spring.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LibLoginCheckInterceptor implements HandlerInterceptor{
	private static final Logger logger = 
			LoggerFactory.getLogger(LibLoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			                 Object handler)
                                 throws Exception{
		logger.debug("<<LoginCheckInterceptor 진입>>");
		
		HttpSession session = request.getSession();
		//로그인 여부 검사
		if(session.getAttribute("mem_num")==null) {
			//로그인이 되지 않은 상태
			response.sendRedirect(
					request.getContextPath()+"/lm/login/template/loginMain.do?lo=2");
			return false;
		}
		return true;
	}
	
	
}
