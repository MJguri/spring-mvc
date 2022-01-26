package spring.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand { // 커맨드 객체 => 스프링은 자동 형 변환 => 날짜는 형변환 불가 
	
	@DateTimeFormat(pattern="yyyyMMddhh")
	private Date from;
	@DateTimeFormat(pattern="yyyyMMddhh")
	private Date to;
	
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
}
