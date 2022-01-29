package com.example.loginapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_reg,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_reg = findViewById(R.id.btn_reg);
        btn_login = findViewById(R.id.btn_login);

        btn_reg.setOnClickListener(view -> {
            startActivity(new Intent(this,RegActivity.class));
        });

        btn_login.setOnClickListener(view -> {
            startActivity(new Intent(this,LoginActivity.class));
        });
    }
}