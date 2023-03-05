package Section08;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class AutomatingJIRAAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Before Run the Write & Run the Code first start the JIRA server first and do login the application.

		RestAssured.baseURI="http://localhost:8080";
		
		//Login Scenario
		SessionFilter session= new SessionFilter();
		String Response=given().header("Content-Type", "application/json").body("{ \r\n"
				+ "    \"username\": \"Ashus434\", \r\n"
				+ "    \"password\": \"MANGOTREE173\"\r\n"
				+ "}").filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//System.out.println(Response);//if we will use log in our response line that would provide a better report instead of System.out.println(Response).
		/*The Response will generate the session details and that details will automatically be stored into session object reference. Hence 
		  we have used that session (Inside Filter() method) variable in given() And we all know in given() method we are using All input details.
		*/
		
		//Create Issue Scenario
	    String Response_1 = given().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"fields\": \r\n"
				+ "    {\r\n"
				+ "        \"project\": \r\n"
				+ "        {\r\n"
				+ "            \"key\": \"RSA\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Credit Card Defect\",\r\n"
				+ "        \"description\": \"Creating my first bug\",\r\n"
				+ "        \"issuetype\":\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
	    
	     JsonPath js_1=new JsonPath(Response_1); 
	     int IssueID = js_1.getInt("id");
	     System.out.println(IssueID);
	
	
		//Add Comment Scenario
	     String Response_2 = given().pathParam("issueIdOrKey", IssueID) .header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \"I'm adding a comment\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("rest/api/2/issue/{issueIdOrKey}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
	     
	     JsonPath js_2=new JsonPath(Response_2); 
	     int CommentID = js_2.getInt("id");
	     System.out.println(CommentID);
	     
	     
	   //Update the Comment Scenario
	     
	   /*  String Response_3 = given().pathParam("issueIdOrKey", IssueID).pathParam("id", CommentID)
	    		.header("Content-Type", "application/json")
	    		.body("{\r\n"
	    				+ "    \"body\": \"I'm updating the existing comment\",\r\n"
	    				+ "    \"visibility\": {\r\n"
	    				+ "        \"type\": \"role\",\r\n"
	    				+ "        \"value\": \"Administrators\"\r\n"
	    				+ "    }\r\n"
	    				+ "}").filter(session)
		.when().post("rest/api/2/issue/{issueIdOrKey}/comment/{id}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();  	*/  
	 
	     
	     
	     
	     //Add Attachment
	     
	     /* Note :- Most of the Company Like FaceBook, Twitter, JIRA are providing their contract through "curl" command.
	     So we should have to Interpret the command */
	     
	    // curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments
	     
	     /* D- Stands for different parameters
	      * 1st admin is the username
	      * 2nd admin is the password
	      * X- Stands for Http method 
	      * H- Stands for Header
	      */
	     /*Note :-  First Right Click on Project then Select File and then Create Jira.txt file inside project.*/
	
	given().header("X-Atlassian-Token","no-check").filter(session).pathParam("issueIdOrKey", IssueID)
	.multiPart("file",new File("Jira.txt")).header("Content-Type", "multipart/form-data")
	
	/* In RestAPI if u want to Add Attachment then u have to use "Multipart()" method. So inside this
	method first we have to use a "file" keyword, then we have create a file class object by writing "new file" & inside of that we have pass our
	text file as an argument. in Java we have class called file so here we just have created an object of it and passing the text file there
	so java will understand oh this is a file 
	 */
	
	/* Except Add Attachment code, we have used body content as Json format so that's why we have header("Content-Type", "application/json")
	 * But here In Add Attachment code we have used multiPart data so that's why we have used this header("Content-Type", "multipart/form-data") 
	 * along with header("X-Atlassian-Token", "no-check"). 
	 */
	
	.when().post("rest/api/2/issue/{issueIdOrKey}/attachments")
	.then().log().all().assertThat().statusCode(200);
	
	}

}
