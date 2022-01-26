package spring.common;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.exception.MemberNotFoundException;

@ControllerAdvice("spring")
public class CommonExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException() {
		return "error/commonException";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handlerTypeMismatchException(TypeMismatchException e) {
		
		return "member/invalidate";
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public String handlerMemberNotFoundException(MemberNotFoundException e) {
		
		return "member/noMember";
	}

}
