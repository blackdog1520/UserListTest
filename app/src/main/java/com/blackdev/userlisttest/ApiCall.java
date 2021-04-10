package com.blackdev.userlisttest;

import retrofit.RestAdapter;

public class ApiCall {
    public static ApiInterface getClient() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://reqres.in/api")
                .build();
        ApiInterface api = adapter.create(ApiInterface.class);
        return api;
    }
}
