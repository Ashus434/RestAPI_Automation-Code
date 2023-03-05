import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*; 
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticJSONPayloadSection7 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("F:\\Study\\eclipse-workspace\\Z_Browser Drivers\\addPlace.json"))))
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String PlaceID=js.getString("place_id");
		
		System.out.println(PlaceID);
	}

}
