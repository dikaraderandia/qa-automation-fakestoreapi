package com.dikara.fakestoreapi.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpec {
    String baseUrl = "https://fakestoreapi.com";
    protected RequestSpecification getRequest() {
        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(baseUrl)
                        .setContentType("application/json")
                        .build());
    }
}
