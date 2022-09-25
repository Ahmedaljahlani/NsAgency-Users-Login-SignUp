package com.example.yemensolutiontest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceHolder {

    @POST("users")
    public Call<Users> addUser(@Body Users users);

    @PUT("users/{id}")
    Call<Users> UpdateUser(@Path("id") int id , @Body Users user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);


    @PATCH("users/{id}")
    Call<Users> patchPost(@Path("id") int id , @Body Users user);
}
