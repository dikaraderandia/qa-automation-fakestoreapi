package com.dikara.ecommerce.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserAPI {

    private RequestSpecification requestSpecification;

    public UserAPI (RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;

    }

    public Response getUsers(){
        return requestSpecification.get("users");
    }


    public Response getUserDetail(String id){
        return requestSpecification
                .get("/users/" + id);
    }
}
