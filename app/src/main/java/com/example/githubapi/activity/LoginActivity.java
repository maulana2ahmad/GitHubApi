package com.example.githubapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.githubapi.R;

public class LoginActivity extends AppCompatActivity {

    private EditText inputusername;

    private Button login;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        iniLiaz();

    }

    public void iniLiaz()
    {
        inputusername = (EditText) findViewById(R.id.input_username);
        login = (Button) findViewById(R.id.login);
    }


    public void getUser(View view) {

        i = new Intent(LoginActivity.this, UserActivity.class);
        i.putExtra("STRING_I_NEED", inputusername.getText().toString());
        startActivity(i);
    }
}
