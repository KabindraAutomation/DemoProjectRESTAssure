package files;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="http://localhost:8080";
		//Login Scenario:
		
		SessionFilter session = new SessionFilter();
		
		
		String response = given().relaxedHTTPSValidation().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"username\": \"ksmasharjan17\", \r\n"
				+ "    \"password\": \"Kale.Dai1122\" \r\n"
				+ "}\r\n"
				+ "").log().all().filter(session).when().post("/rest/auth/1/session")
		.then().extract().response().asString();
		
		
		String expectedMessage = "Hi there how's going on";
		
		//Add Comment
		String addCommentResponse = given().pathParam("id", "10002").log().all().header("content-type","application/json").body("{\r\n"
				+ "    \"body\": \""+expectedMessage+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filters(session).when().post("rest/api/2/issue/{id}/comment")
		.then()
			.log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(addCommentResponse);
		String CommentId = js.getString("id");
		
		//Add Attachment
		given()
			.header("X-Atlassian-Token", "no-check").filter(session).pathParam("id","10002")
			.header("Content-Type","multipart/form-data")
			.multiPart("file",new File("C:\\eclipse-workspace\\DemoProject\\src\\files\\jira.txt"))
		.when()
			.post("/rest/api/2/issue/{id}/attachments")
		.then()
			.log().all().assertThat().statusCode(200);
		
		//Get Issues:
		String IssueDetail = given()
			.filter(session).pathParam("id", "10002").queryParam("fields", "comment").log().all()
		.when()
			.get("/rest/api/2/issue/{id}")
		.then()
			.log().all().extract().response().asString();
		System.out.println(IssueDetail);
		
		JsonPath js1 = new JsonPath(IssueDetail);
		int commentCount =  js1.getInt("fields.comment.comments.size()");
		for(int i=0;i<commentCount; i++)
		{
			String CommentIdissue = js1.get("fields.comment.comments["+i+"].id").toString();
			if(CommentIdissue.equals(CommentId))
			{
				String bodyMessage = js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(bodyMessage);
				Assert.assertEquals(bodyMessage, expectedMessage);
			}
		}

	}

}
