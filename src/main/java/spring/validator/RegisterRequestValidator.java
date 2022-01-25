package spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.vo.RegisterRequest;

public class RegisterRequestValidator implements Validator{
	
	//이메일 검증을 위한 정규식 
	private static final String emailExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// 전달받은 객체가 커맨드 객체로 변환이 가능한지를 체크
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 실제 검증용 코드를 작성하는 부분 
		// 이메일이 입력이 되었는가? 이메일 형식은 맞는가?
		// 이름은 입력이 되었는가?
		// 비밀번호와 비밀번호 확인은 입력이 되었는가?
		// 비밀번호와 비밀번호 확인 값이 일치하는가?	
		
		RegisterRequest regReq= (RegisterRequest)target;
		
		if(regReq.getEmail()==null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required"); // JSP에 에러 출력태그에 email이라는 속성에 required 값을 전달한다. 
		}
		else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) { // matches() 일치한다면 true, 일치 하지 않는다면 false
				errors.rejectValue("email", "bad");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");  // regReq. name의 필드를 가져와서 비어잇는지, 공백이 있는지 확인 후 오류가있다면 required 메시지
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		if(!regReq.getPassword().isEmpty()) { // 비어있지 않을때 비교 
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
		
		// 검증을 어디서 할까요??
		
	}

}
