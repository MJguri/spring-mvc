package spring.service;

import java.util.Date;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistingMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService {// 서블릿(Action 클래스)
	
	private MemberDao memberDao; 

	public MemberRegisterService(MemberDao memberDao) { 
		this.memberDao = memberDao;						
		// 생성자
	}
	public void regist(RegisterRequest req) {// 사용자로부터 회원가입을 받으면 작동하는 기능
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new AlreadyExistingMemberException("이미 존재하는 계정입니다.:"+req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(),req.getPassword(),req.getName(),new Date()
				);
		memberDao.insert(newMember); //회원 가입
	}
	



}
