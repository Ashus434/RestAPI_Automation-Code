package Section11_SerializationAndDeserialization;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class EndPoint {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

	    /* Here we are doing the deSerialization at last */	
		
		System.setProperty("webdriver.edge.driver", "F:/Study/eclipse-workspace/Z_Browser Drivers/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		/*System.setProperty("webdriver.chrome.driver", "F:/Study/eclipse-workspace/Z_Browser Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver(); */
	    /*driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[id='identifierId']")).sendKeys("ashutosh.kar.130@gmail.com");
		driver.findElement(By.cssSelector("button[class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 qIypjc TrZEUc lw1w4b'] span[class='VfPpkd-vQzf8d']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("Rahulshetty123");
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("button[class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 qIypjc TrZEUc lw1w4b'] span[class='VfPpkd-vQzf8d']")).click();
		String CurrentURL =driver.getCurrentUrl(); 
		*/
		
		/* As Google is not giving permission to sign-in through automation script. So we are commenting the above codes
		   and doing the Sign-in Manually and after successful sign-in we are taking the link from browser and pasting
		   it in our code. then splitting it and getting the Authorization code.
		   Note:- Every time we have to clear the cookies and cache from the browser, and then have to do the sign-in
		   manually.
		*/
		  String CurrentURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdcSgZw8kXzlbWSnLGTNctvPqiEMkjm36MMryvZCcbl5kA7oIv6vdEdHBzVmDC_oIw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none";
		  String SplitedURL = CurrentURL.split("code=")[1];
		  String Code = SplitedURL.split("&scope")[0];
		  System.out.print(Code);
		
		
		 //Hitting Access Token URL
		 String Access_Token =
		 given().urlEncodingEnabled(false)
		.queryParams("code", Code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com" )
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W" )
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js= new JsonPath(Access_Token);
		String AccessToken = js.getString("access_token");
		System.out.println(AccessToken);
		
		
		
		
		//Hitting Redirect_url & doing deSerialization 
		PojoClassesForDeserialization gc = 
		given()
	   .queryParams("access_token", AccessToken ).expect().defaultParser(Parser.JSON)
	   .when().get("https://rahulshettyacademy.com/getCourse.php").as(PojoClassesForDeserialization.class);
		
		/* The "PojoClassesForDeserialization" is the parent POJO Class and "PojoClassesForDeserialization.class"
		   is an object of  "PojoClassesForDeserialization" class. So when we actually get the response (from this ".when().get("https://rahulshettyacademy.com/getCourse.php"))
		   we are basically converting the whole response into the Java Object of PojoClassesForDeserialization class .So the return type will be
		   "PojoClassesForDeserialization" java object.
		   
		   ".as(PojoClassesForDeserialization.class)"- Means as Java object
		   
		    To RestAssured We have to explicitly tell that what format response we are sending to our POJO classes. 
		    So explicitly we have to tell to RestAssured, what format response we are expecting. So based upon that
		    response this ".as()" method will help us to convert that into Java Object.
		    So if we will use this .defaultParser(Parser.JSON) method so automatically whatever response we will get,
		    RestAssured Treats that as a default  Json Parser. 
		    
		    If our content type is JSON then we don't need to use this ".expect().defaultParser(Parser.JSON)"
		 */
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
		//Print "SoapUI Webservices testing"'s Price of API
		  List<api> apiCourses = gc.getCourses().getApi();
		  
		  for(int i=0; i<apiCourses.size(); i++ ) {
			  
			 if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				 System.out.println(apiCourses.get(i).getPrice());
		    }
		  }

		  
		  //Print all the course title of webAutomation.
		  List<webAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		
		  for(int j=0; j<webAutomationCourses.size(); j++ )
		  {
			 System.out.println(webAutomationCourses.get(j).getCourseTitle());
		  }
	}
}
