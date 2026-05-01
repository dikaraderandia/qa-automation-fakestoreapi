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

//        {
//            "id": 1,
//                "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
//                "price": 109.95,
//                "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
//                "category": "men's clothing",
//                "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
//                "rating": {
//            "rate": 3.9,
//                    "count": 120
//        }
//        }
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
