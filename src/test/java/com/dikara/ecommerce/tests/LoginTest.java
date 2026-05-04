package com.dikara.ecommerce.tests;

import com.dikara.ecommerce.api.LoginAPI;
import com.dikara.ecommerce.data.DataProvider;
import com.dikara.ecommerce.dto.login.LoginRequest;
import com.dikara.ecommerce.spec.RequestSpec;
import com.dikara.ecommerce.spec.ResponseSpec;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class LoginTest extends RequestSpec {


    @Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
    public void loginTestDataDriven(LoginRequest user, int expectedStatus){
        LoginAPI loginAPI = new LoginAPI(getRequest());

        Response response = loginAPI.login(user);
        if (expectedStatus == 201){

            response.then()
                    .spec(ResponseSpec.success201Login());

        } else{

            response.then()
                    .statusCode(expectedStatus);
        }


    }


    @Test
    public void loginTest(){
        LoginAPI loginAPI = new LoginAPI(getRequest());
        LoginRequest loginRequest = new LoginRequest("mor_2314", "83r5^_");

        Response response = loginAPI.login(loginRequest);
        response.then()
                .spec(ResponseSpec.success201Login());
    }
}
