package com.dikara.fakestoreapi.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class ResponseSpec {

    public  static ResponseSpecification success200(){

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("id", notNullValue())
                .build();

    }


    public  static ResponseSpecification success201Login(){

        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectBody("token", notNullValue())
                .build();

    }


    public  static ResponseSpecification success201(){

        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectBody("token", notNullValue())
                .build();

    }

    public static ResponseSpecification userDetailSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("name", notNullValue())
                .expectBody("username", notNullValue())
                .expectBody("email", containsString("@"))
                .expectBody("address", notNullValue())
                .expectBody("phone", notNullValue())
                .expectBody("website", notNullValue())
                .expectBody("company", notNullValue())
                .build();
    }
}
