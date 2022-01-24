package spring.vo;

public class RegisterRequest {// 사용자로부터 받는 데이터 => 커맨드 객체

	private String email;
	private String password;   
	private String confirmPassword; //비밀번호 확인
	private String name;
	
	public boolean isPasswordEqualToConfirmPassword() { // 프론트엔드 자바스크립트로 만드는 영역
		return password.equals(confirmPassword);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
