package com.example.githubapi.rest;

import com.example.githubapi.model.GitHubRepos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubReposEndPoint {

    //"user_repositories_url": "https://api.github.com/users/{user}/repos{?type,page,per_page,sort}",
    //"https://api.github.com/users/{user}/repos"
    //"https://api.github.com/users/maulana2ahmad/repos"

    @GET("/users/{user}/repos")
    Call<List<GitHubRepos>> getRepos (@Path("user") String name);
}