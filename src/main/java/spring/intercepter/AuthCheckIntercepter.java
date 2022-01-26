package spring.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthCheckIntercepter implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러가 수행되기 전 구동되는 기능 => 로그인 인증 
		
		HttpSession session = request.getSession();
		if(session != null) {
			Object auth = session.getAttribute("authInfo");
			if(auth != null) {
				return true;
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/login");
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러가 수행된 후 , 뷰페이지가 작동되기전 기능
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰페이지가 작동된 후 사용되는 기능 
		
	}

}
