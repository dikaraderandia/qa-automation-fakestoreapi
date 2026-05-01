package com.dikara.fakestoreapi.api;

import com.dikara.fakestoreapi.dto.login.LoginRequest;
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
}
