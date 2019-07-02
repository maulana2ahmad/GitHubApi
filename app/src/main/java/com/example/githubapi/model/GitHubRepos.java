package com.example.githubapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class GitHubRepos {

    //variavle @SerializedName didapat dari https://api.github.com/users/maulana2ahmad/repos
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    public GitHubRepos(
            String language,
            String description,
            String name) {

        this.setLanguage(language);
        this.setDescription(description);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
