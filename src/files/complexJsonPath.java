package files;

import io.restassured.path.json.JsonPath;

public class complexJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());
		// TC: 1= Print No of Course returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		// TC: 2 : Print PurchaseAmount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// TC:3 : Print Title of the first course
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);

		// print all course titles and there respective prices
		for (int i = 0; i < count; i++) {

			String courseTitle = js.get("courses[" + i + "].title");
			System.out.println(courseTitle);
			System.out.println(js.getInt("courses[" + i + "].price"));

		}
		// Print no. of copies sold by RPA
		System.out.println(" Print no. of copies sold by RPA");

for (int i= 0; i<count; i++) {
			
			String courseTitle = js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")){
			//copies sold
			int copies = js.get("courses["+i+"].copies");	
				System.out.println(copies);
				break;
			}
		
		}
	//
		
	}

}
