package com.qa.starwars.client;

import java.util.Map;

import com.qa.starwars.exceptions.FrameworkExceptions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static final String BASE_URI = "https://swapi.py4e.com/api";
	private static final String BEARER_TOKEN = "";
	private static RequestSpecBuilder specBuilder;
	
	static {
		specBuilder = new RequestSpecBuilder();
	}
	
	public void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);
	}
	
	private void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;

		default:
			System.out.println("Please provide a correct content type.");
			throw new FrameworkExceptions("INVALIDCONTENTTYPE");	
		}
	}
	
	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URI);
		//addAuthorizationHeader();  
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Map<String, String> headers) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();  
		if(headers != null) {
			specBuilder.addHeaders(headers);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Map<String, String> headers, Map<String, String> queryParams) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();  
		if(queryParams != null) {
			specBuilder.addQueryParams(queryParams);
		}
		if(headers != null) {
			specBuilder.addHeaders(headers);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();  
		setRequestContentType(contentType);
		if(requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headers) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();  
		setRequestContentType(contentType);
		if(requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		if(headers != null) {
			specBuilder.addHeaders(headers);
		}
		return specBuilder.build();
	}
	
	// Http Utils :: GET
	
	public Response get(String serviceUrl, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
                           .when().log().all()
                               .get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec())
		              .when()
		                 .get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String, String> headers, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headers)).log().all()
                           .when().log().all()
                               .get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headers))
		              .when()
		                 .get(serviceUrl);
	}
	
	public Response get(String serviceUrl, Map<String, String> headers, Map<String, String> queryParams, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headers, queryParams)).log().all()
                           .when().log().all()
                               .get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headers, queryParams))
		              .when()
		                 .get(serviceUrl);
	}
	
	// POST
	
	public Response post(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
					                .when().log().all()
					                    .post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType))
                .when()
                    .post(serviceUrl);
	}
	
	public Response post(String serviceUrl, String contentType, Map<String, String> headers, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headers)).log().all()
					                .when().log().all()
					                    .post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headers))
                .when()
                    .post(serviceUrl);
	}
	
	//PUT
	
	public Response put(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
					                .when().log().all()
					                    .put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType))
                .when()
                    .put(serviceUrl);
	}
	
	public Response put(String serviceUrl, String contentType, Map<String, String> headers, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headers)).log().all()
					                .when().log().all()
					                    .put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headers))
                .when()
                    .put(serviceUrl);
	}
	
	// PATCH
	
	public Response patch(String serviceUrl, String contentType, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
					                .when().log().all()
					                    .patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType))
                .when()
                    .patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl, String contentType, Map<String, String> headers, Object requestBody, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headers)).log().all()
					                .when().log().all()
					                    .patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headers))
                .when()
                    .patch(serviceUrl);
	}
	
	// DELETE 
	
	public Response delete(String serviceUrl, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
					              .when().log().all()
					                    .delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec())
	              .when()
	                    .delete(serviceUrl);
	}
	
}
