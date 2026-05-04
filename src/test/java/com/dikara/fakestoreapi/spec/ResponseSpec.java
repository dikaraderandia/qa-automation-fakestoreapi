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


    public static ResponseSpecification getUserDetail() {

//        {
//            "address": {
//            "geolocation": {
//                "lat": "-37.3159",
//                        "long": "81.1496"
//            },
//            "city": "kilcoole",
//                    "street": "new road",
//                    "number": 7682,
//                    "zipcode": "12926-3874"
//        },
//            "id": 1,
//                "email": "john@gmail.com",
//                "username": "johnd",
//                "password": "m38rmF$",
//                "name": {
//            "firstname": "john",
//                    "lastname": "doe"
//        },
//            "phone": "1-570-236-7033",
//                "__v": 0
//        }
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("id", notNullValue())
                .expectBody("email", notNullValue())
                .expectBody("username", notNullValue())
                .expectBody("address", notNullValue())
                .expectBody("password", notNullValue())
                .expectBody("phone", notNullValue())
                .build();


    }
}
