package com.dikara.fakestoreapi.tests;

import com.dikara.fakestoreapi.api.ProductAPI;
import com.dikara.fakestoreapi.spec.RequestSpec;
import com.dikara.fakestoreapi.spec.ResponseSpec;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertTrue;

public class ProductTest extends RequestSpec {

    @Test
    public void getProducts(){
        ProductAPI productAPI = new ProductAPI(getRequest());
        Response response = productAPI.getProducts();

        response.then()
                .spec(ResponseSpec.success200());

        assertTrue(response.jsonPath().getList("$").size() >0);
    }

    @Test
    public void getProductsInvalidPath(){
        ProductAPI productAPI = new ProductAPI(getRequest());
        Response response= productAPI.getProductsInvalid();
        response.then()
                .spec(ResponseSpec.invalid404());
    }

    @Test
    public void getProductsWrongMethod(){
        ProductAPI productAPI = new ProductAPI(getRequest());
        Response response= productAPI.getProductsWrongMethod();
        response.then()
                .spec(ResponseSpec.invalid405());
    }


    @Test
    public void getProductDetail(){
        ProductAPI productAPI = new ProductAPI(getRequest());
        Response response= productAPI.getProductDetail("1");
        response.then()
                .spec(ResponseSpec.getProductDetail())
                .body(matchesJsonSchemaInClasspath("schema/product-detail-schema.json"));
    }



}
