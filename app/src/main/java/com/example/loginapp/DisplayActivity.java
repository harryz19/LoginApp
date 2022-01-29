package com.example.loginapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView welcome,tv_det;
    DBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        welcome = findViewById(R.id.welcome);
        tv_det = findViewById(R.id.tv_det);

        myDBHelper = new DBHelper(this);

        String user = getIntent().getStringExtra("username");
        welcome.setText("Welcome!, "+user);

        Cursor cursor = myDBHelper.showdata(user);
        StringBuilder stringBuilder = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append("NAME : ").append(cursor.getString(0)).append("\n");
            stringBuilder.append("ADDRESS : ").append(cursor.getString(1)).append("\n");
            stringBuilder.append("MOBILE : ").append("+91").append(cursor.getString(2)).append("\n");
            stringBuilder.append("DOB : ").append(cursor.getString(3)).append("\n");
            stringBuilder.append("EMAIL : ").append(cursor.getString(4)).append("@gmail.com");
        }
        tv_det.setText(stringBuilder);

    }
}