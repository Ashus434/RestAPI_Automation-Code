package BodyDetails;

public class payload {

	
	public static String CoursePrice() {
		return "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 1310,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "},\r\n"
				+ "  {\r\n"
				+ "\"title\": \"Appium\",\r\n"
				+ "\"price\": 80,\r\n"
				+ "\"copies\": 5\r\n"
				+ "}\r\n"
				+ "  \r\n"
				+ "]\r\n"
				+ "}\r\n"
				+ "";
		
	}
	
	public static String AddBook(String isbn, String aisle) {
		
		String payload= "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}";
		return payload;	
	
	}
	
public static String DeleteBook(String id) {
		
		String payload= "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "} ";
		return payload;

}
}
