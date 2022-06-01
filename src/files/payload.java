package files;

public class payload {
	
	public static String AddPlace() {
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"ConwayKabin CodingAcademy\",\r\n"
				+ "  \"phone_number\": \"(+91) 555 555 5555\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"JAVA\",\r\n"
				+ "    \"C#\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
		
	}
	public static String CoursePrice() {
		return "{\r\n"
				+ "    \"dashboard\": {\r\n"
				+ "	\"purchaseAmount\":910,\r\n"
				+ "	\"website\":\"rahulshettyacademy.com\"\r\n"
				+ "    },\r\n"
				+ "    \"courses\": [\r\n"
				+ "	{\r\n"
				+ "	    \"title\":\"Selenium Python\",\r\n"
				+ "	    \"price\":50,\r\n"
				+ "	    \"copies\":6\r\n"
				+ "	},\r\n"
				+ "	{\r\n"
				+ "	    \"title\":\"Cypress\",\r\n"
				+ "	    \"price\":40,\r\n"
				+ "	    \"copies\":4\r\n"
				+ "	},\r\n"
				+ "	{\r\n"
				+ "	    \"title\":\"RPA\",\r\n"
				+ "	    \"price\":45,\r\n"
				+ "	    \"copies\":10\r\n"
				+ "	}\r\n"
				+ "    ]\r\n"
				+ "}\r\n"
				+ "";
		
		
	}
	public static String AddBook() {
		
		
		String payload = "{\r\n"
				+ " \r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"bcde\",\r\n"
				+ "\"aisle\":\"22712\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		return payload;
	}

}
