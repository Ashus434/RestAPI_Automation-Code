package section10_OAuth_2dot0;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.restassured.path.json.JsonPath;

public class Oauth_Authentication_AuthorizationCodeGrantType {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

	    /* Here we are automating the Oauth 2.0 Authentication through RestAPI */	
		
		
		System.setProperty("webdriver.edge.driver", "F:/Study/eclipse-workspace/Z_Browser Drivers/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		
		/*System.setProperty("webdriver.chrome.driver", "F:/Study/eclipse-workspace/Z_Browser Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver(); */
		
	  /*  driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
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
		
		  String CurrentURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdfIWIa-zP4C_cFXQyCjuPNwDlWwE-uqs99SAU0_tIKYEDKbSvWh3tZYC7Yqpdw0jw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
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
		
		
		
		//Hitting Redirect_url
		String Response = 
		given()
	   .queryParams("access_token", AccessToken )
	   .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(Response);
		
		/*Note:- Here we are using the end point url instead of resource, in get method, because here the end point/base url
		         is not common or universal for all scenarios. 
		         
		         & here we are taking the response directly from when method, because here the response assertion,
		         extractions are not required really. So this is the reason why we have not used then() method. we
		         are directly taking the raw response as string format. 
		*/
		
	}

}
