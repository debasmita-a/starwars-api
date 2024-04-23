package com.qa.starwars.tests;

import org.testng.annotations.Test;

import com.qa.starwars.client.RestClient;

public class GetPeopleAPI {

   public RestClient restClient;
	
	@Test
	public void getPeopleAPITest() {
		restClient = new RestClient();
		restClient.get("/people/", true)
		              .then().log().all()
		                  .assertThat()
		                      .statusCode(200);
		                          
	}
}
