package com.dikara.ecommerce.data;

import com.dikara.ecommerce.dto.login.LoginRequest;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "loginDataInput")
    public Object[][] loginDataInput(){
        return new Object[][]{
                {
                    new LoginRequest("mor_2314", "83r5^_"), 201

                },

                // password empty
                {
                        new LoginRequest("mor_2314", ""),400

                },
                // wrong password
                {
                        new LoginRequest("mor_2314", "sdfsdf"),401

                },
                //password null
                {
                        new LoginRequest("mor_2314", null),400

                },

                // username empty
                {
                        new LoginRequest("", "83r5^_"),400

                },
                // username null
                {
                        new LoginRequest(null, "83r5^_"),400

                },

                // username not existed/ username wrong
                {
                        new LoginRequest("not_existed", "83r5^_"),401

                },
        };
    }

    @org.testng.annotations.DataProvider(name = "loginDataSecurity")
    public Object[][] loginDataSecurity(){
        return new Object[][]{
                {
                   //brute force
                    new LoginRequest("not_existed", "83r5^_"), 1, 401
                },
                {
                    //sql injection
                    new LoginRequest("admin' OR '1'='1","83r5^_"),2, 401
                }
        };
    }




    @org.testng.annotations.DataProvider(name = "productData")
    public Object[][] productData(){
        return new Object[][]{
                {
                       "abcd"

                },

                {
                    "1"

                },

                {
                        "-1"

                },

                {
                        "9999"

                }
        };
    }

    @org.testng.annotations.DataProvider(name ="userData")
    public Object[] UsersData(){
        return new Object[][]{
                {"1", 200},{"9999", 200}, {"-1",400}, {"abcd",400}
        };
    }
}




