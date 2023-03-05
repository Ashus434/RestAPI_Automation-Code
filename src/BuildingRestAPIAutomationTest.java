import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //This package we have to write manually because it's a static one & Eclipse won't suggest for given() method.
	
public class BuildingRestAPIAutomationTest {
	

		public static void main(String[] args) {
			// TODO Auto-generated method stub

			
			// validate if Add Place API is working as expected 
					
					/* RestAssured works on 3 Principle i.e given, when & then */
			
					//given- All input details (apart from these two (resource,http method) all will be going under given method as input )
					//when - Hit/Submit the API -Resource, http method(HTTP methods which are commonly used to communicate with Rest API’s. Here it is POST)
					//Then - validate the response
			
			        /* First we have to set Base URL through baseURI */
					RestAssured.baseURI= "https://rahulshettyacademy.com"; 
					
					given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
					.body("{\r\n"
							+ "  \"location\": {\r\n"
							+ "    \"lat\": -38.383494,\r\n"
							+ "    \"lng\": 33.427362\r\n"
							+ "  },\r\n"
							+ "  \"accuracy\": 50,\r\n"
							+ "  \"name\": \"Frontline house\",\r\n"
							+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
							+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
							+ "  \"types\": [\r\n"
							+ "    \"shoe park\",\r\n"
							+ "    \"shop\"\r\n"
							+ "  ],\r\n"
							+ "  \"website\": \"http://google.com\",\r\n"
							+ "  \"language\": \"French-IN\"\r\n"
							+ "}") //body("") method always need the Json as a String Format. 
					
					.when().post("maps/api/place/add/json")
					
					.then().log().all().assertThat().statusCode(200);	//By the help of log().all() method we will able to see in console that what responses we are getting actually. 						
		}

	}


