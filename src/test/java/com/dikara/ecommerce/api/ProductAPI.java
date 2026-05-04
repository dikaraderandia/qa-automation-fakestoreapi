package com.dikara.ecommerce.api;

import com.dikara.ecommerce.spec.RequestSpec;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
