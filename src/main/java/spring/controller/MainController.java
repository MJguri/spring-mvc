package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")  // localhost:8090/ex00/main
	public String main() {
		return "main";		//  /WEB-INF/views/main.jsp
	}
}
