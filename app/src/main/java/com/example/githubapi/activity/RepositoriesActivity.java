package com.example.githubapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.githubapi.R;
import com.example.githubapi.adapter.ReposAdapter;
import com.example.githubapi.model.GitHubRepos;
import com.example.githubapi.rest.GitHubReposEndPoint;
import com.example.githubapi.rest.client.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesActivity extends AppCompatActivity {

    String receivedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GitHubRepos> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");

        userNameTV = (TextView) findViewById(R.id.userNameTV);

        userNameTV.setText("User: " + receivedUserName);

        mRecyclerView= (RecyclerView) findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo,
                getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();


    }

    public void loadRepositories()
    {
        GitHubReposEndPoint apiServces =
                APIClient.getClient().create(GitHubReposEndPoint.class);

        Call<List<GitHubRepos>> call = apiServces.getRepos(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepos>>() {
            @Override
            public void onResponse(Call<List<GitHubRepos>> call, Response<List<GitHubRepos>> response) {

                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepos>> call, Throwable t) {

                //Log error here since request failed
                Log.e("Repos", t.toString());

            }
        });
    }
}
