package com.blackdev.userlisttest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiInterface {
    @GET("/users?page=2")
    public void getUserList(Callback<Example> callback);
}
