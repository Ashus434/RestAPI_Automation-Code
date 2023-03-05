package Section11_SerializationAndDeserialization;

import java.util.List;

public class courses {
	
	private List<webAutomation> webAutomation; //Here we are declaring the webAutomation type as List type. Because webAutomation child JSON have a list of Indexes.
	private List<api> api;
	private List<mobile> mobile;
	
	//In Json file "webAutomation", "api", "mobile" are an Array.  that's why those are with square bracket[]. And this is the reason why we are declaring List data type for them. So Expectation is List of data from them.
	
	public List<webAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<webAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	
	
	public List<api> getApi() {
		return api;
	}
	public void setApi(List<api> api) {
		this.api = api;
	}
	
	
	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<mobile> mobile) {
		this.mobile = mobile;
	}
	
}
