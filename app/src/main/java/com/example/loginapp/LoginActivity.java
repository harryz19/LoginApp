package com.example.loginapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText et_userid,et_pass;
    Button btn_logindet;
    DBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_userid = findViewById(R.id.et_userid);
        et_pass = findViewById(R.id.et_pass);
        btn_logindet = findViewById(R.id.btn_logindet);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        myDBHelper = new DBHelper(this);

        btn_logindet.setOnClickListener(view -> {
            boolean checklogin = myDBHelper.checkuserpass(et_userid.getText().toString(),et_pass.getText().toString());
            if(checklogin){
                Intent intent = new Intent(this,DisplayActivity.class);
                intent.putExtra("username",et_userid.getText().toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this,"Invalid credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }
}