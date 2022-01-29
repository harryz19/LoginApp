package com.example.loginapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegActivity extends AppCompatActivity {

    EditText et_name, et_add, et_mn, et_dob, et_email, et_username, et_password;
    Button btn_register;
    DBHelper myDBHelper;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_name = findViewById(R.id.et_name);
        et_add = findViewById(R.id.et_add);
        et_mn = findViewById(R.id.et_mn);
        et_dob = findViewById(R.id.et_dob);
        et_email = findViewById(R.id.et_email);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        btn_register = findViewById(R.id.btn_register);
        myDBHelper = new DBHelper(this);

        btn_register.setOnClickListener(view -> {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("NAME : ").append(et_name.getText().toString()).append("\n");
            stringBuilder.append("ADDRESS : ").append(et_add.getText().toString()).append("\n");
            stringBuilder.append("MOBILE : ").append(et_mn.getText().toString()).append("\n");
            stringBuilder.append("DOB : ").append(et_dob.getText().toString()).append("\n");
            stringBuilder.append("EMAIL : ").append(et_email.getText().toString()).append("\n");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Register")
                    .setMessage("Do you want to register?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {

                        boolean isInserted = myDBHelper.insertData(et_name.getText().toString(), et_add.getText().toString(), et_mn.getText().toString(),
                                et_dob.getText().toString(), et_email.getText().toString(), et_username.getText().toString(), et_password.getText().toString());

                        if (isInserted) {
                            Toast.makeText(this, "Data inserted successfully.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Error! Data not inserted.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        Toast toast = Toast.makeText(RegActivity.this, stringBuilder, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    })
                    .show();
        });

        et_dob.setOnClickListener(view -> {

            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);

            picker = new DatePickerDialog(RegActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    et_dob.setText(i2 + "-" + (i1 + 1) + "-" + i);
                }
            }, year, month, day);
            picker.show();
        });

    }

}
