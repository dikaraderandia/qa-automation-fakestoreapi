package com.dikara.fakestoreapi.api;

import com.dikara.fakestoreapi.dto.login.LoginRequest;
import com.dikara.fakestoreapi.spec.RequestSpec;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class ProductAPI extends RequestSpec {

    private RequestSpecification request;
    public ProductAPI (RequestSpecification request){
        this.request = request;
    }


    public Response getProducts(){

        return request
                .get("/products");
    }


    public Response getProductsInvalid(){

        return request
                .get("/products");
    }

    public Response getProductsWrongMethod( ){
        return request
                .post("/products");
    }

    public Response getProductDetail(String id){
        return request
                .get("/products/" + id);
    }
}
