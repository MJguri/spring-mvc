package spring.survey;

import java.util.List;

public class AnsweredData {// 응답 정보 : 필드에 리스트 타입과 중첩타입이 존재 : 커맨드객체로 사용
	
	private List<String> responses;
	private Respondent res;
	
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	
	

}
