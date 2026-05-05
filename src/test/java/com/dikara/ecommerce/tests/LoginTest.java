package com.dikara.ecommerce.tests;

import com.dikara.ecommerce.api.AuthClient;
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
        AuthClient authClient = new AuthClient(getRequest());

        Response response = authClient.login(user);
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
        AuthClient authClient = new AuthClient(getRequest());

        if (type == 2){
            //sql injection
            Response response = authClient.login(login);
            response.then()
                    .statusCode(expectedStatusResp);


        }else{
            // brute force
            for (int i = 0 ; i<=5 ; i++){
                System.out.println("brute force: "+ i);
//                Response response = loginAPI.login(login);
//                response.then()
//                        .statusCode(expectedStatusResp);

                authClient.login(login);
            }

            Response lockedResponse = authClient.login(login);

            lockedResponse.then().statusCode(429);

        }
    }


    @Test
    public void shouldLoginSuccessfullyWithValidCredential(){
        AuthClient authClient = new AuthClient(getRequest());
        LoginRequest loginRequest = new LoginRequest("mor_2314", "83r5^_");

        Response response = authClient.login(loginRequest);
        response.then()
                .spec(ResponseSpec.success201Login());
    }

    @Test
    public void shouldLoginAndAccessUserProfile(){
        AuthClient auth = new AuthClient(getRequest());

        String token = auth.getToken(new LoginRequest("mor_2314", "83r5^_"));

        Response res = auth.loginWithToken(token);
        res.then().statusCode(200);
    }

    @Test
    public void loginTestWrongMethode(){
        AuthClient authClient = new AuthClient(getRequest());

        Response response = authClient.loginWrongMethod();
        response.then()
                .spec(ResponseSpec.invalid404());
    }


    @Test
    public void shouldReturn400WhenMissingFields(){
        AuthClient authClient = new AuthClient(getRequest());

        Response response = authClient.loginMissingField();
        response.then()
                .spec(ResponseSpec.invalid400());
    }


    @Test
    public void loginResponseTime(){
        AuthClient authClient = new AuthClient(getRequest());
        LoginRequest loginRequest = new LoginRequest("mor_2314", "83r5^_");

        Response response = authClient.login(loginRequest);
        response.then()
                .time(lessThan(1000L));;
    }

    @Test
    public void invalidToken(){
        AuthClient authClient = new AuthClient(getRequest());
        Response res = authClient.loginWithInvalidToken();
        res.then().statusCode(401);
    }
}
