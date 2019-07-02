package com.example.githubapi.model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    //@SerializedName -> menetapkan variable json yang di ambil dari json url
    //https://api.github.com/users/maulana2ahmad
    //https://api.github.com/users/{user}
    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("email")
    private String email;

    @SerializedName("blog")
    private String blog;

    public GitHubUser(String login,
                      String name,
                      String avatar,
                      String followers,
                      String following,
                      String email,
                      String blog)
    {
        this.setLogin(login);
        this.setName(name);
        this.setAvatar(avatar);
        this.setFollowers(followers);
        this.setFollowing(following);
        this.setEmail(email);
        this.setBlog(blog);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
