package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.AlreadyExistingMemberException;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	// step1==============================================================================================
	
	//@RequestMapping("/register/step1") // http://localhost:8090/ex00 /register/step1
	@RequestMapping("/step1")  //  => /register/step1
	public String handlerStep1() {
		return "register/step1"; // /WEB-INF/views/ register/step1 .jsp
	}
	
	// step2==============================================================================================
	
	//@RequestMapping("/register/step2")
//	@RequestMapping(value="/register/step2", method=RequestMethod.POST) //POST방식으로 요청이 들어왔을 때만 작동
//	public String handleStep2(HttpServletRequest request) {  // 방법1 : Request객체에서 직접 데이터를 받아오기
//		String agreeParam = request.getParameter("agree");
//		
//		if(agreeParam == null || !agreeParam.equals("true")) {
//			return "register/step1";
//		}
//		
//		return "register/step2";
//	}
	
	// step2 - POST==============================================================================================
	//@RequestMapping(value="/register/step2", method=RequestMethod.POST) //POST방식으로 요청이 들어왔을 때만 작동
	@RequestMapping(value="/step2", method=RequestMethod.POST) //  => /register/step2
	public String handlerStep2(  // 방법2 : @RequestParam어노테이션을 이용해서 데이터 받아오기
			@RequestParam(value="agree", defaultValue="false")Boolean agree,
			Model model) { 
		
		// 데이터를 보내주기 위한 모델 객체를 얻어와야 한다.
		if(!agree) {
			return "register/step1";
		}

		model.addAttribute("formData",new RegisterRequest());
		// 오류 방지를 위한 빈 formData를 전달
		
		return "register/step2";
	}
	
	// step2 - GET==============================================================================================
	@RequestMapping(value="/step2", method=RequestMethod.GET)
	public String handlerStep2Get() {
		//return "register/step1"; // 주소창에는  step2로 보여짐 , 보여지는 페이지는 step1
		
		return "redirect:register/step1";// 주소창에는  step1로 보여짐 , 보여지는 페이지는 step1
		//return "redirect:http://localhost:8090/ex00/register/step1"; // 전체주소를 입력해서 리다이렉트 할수 있다.
	}
	
// step3==============================================================================================
	
	// 클라이언트로 부터 데이터를 읽어오는 방법 1 
//	@RequestMapping(value="/step3")
//	public String handlerStep3(HttpServletRequest request) {
//		String email = request.getParameter("email");
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
//		
//		// 읽어온 데이터 처리
//		
//		return "register/step3";
//	}
	
	// 클라이언트로 부터 데이터를 읽어오는 방법 2
//	@RequestMapping(value="/step3")
//	public String handlerStep3(HttpServletRequest request,
//			@RequestParam(value="email")String email,
//			@RequestParam(value="name")String name
//			@RequestParam(value="password")String password,
//			@RequestParam(value="confirmPassword")String confirmPassword
//			) {
//		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
//		// 읽어온 데이터 처리
//		
//		return "register/step3";
//	}
	
   //여러 데이터를 한꺼번에 전달 받는 방식 => 커맨드 객체 => 매개값으로 전달 => 응답페이지에 값을 전달
   @RequestMapping(value="/step3", method=RequestMethod.POST)
      public String handlerStep3(@ModelAttribute("formData")RegisterRequest regReq) {
         
         try {
            memberRegisterService.regist(regReq);
            return "register/step3";
         }
         catch(AlreadyExistingMemberException e) {
            return "register/step2";
         }
   }


}
















