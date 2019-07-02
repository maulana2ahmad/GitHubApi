package com.example.githubapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubapi.R;
import com.example.githubapi.model.GitHubUser;
import com.example.githubapi.rest.GitHubUserEndPoint;
import com.example.githubapi.rest.client.APIClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ImageView avatar;
    TextView userNameTV, loginTV, followersTV, followingTV, emailTV, blogTV;
    Button btn_OwnedRepos;

    Bundle extras;
    String newString;

    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        iniLiaz();

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);
        loadData();

    }

    public void iniLiaz()
    {
        avatar = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        loginTV = (TextView) findViewById(R.id.login);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        emailTV = (TextView) findViewById(R.id.email);
        blogTV = (TextView) findViewById(R.id.blog);

        btn_OwnedRepos = (Button) findViewById(R.id.btn_OwnedRepos);
    }

    public void loadOwnRepos(View view) {

        Intent intent = new Intent(UserActivity.this, RepositoriesActivity.class);
        intent.putExtra("username", newString);
        startActivity(intent);

    }

    //load avatar image
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {

                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }
    //end load avatar image

    //membuat method baru untuk load data API
    public void loadData()
    {
        final GitHubUserEndPoint apiServices =
                APIClient.getClient().create(GitHubUserEndPoint.class);

        //panggil class GitHubUser dan mendapatkan method getUser dari interface GitHubUserEndPoint
        Call<GitHubUser> call = apiServices.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                //image
                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar()).get();

                } catch (Exception e) {

                    e.printStackTrace();

                }
                avatar.setImageBitmap(myImage); //tidak ada ini maka image tidak terpanggil
                //avatar.getLayoutParams().height=220;
                //avatar.getLayoutParams().width=220;
                //end image

                if (response.body().getName() == null)
                    {
                        userNameTV.setText("No name provided");
                    }
                else
                {
                    userNameTV.setText("Username: \n" + response.body().getName());
                }

                loginTV.setText("Login: " + response.body().getLogin());
                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());

                if (response.body().getEmail() == null)
                {
                    emailTV.setText("No email provided");
                }
                else {
                    emailTV.setText("Email: " + response.body().getEmail());
                }

                if (response.body().getBlog() == null)
                {
                    blogTV.setText("No blog provide");
                }
                else {
                    blogTV.setText("Blog: " + response.body().getBlog());
                }

            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

                //log error  here since request failed

            }
        });
    }
}
