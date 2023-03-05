import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import BodyDetails.payload;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath; 

public class DynamicJsonSection7
{

	@Test
	public void AddBook() {
		
	
	RestAssured.baseURI="http://216.10.245.166";
	String res= given().log().all().header("Content-Type", "application/json").body(payload.AddBook("bobsb", "2916933457"))//passing the parameter to payload.AddBook method. So whenever you will run just edit the parameter then run. so new value will be add. 
	.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	 JsonPath js=new JsonPath(res); 
	 String id = js.getString("ID");
	 System.out.println(id);
	
	
	}
	
	
	public void DeleteBook(String id) {
		
		
		RestAssured.baseURI="http://216.10.245.166";
		String res1= given().log().all().header("Content-Type", "application/json").body(payload.DeleteBook(id))
		.when().post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		 JsonPath js1=new JsonPath(res1); 
		 String MSG = js1.getString("msg");
		 System.out.println(MSG);
		 
		}
	
}

