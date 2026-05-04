package com.dikara.fakestoreapi.tests;

import com.dikara.fakestoreapi.api.UserAPI;
import com.dikara.fakestoreapi.data.DataProvider;
import com.dikara.fakestoreapi.spec.RequestSpec;
import com.dikara.fakestoreapi.spec.ResponseSpec;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;

public class UserTest extends RequestSpec {

    @Test
    public void getUsers(){
        UserAPI userAPI = new UserAPI(getRequest());

        Response response = userAPI.getUsers();

        response.then().spec(ResponseSpec.success200())
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("schema/users-schema.json"));

    }

    @Test
    public void getUserDetail(){
        UserAPI userAPI = new UserAPI(getRequest());
        Response response = userAPI.getUserDetail("1");
        response.then().spec(ResponseSpec.getUserDetail())
                .body(matchesJsonSchemaInClasspath("schema/users-detail-schema.json"));
    }

    @Test(dataProvider = "userData", dataProviderClass = DataProvider.class)
    public void getUserDetailDataDriven(String id, int expectedStatus){
        UserAPI userAPI = new UserAPI(getRequest());
        Response response = userAPI.getUserDetail(id);

        response.then().statusCode(expectedStatus);



    }
}
