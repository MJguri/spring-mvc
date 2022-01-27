package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.common.CommonExceptionHandler;
import spring.controller.ChangePwdController;
import spring.controller.ListController;
import spring.controller.LoginController;
import spring.controller.LogoutController;
import spring.controller.MemberDetailController;
import spring.controller.RegisterController;
import spring.controller.SurveyController;
import spring.dao.MemberDao;
import spring.intercepter.AuthCheckIntercepter;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberDao dao;
	@Autowired
	private MemberRegisterService regSvc;
	@Autowired
	private ChangePasswordService changePwdSvc;
	@Autowired
	private AuthService authService;
	

	@Bean
	public RegisterController registerController() {
		RegisterController regCon = new RegisterController();
		regCon.setMemberRegisterService(regSvc);
		return regCon;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean
	public LoginController loginController() {
		LoginController loginCon = new LoginController();
		loginCon.setAuthService(authService);
		return loginCon;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public ChangePwdController changePwdController() {
		return new ChangePwdController(changePwdSvc);
	}
	
	@Bean
	public ListController listController() {
		ListController listCon = new ListController();
		listCon.setDao(dao);
		return listCon;
	}
	
	@Bean
	public MemberDetailController memberDetailController() {
		MemberDetailController mDetailCon = new MemberDetailController();
		mDetailCon.setDao(dao);
		return mDetailCon;
	}
	
	@Bean
	public CommonExceptionHandler commonExceptionHandler() {
		return new CommonExceptionHandler();
	}
	
	@Bean   //사용할 인터셉터 빈
	public AuthCheckIntercepter authCheckIntercepter() {
		return new AuthCheckIntercepter();
	}
}
