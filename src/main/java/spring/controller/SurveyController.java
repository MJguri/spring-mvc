package spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.survey.AnsweredData;
import spring.survey.Question;

@Controller
@RequestMapping("/survey")   // 요청
public class SurveyController {
	
	private List<Question> createQuestion(){// 설문 내용을 만들기 위한 메서드
		Question q1 = new Question(
				"당신의 역할은 무엇입니까?",
				Arrays.asList("백엔드","프론트","풀스택"));
		
		Question q2 = new Question(
				"많이 사용하는 개발 도구는 무엇인가요?",
				Arrays.asList("넷빈즈","이클립스","인텔리J"));
		
		Question q3 = new Question(
				"하고 싶은 말을 적어 주세요?");
		
		return Arrays.asList(q1,q2,q3);
	}
	

//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//			//      컨트롤러에서 생성된 데이터를 뷰페이지에 넘겨주기 위해서 사용하는 객체 : (=request객체)
//		List<Question> questions = createQuestion(); //질문지 생성
//		model.addAttribute("questions",questions); //질문지를 Model에 담아서 뷰페이지로 전송
//		
//		return "survey/surveyForm";
//	}
	
	// 실제적으로 컨트롤러에서 서블릿으로 반환되는 값 ModelAndView를 이용해서 반환해봅시다.
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		
		List<Question> questions = createQuestion(); //질문지 생성
		
		mav.addObject("questions",questions);  //Model의 역할
		mav.setViewName("survey/surveyForm"); // View의 역할		
		
		return mav;
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
