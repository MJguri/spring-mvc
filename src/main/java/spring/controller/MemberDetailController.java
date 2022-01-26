package spring.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

@Controller
public class MemberDetailController {
	private MemberDao dao;

	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/member/detail/{id}")
	public String detail(@PathVariable("id") Long memberId, Model model) {
		
		Member m = dao.selectById(memberId);
		
		if(m==null) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute("list",m);
		
		return "member/memberDetail";
	}
	

	
	
	
}
