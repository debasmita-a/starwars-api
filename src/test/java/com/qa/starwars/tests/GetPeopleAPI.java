package com.qa.starwars.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.starwars.client.RestClient;
import com.qa.starwars.utils.StringUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetPeopleAPI {

   public RestClient restClient;
	
	@Test
	public void getPeopleAPITest() {
		restClient = new RestClient();
		restClient.get("/people/", true)
		              .then().log().all()
		                  .assertThat()
		                      .statusCode(200)
		                         .and()
		                            .body("count", equalTo(87))
		                               .and()
		                                   .body("results.name", hasItem("C-3PO"));
		                          
	}
	
	@Test
	public void getPoepleAPISchemaTest() {
		
	}
	
	@Test
	public void getPeopleAPIWith_ExtractBody() {
		restClient = new RestClient();
		Response response = restClient.get("/people/", true);
		JsonPath js = response.jsonPath();   // JSON array
		List<String> names = js.getList("results.name");
		System.out.println(names);
		                  
	}
	
	@Test
	public void getPeopleAPIWith_ExtractBody_extract() {
		restClient = new RestClient();
		int count = restClient.get("/people", true)
		             .then().log().all()
		                  .extract().path("count");
		Response response = restClient.get("/people", true)
        .then().log().all()
             .extract().response();
		response.asPrettyString();
		 System.out.println(count);                 
	}
	
	@Test
	public void getPeopleWithSingleID() {
		restClient = new RestClient();
		
		restClient.get("/people/1", true)
		              .then()
		                  .assertThat()
		                       .statusCode(200)
		                           .and()
		                              .body("name", equalTo("Luke Skywalker"))
		                                       .body("eye_color", equalTo("blue"));
		                              
	}
	
	
	@DataProvider
	public Object[][] filmFromPeopleID_01(){
		return new Object[][] {
			{"A New Hope"},
			{"The Empire Strikes Back"},
			{"Return of the Jedi"},
			{"The Force Awakens"},
			{"Revenge of the Sith"}			
		};
	}
	
	@Test
	public void getPeopleWithSingleID_with_extract() {
        restClient = new RestClient();
		
		Response response = restClient.get("/people/1", true)
		                                  .then()
		                                       .extract()
		                                           .response();
		JsonPath js = response.jsonPath();
		List<String> films = new ArrayList<String>();
		films = js.getList("films");
		
	}	
	

}
