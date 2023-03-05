import org.testng.Assert;

import BodyDetails.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* In this section you will need the JSON Online Editor. So open that in browser */
		/* Also open the Json document and put that Json in payload-->CoursePrice() method*/
		
	
		 //1. Print No of courses returned by API
		
		 JsonPath js=new JsonPath(payload.CoursePrice()); 
		 int count = js.getInt("courses.size()");
		 System.out.println(count);
		
			
		 //2. Print Purchase Amount
		 int Totalamount = js.getInt("dashboard.purchaseAmount");
		 System.out.println(Totalamount);
		 
		 
		 
		 //3. Print Title of the first course
		 String title = js.getString("courses[0].title"); //in Json square bracket [] means Array. So course is an array & title is present for all 3 indexes so we are taking the 0th one.
		 System.out.println(title);
		 
		 
		 //4. Print All course titles and their respective Prices
		 for(int i=0; i<count;i++) {
			 String Coursestitle = js.getString("courses["+i+"].title");
			 int Coursesprice = js.getInt("courses["+i+"].price");//With this technique we can insert a variable inside string
			                 
			   System.out.println(Coursestitle);
			 
			   System.out.println(Coursesprice);
			                   /*or
			   System.out.println(js.getInt("courses["+i+"].price"));
			                   //or
			   System.out.println(js.get("courses["+i+"].price").toString());
			   */
		    }  
			
		   
         // 5. Print no of copies sold by RPA Course
		 
		 System.out.println("No of copies sold by RPA Course" );
		    for(int J=0; J<count;J++) 
		    {
		    String ParticularCoursestitle = js.getString("courses["+J+"].title"); 
		    if(ParticularCoursestitle.equalsIgnoreCase("RPA"))
		    {
		    	 System.out.println(js.getString("courses["+J+"].copies").toString());
		    	 break;
		    }
		    }
		    
		    
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
