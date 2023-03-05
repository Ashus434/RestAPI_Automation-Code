import org.testng.Assert;
import org.testng.annotations.Test;

import BodyDetails.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParseV2 {
	
	//Install the "Testng for Eclipse" Plug-in from Eclipse Market place.
	
	@Test  //Instead of Public Static main Method we are using here "Test" annotation & here we are writing our test related thing.
	
	public void SumoffCourse() {
		
		 //1. Print No of courses returned by API
		 JsonPath js=new JsonPath(payload.CoursePrice()); 
		 int count = js.getInt("courses.size()");
		 System.out.println(count);
		 
		 //2. Print Purchase Amount
		 int Totalamount = js.getInt("dashboard.purchaseAmount");
		 System.out.println(Totalamount);
		 
		   // 6. Verify if Sum of all Course prices matches with Purchase Amount
		    int sum = 0;
		    for(int l=0; l<count;l++) 
		    {
		    int CoursesPrice = js.getInt("courses["+l+"].price"); 
		    int CoursesCopies = js.getInt("courses["+l+"].copies");
		    
		    int amount = CoursesPrice * CoursesCopies;
		    System.out.println(amount);
		    sum = sum + amount;
		    
		 }
		  System.out.println(sum);
		  
		  Assert.assertEquals(sum, Totalamount);
		  
		 
	}

}
