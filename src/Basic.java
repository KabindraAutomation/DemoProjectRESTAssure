import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethod;
import files.payload;

public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Validate if Add place API is working as expected
		
		// given--> all input details
		//when--> submit the API  - resource, http method
		//then --> Validate the response
		
		// Add Place --> Update place with New Address --> Get Place to validate if New address is present in responses
		RestAssured.baseURI = "https://rahulshettyacademy.com";
			String response = 	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
				
			System.out.println(response);
			// To prase the value and store in one variable:
			JsonPath js = new JsonPath(response); //for parsing json
			String PlaceID = js.getString("place_id");
			System.out.println("Place id value = " + PlaceID);
				
			//Update place with New Address
			String newAddress = "3033 Conway Arkansas, USA";
			
			given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+PlaceID+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200).body( "msg", equalTo("Address successfully updated"));
			
			//GEt Place:
			String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id", PlaceID)
			.when().get("/maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
			

			JsonPath js1 = ReUsableMethod.rawToJson(getPlaceResponse);
			//JsonPath js1 = new JsonPath(getPlaceResponse);
			String actualAddress = js1.getString("address");
			System.out.println(actualAddress);
			Assert.assertEquals(actualAddress, newAddress);
			
					
			
	}

}
