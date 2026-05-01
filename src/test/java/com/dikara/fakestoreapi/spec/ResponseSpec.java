package com.dikara.fakestoreapi.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class ResponseSpec {

    public  static ResponseSpecification success200(){

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

    }




    public  static ResponseSpecification invalid404(){

        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();

    }

    public  static ResponseSpecification invalid405(){

        return new ResponseSpecBuilder()
                .expectStatusCode(405)
                .build();

    }


    public  static ResponseSpecification invalid400(){

        return new ResponseSpecBuilder()
                .expectStatusCode(400)
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

    public static ResponseSpecification getProductDetail() {


        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("id", notNullValue())
                .expectBody("title", notNullValue())
                .expectBody("price", notNullValue())
                .expectBody("description", notNullValue())
                .expectBody("category", notNullValue())
                .expectBody("image", notNullValue())
                .expectBody("rating", notNullValue())
                .expectBody("rating.rate",notNullValue())
                .expectBody("rating.count", notNullValue())
                .build();
    }
}
