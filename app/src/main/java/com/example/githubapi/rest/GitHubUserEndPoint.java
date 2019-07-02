package com.example.githubapi.rest;

import com.example.githubapi.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoint {

    //mendapatkan parameter dari url /users/{user}
    //https://api.github.com/users/{user}
    @GET("/users/{user}")
    //memanggil data dari set get yang sudah di set pada model
    Call<GitHubUser> getUser(@Path("user") String user);
}
