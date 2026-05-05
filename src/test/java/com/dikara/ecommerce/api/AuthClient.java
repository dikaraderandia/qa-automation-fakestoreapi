package com.dikara.ecommerce.api;

import com.dikara.ecommerce.dto.login.LoginRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthClient {

    private RequestSpecification request;

    public AuthClient(RequestSpecification request){

        System.out.println("Masuk API, request: " + request);
        this.request = request;
    }

    public Response login(LoginRequest login){
        return request
                .body(login)
                .post("/auth/login");
    }

    public String getToken(LoginRequest login){
        return login(login)
                .then()
                .extract()
                .path("token");
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


    public Response loginWithToken (String token){

        return request
                .header("Authorization", "Bearer "+ token)
                .get("/users");


    }
}
