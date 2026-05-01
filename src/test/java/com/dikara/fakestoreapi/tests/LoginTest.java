package com.dikara.fakestoreapi.tests;

import com.dikara.fakestoreapi.api.LoginAPI;
import com.dikara.fakestoreapi.data.LoginDataProvider;
import com.dikara.fakestoreapi.dto.login.LoginRequest;
import com.dikara.fakestoreapi.spec.RequestSpec;
import com.dikara.fakestoreapi.spec.ResponseSpec;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class LoginTest extends RequestSpec {


    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
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
