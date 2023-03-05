package Section08;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

  /* (In this code we are adding one attachment file multiple times for a single issue 
      by running this code multiple times also comment will add multiple times ) 
                                        &
     (Here we are  Parsing complex Json & Limiting Json Response through Query Parameter )
*/
public class AutomatingJIRAAPIPart2 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Before Run the Write & Run the Code first start the JIRA server first and do login the application.

		RestAssured.baseURI="http://localhost:8080";
		
		//Login Scenario
		SessionFilter session= new SessionFilter();
		String Response=given().relaxedHTTPSValidation().header("Content-Type", "application/json").body("{ \r\n"
				+ "    \"username\": \"Ashus434\", \r\n"
				+ "    \"password\": \"MANGOTREE173\"\r\n"
				+ "}").filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String ExpectedMessage = "I'm adding a comment" ;
		/*Note:- "relaxedHTTPSValidation()" method will help to do the HTTPS Certificate Validation. But For our current web site it is not there.
		 * So Just for Knowledge.
		 */
		
		//Add Comment Scenario
	     String Response_2 = given().pathParam("issueIdOrKey", "10310") .header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \""+ExpectedMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("rest/api/2/issue/{issueIdOrKey}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
	     
	     JsonPath js_2=new JsonPath(Response_2); 
	     String CommentID = js_2.get("id");
	     System.out.println(CommentID);
	     
	     
          //Add Attachment
	      given().header("X-Atlassian-Token","no-check").filter(session).pathParam("issueIdOrKey", "10310")
	      .multiPart("file",new File("Jira.txt")).header("Content-Type", "multipart/form-data")
	      .when().post("rest/api/2/issue/{issueIdOrKey}/attachments")
	      .then().log().all().assertThat().statusCode(200);
	      
	      
	      //Get Issue (Only Retrieved with Path Parameter)
	    /* String IssueDetails = given().filter(session).pathParam("issueIdOrKey", "10310").log().all()
	      .when().get("rest/api/2/issue/{issueIdOrKey}")
	      .then().log().all().extract().response().asString(); */
	      
	      
	      
		   //Get Issue (Retrieved with both Path & Query)
	       String IssueDetails_2 = given().filter(session)
	    		   .pathParam("issueIdOrKey", "10310").queryParam("fields", "comment").log().all()
	    		   
	      .when().get("rest/api/2/issue/{issueIdOrKey}")
	      .then().log().all().extract().response().asString();	     
	      
		     JsonPath js_3=new JsonPath(IssueDetails_2); 
		     int CommentsCount = js_3.getInt("fields.comment.comments.size()");
		     
		     for(int i=0; i<CommentsCount; i++ ) {
		    	 
		    	System.out.println(js_3.getInt("fields.comment.comments ["+i+"].id") );
		    	String ParticularCommentID =  js_3.get("fields.comment.comments ["+i+"].id").toString();
		    	
		    	if(ParticularCommentID.equalsIgnoreCase(CommentID))
		    	{
		    		String Actualmessage = js_3.get("fields.comment.comments ["+i+"].body").toString();
		    		System.out.println(Actualmessage);
		    		Assert.assertEquals(ExpectedMessage, Actualmessage);
		    	}
		     }
		     
		     /* Note:- Use Json Editor here, after just got the issue details through Path & Query Parameter.
		      * First Don't use the Json Path & For loop code. Once u used the Json Editor then use the code.
		      */
	     
}
}
