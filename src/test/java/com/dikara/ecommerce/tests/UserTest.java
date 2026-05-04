package com.dikara.ecommerce.tests;

import com.dikara.ecommerce.api.UserAPI;
import com.dikara.ecommerce.data.DataProvider;
import com.dikara.ecommerce.spec.RequestSpec;
import com.dikara.ecommerce.spec.ResponseSpec;
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
