package com.dawn.microservice.product_service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

   @ServiceConnection
   static MySQL mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

   @LocalServerPort
   private Integer port;

   @BeforeEach
   void setup() {
	RestAssured.baseURI = "http://localhost";
	RestAssured.port = port;

   }

   static {
	mongoDBContainer.start();
   }

   @Test
   void contextLoads() {
	String requestBody = """
		{
		"name": "iPhone 15",
		"description":"iPhone 15 is a smart phone from Apple",
		"price": 1000
		}
		""";

	RestAssured.given().contentType("application/json").body(requestBody).when().post("/api/product").then()
		.statusCode(201).body("id", Matchers.notNullValue()).body("name", Matchers.equalTo("iPhone 15"))
		.body("description", Matchers.equalTo("iPhone 15 is smartphone from Apple"))
		.body("price", Matchers.equalTo(1000));
   }

}
