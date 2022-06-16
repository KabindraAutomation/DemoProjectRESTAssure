package OAuthLearn;
import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;


public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		
		//How to get access token

		System.setProperty("webdriver.chrome.driver", "C://AutomationDriver//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type = 'email']")).sendKeys("ksmaharjan");
		driver.findElement(By.cssSelector("input[type = 'email']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input[type = 'password']")).sendKeys("Kalu");
		driver.findElement(By.cssSelector("input[type = 'password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url = driver.getCurrentUrl();
		String partialcode = url.split("code=4")[1];
		String code = partialcode.split("&scope")[0];
		
		
		
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
			.queryParams("code", code)
			.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.queryParams("client_secret", "\r\n"
					+ "erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type", "authorization_code")
		.when().log().all()
			.post("https://accounts.google.com/o/oauth2/v4/token").asString();
		JsonPath js = new JsonPath(accessTokenResponse);
			String accessToken =js.getString("access_token");

		
		
		
		String response = given()
			.queryParam("access_token", accessToken)
		.when().log().all()
			.get("\r\n"
					+ "https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(response);
	

	}

}
