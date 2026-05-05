package com.dikara.ecommerce.api;

import com.dikara.ecommerce.dto.login.LoginRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginAPI {

    private RequestSpecification request;

    public LoginAPI(RequestSpecification request){

        System.out.println("Masuk API, request: " + request);
        this.request = request;
    }

    public Response login(LoginRequest login){
        return request
                .body(login)
                .post("/auth/login");
    }


    public Response loginMissingField(){
        return request
                .body("{\"username\":\"mor_2314\"}")
                .post("/auth/login");
    }

    public Response loginWrongMethod(){

        return request
                .get("/auth/login");
    }


    public Response loginWithInvalidToken (){
        String expiredToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXIiOiJtb3JfMjMxNCIsImlhdCI6MTc3Nzk0OTU5Nn0.YOWLCVm8tJHkQo3HVB37tC2FgoLJpq1sUHhJIaWDMrk";
        return request
                .header("Authorization", "Bearer "+ expiredToken)
                .get("/users");


    }
}
