package com.example.githubapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapi.R;
import com.example.githubapi.model.GitHubRepos;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<GitHubRepos> repos;
    private int rowLayout;
    private Context context;

    public ReposAdapter(List<GitHubRepos> repos, int rowLayout, Context context) {
        this.setRepos(repos);
        this.setRowLayout(rowLayout);
        this.setContext(context);
    }

    public List<GitHubRepos> getRepos() {
        return repos;
    }

    public void setRepos(List<GitHubRepos> repos) {
        this.repos = repos;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    //membuat static class untuk iniLiaz list_item_repos.xml
    public static class ReposViewHolder extends RecyclerView.ViewHolder {
        LinearLayout reposLayout;
        TextView repoName;
        TextView repoDescription;
        TextView repolanguage;


        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);

            reposLayout = (LinearLayout) itemView.findViewById(R.id.repo_item_layout);
            repoName = (TextView) itemView.findViewById(R.id.repoName);
            repoDescription = (TextView) itemView.findViewById(R.id.repoDescription);
            repolanguage = (TextView) itemView.findViewById(R.id.repoLanguage);
        }
    }

    ///extedn dari Viewholder
    @NonNull
    @Override
    public ReposAdapter.ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {

        holder.repoName.setText(repos.get(position).getName());
        holder.repoDescription.setText(repos.get(position).getDescription());
        holder.repolanguage.setText(repos.get(position).getLanguage());

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

}