package com.dikara.ecommerce.data;

import com.dikara.ecommerce.dto.login.LoginRequest;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
                {
                    new LoginRequest("mor_2314", "83r5^_"), 201

                },


                {
                        new LoginRequest("mor_2314", ""),400

                },

                {
                        new LoginRequest("mor_2314", "sdfsdf"),401

                },

                {
                        new LoginRequest("mor_2314", null),400

                },
                {
                        new LoginRequest("", "83r5^_"),400

                },
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
    public Object[] UserData(){
        return new Object[][]{
                {"1", 200},{"9999", 200}, {"-1",400}, {"abcd",400}
        };
    }



}
