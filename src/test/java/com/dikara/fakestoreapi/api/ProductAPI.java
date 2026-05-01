package com.dikara.fakestoreapi.api;

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
}
