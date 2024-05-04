package com.qa.starwars.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.starwars.client.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetFilmsAPI {

	public RestClient restClient;
	
	@Test
	public void getFilmsAPITest() {
		restClient = new RestClient();
		
		restClient.get("/films", false)
		               .then()
		                   .assertThat()
		                       .statusCode(200)
		                             .and()
		                                 .body("count", equalTo(7))
		                                     .and()
		                                         .body("results", hasSize(7));
	}
	
	
	@DataProvider
	public Object[][] filmsTitle(){
		return new Object[][] {
			{"A New Hope"},
			{"The Empire Strikes Back"},
			{"Return of the Jedi"},
			{"The Force Awakens"},
			{"Revenge of the Sith"}			
		};
	}
	
	@Test(dataProvider = "filmsTitle")
	public void verifyFilmTitles(String title) {
		restClient = new RestClient();
		
		Response response = restClient.get("/films", false)
		              .then()
		                  .extract()
		                       .response();
		JsonPath js = response.jsonPath();
		List<String> titles = js.getList("results.title");
		//System.out.println(titles);
		Assert.assertTrue(titles.contains(title));
	}
	
	@Test
	public void getFilmDetailsMap() {
		restClient = new RestClient();
		
		JsonPath js = restClient.get("/films", false)
		               .then()
		                   .extract()
		                       .jsonPath();
	}

}
