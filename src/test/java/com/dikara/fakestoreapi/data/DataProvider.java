package com.dikara.fakestoreapi.data;

import com.dikara.fakestoreapi.dto.login.LoginRequest;

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



}
