package com.dikara.ecommerce.tests;

import com.dikara.ecommerce.api.LoginAPI;
import com.dikara.ecommerce.data.DataProvider;
import com.dikara.ecommerce.dto.login.LoginRequest;
import com.dikara.ecommerce.spec.RequestSpec;
import com.dikara.ecommerce.spec.ResponseSpec;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class LoginTest extends RequestSpec {


    @Test(dataProvider = "loginDataInput", dataProviderClass = DataProvider.class)
    public void loginInputTestDataDriven(LoginRequest user, int expectedStatus){
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

    @Test(dataProvider = "loginDataSecurity", dataProviderClass = DataProvider.class)
    public void loginSecurityTestingDataDriven (LoginRequest login, int type, int expectedStatusResp){
        LoginAPI loginAPI = new LoginAPI(getRequest());

        if (type == 2){
            //sql injection
            Response response = loginAPI.login(login);
            response.then()
                    .statusCode(expectedStatusResp);


        }else{
            // brute force
            for (int i = 0 ; i<=5 ; i++){
                System.out.println("brute force: "+ i);
                Response response = loginAPI.login(login);
                response.then()
                        .statusCode(expectedStatusResp);
            }

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

    @Test
    public void loginTestWrongMethode(){
        LoginAPI loginAPI = new LoginAPI(getRequest());

        Response response = loginAPI.loginWrongMethod();
        response.then()
                .spec(ResponseSpec.invalid404());
    }


    @Test
    public void loginMissingFields(){
        LoginAPI loginAPI = new LoginAPI(getRequest());

        Response response = loginAPI.loginMissingField();
        response.then()
                .spec(ResponseSpec.invalid400());
    }


    @Test
    public void loginResponseTime(){
        LoginAPI loginAPI = new LoginAPI(getRequest());
        LoginRequest loginRequest = new LoginRequest("mor_2314", "83r5^_");

        Response response = loginAPI.login(loginRequest);
        response.then()
                .time(lessThan(2000L));;
    }

    @Test
    public void invalidToken(){
        LoginAPI loginAPI = new LoginAPI(getRequest());
        Response res = loginAPI.loginWithInvalidToken();
        res.then().statusCode(401);
    }
}
