package spring.service;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

public class ChangePasswordService { // 비밀번호 변경 기능
	
	private MemberDao memberDao;// = new MemberDao();

	//	생성자
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = memberDao.selectByEmail(email); //기존 회원 정보를 읽어와서	
		if(member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPassword, newPassword); // 저장된 비밀번호를 바꾸세요.
		memberDao.update(member);	// 바뀐 회원 정보로 갱신하세요.
	}



}
