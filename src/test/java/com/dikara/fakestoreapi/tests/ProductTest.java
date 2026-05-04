package com.dikara.fakestoreapi.tests;

import com.dikara.fakestoreapi.api.ProductAPI;
import com.dikara.fakestoreapi.data.DataProvider;
import com.dikara.fakestoreapi.spec.RequestSpec;
import com.dikara.fakestoreapi.spec.ResponseSpec;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertNotNull;
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
                .body(matchesJsonSchemaInClasspath("schema/product-detail-schema.json"))
                .log().all();;

        assertNotNull(response.jsonPath().get("price"));
        assertTrue(response.jsonPath().getFloat("price" )>0);

        assertTrue(response.jsonPath().getFloat("rating.rate" )>0);

    }

    @Test(dataProvider = "productData", dataProviderClass = DataProvider.class)
    public void getProductDetailDataDriven (String id){

        ProductAPI productAPI = new ProductAPI(getRequest());
        Response response= productAPI.getProductDetail(id);

        if (id.equals("1") || id.equals("9999")){
            if (id.equals("1")){
                response.then()
                        .spec(ResponseSpec.getProductDetail())
                        .body(matchesJsonSchemaInClasspath("schema/product-detail-schema.json"));

            }else{
                response.then()
                        .spec(ResponseSpec.success200());

            }

            System.out.println("id: "+ id+", statusCode: 200");

        }else {
            System.out.println("id: "+ id+", statusCode: 400");
            response.then()
                    .spec(ResponseSpec.invalid400());
        }



    }



}
