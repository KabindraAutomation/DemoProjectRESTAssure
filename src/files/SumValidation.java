package files;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	
	@Test
	public void sumofCourse()
	{
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		int sum = 0; 
		for (int i = 0; i < count; i++) {

		
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.get("courses["+i+"].copies");
			int amount = price * copies ;
			System.out.println(amount);
			 sum = sum+ amount ; 
			
		
		}
		 System.out.println("Total course price = "+ sum);
		 int purchaseAmout = js.getInt("dashboard.purchaseAmount");
			 
		 Assert.assertEquals(sum, purchaseAmout);
	
	
	}
}
