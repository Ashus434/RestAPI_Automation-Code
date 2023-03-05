import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*; //This package we have to write manually because it's a static one & Eclipse won't suggest for given() method.
import static org.hamcrest.Matchers.*;//This package we have to write manually because it's a static one & Eclipse won't suggest it for equalTo() method.

import org.testng.Assert;
import BodyDetails.BodyDetails;
	
public class ValidatingRestAPIResponse {

		public static void main(String[] args) {
			// TODO Auto-generated method stub

			
			// validate if Add Place API is working as expected 
			//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
					
				//given - all input details 
				//when - Submit the API -resource, http method
				//Then - validate the response
					RestAssured.baseURI= "https://rahulshettyacademy.com";
					
					//Add Place
					String response =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
					.body(BodyDetails.AddBodyDetails())//Created a BodyDetails class & AddBodyDetails() method separately inside BodyDetails Package.
					.when().post("maps/api/place/add/json")
					.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
					.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
						
					System.out.println(response);
					
					JsonPath js=new JsonPath(response);//JsonPath is a class so it's object is helping us to convert the String (response) into Json.
					String PlaceID=js.getString("place_id");// the place_id Json address we are providing as String path format to getString() method to extract the place_id's String value.
					
					System.out.println(PlaceID);
					
					
					
					//Update Place
					String newAddressString="70 winter walk, USA";
					
					given().log().all().queryParam("key", "qaclick123").header("Content-Type","text/plain").body("{\r\n"
							+ "\"place_id\":\""+PlaceID+"\",\r\n"
							+ "\"address\":\""+newAddressString+"\",\r\n"
							+ "\"key\":\"qaclick123\"\r\n"
							+ "}").
					 when().put("maps/api/place/update/json")
					.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
					/* "body("msg", equalTo("Address successfully updated")" body we are using because it's related to RestAssured so here we are using body for comparison */
					/* but below Jsonpath is  java related thing so for comparison we have to use Assertion of testng instead of Body of RestAssured */
					
					
					//Get Place
					String getPlaceresponse =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",PlaceID )
					.when().get("maps/api/place/get/json")
					.then().assertThat().log().all().statusCode(200).extract().response().asString();
					
					System.out.println(getPlaceresponse);
					
					JsonPath js1=new JsonPath(getPlaceresponse);
					String actualAddress=js1.getString("address");
					
					System.out.println(actualAddress);
					
				Assert.assertEquals(actualAddress, newAddressString); //Read the Above Line
					
		}

	}


