package section12_Serialization;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RestAssured.baseURI= "https://rahulshettyacademy.com"; 
		
		AddPlace_ParentPOJOClass Ap = new AddPlace_ParentPOJOClass();
		Ap.setAccuracy(50);
		Ap.setAddress("29, side layout, cohen 09");
		Ap.setLanguage("French-IN");
		Location L = new Location();
		L.setLat(-38.383494);
		L.setLng(33.427362);
		Ap.setLocation(L);
		Ap.setName("Frontline house");
		Ap.setPhone_number("(+91) 983 893 3937");
		ArrayList<String> typelist = new ArrayList<String>();
		typelist.add("shoe park");
		typelist.add("shop");
		Ap.setTypes(typelist);
		Ap.setWebsite("http://google.com");
		
		/* By The help of RequestSpecBuilder & ResponseSpecBuilder we will be able to 
		 * set common headers, query parameters, statusCode
		 * base URI, etc. We observed that these things are used often.
		 */
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		//Here we built the Request Spec Builder & the return type is RequestSpecification
		
		ResponseSpecification Res= new ResponseSpecBuilder().expectStatusCode(200)
		.expectContentType(ContentType.JSON).build();
		//Here we built the Response Spec Builder & the return type is ResponseSpecification
				
		
		//String res = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		/*Earlier we were using the given request like above line, but after RequestSpecBuilder build
		  we are directly using like this "given().spec(req)"  */
		
		/* String request = given().spec(req)
		.body(Ap) */
		
		RequestSpecification request = given().spec(req)
		.body(Ap);
		
		/*Here we broke the given statement from when() and then() by a semi column(;) and 
		and made that as a Request and that request variable(request) we are using for 
		when() & then() statement and storing the response in a (response) variable */
		
		/* .when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response(); */
		/*Earlier we were using the when and then statement with given (like above line), but after ResponseSpecBuilder build
		  we are directly using below like this */
		
		Response response = request.when().post("maps/api/place/add/json")
		.then().spec(Res).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);
	}
}
