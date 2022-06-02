package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider = "BookData")
	
	public void addBook(String aisle, String isbn) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.AddBook(aisle, isbn)).when().post("/Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReUsableMethod.rawToJson(response);
		String id = js.get("id");
		System.out.println(id);

	}
	// delete book scenario also --> for next time we will not get duplicate error
	// message:
	@DataProvider(name = "BookData")
	public Object[][] getData() {
		//Array = collection of elements
		//Multidimensional array
		return new Object[][] {{"adf", "3344"}, {"sedfs","3234"}, {"serfwe","343"}};
		
	}

}
