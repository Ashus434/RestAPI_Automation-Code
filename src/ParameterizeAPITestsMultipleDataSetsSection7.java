import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BodyDetails.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ParameterizeAPITestsMultipleDataSetsSection7 {

	@Test(dataProvider = "BookData" )//Here we are telling to @Test annotation about BookData data provider. So it will go there and look for the data for his Add Book methods arguments
	public void AddBook(String isbn, String aisle) 
	{
		
	RestAssured.baseURI="http://216.10.245.166";
	String res= given().header("Content-Type", "application/json").body(payload.AddBook(isbn, aisle))//passing the parameter to payload.AddBook method. So whenever you will run just edit the parameter then run. so new value will be add. 
	.when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
	
	 JsonPath js=new JsonPath(res); 
	 String id = js.getString("ID");
	 System.out.println(id);
	
	}
	
	
	@DataProvider(name="BookData")//By the help of data provider annotation we can parameterize the @Test functions/methods. Here our data Provider name we declared as "BookData"
	public Object[][] getdata() 
	{
		//multidimensional Array= Collection of Arrays
		return new Object[][] {{"gaka","mdsa"},{"asgha","urha"},{"ywha","rtba"}};//here we are creating an object of multidimensional array & Initializing & returning the object to getdata method.
	}
	//Due to 3 number of arrays the @test code will run 3 times
}
