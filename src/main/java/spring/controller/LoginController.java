package spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.AuthService;
import spring.validator.LoginCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.LoginCommand;

@Controller
public class LoginController {
	
	private AuthService authService;
	
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String form(Model model) {
//		model.addAttribute("loginCommand",new LoginCommand());
//		
//		return "login/loginForm";
//	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String form(LoginCommand loginCommand, @CookieValue(value="rememberEmail", required=false) Cookie rememberEmail) {
		
		if(rememberEmail != null ) {
			loginCommand.setEmail(rememberEmail.getValue());
			loginCommand.setRememberEmail(true);
		}
		
		return "login/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) { // 폼에서 로그인 기능을 요청
		// 1. 이메일, 비밀번호가 입력이 제대로 되었는지 검증 
		new LoginCommandValidator().validate(loginCommand, errors);
		
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		
		// 입력받은 이메일과 비밀번호로 로그인(서비스 객체) 시도 
		try {
			AuthInfo authInfo =  authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			//로그인 정보를 기록할 세션 코드
			session.setAttribute("authInfo", authInfo);
			
			//이메일 저장용 쿠키 
			Cookie rememberCookie = new Cookie("rememberEmail",loginCommand.getEmail());
			
			rememberCookie.setPath("/login");  // /login 경로에서 요청한게 아니면 쿠키안줌 . 만들때 설정 
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*365);
			}
			else {
				rememberCookie.setMaxAge(0);
			}
			
			response.addCookie(rememberCookie);
			
		}
		catch(IdPasswordNotMatchingException e) {
			//이메일이 없거나 비밀번호가 틀린 경우 
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
		
		return "login/loginSuccess";
		
	}
}
