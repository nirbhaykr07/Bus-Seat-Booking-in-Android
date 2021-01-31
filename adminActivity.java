package com.project.MyBus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminActivity extends AppCompatActivity {

    TextView mTextViewLogin;
    Button          mButtonLogin;
    EditText mTextUsername;
    EditText        mTextPassword;
    DatabaseHelper  db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginPage = new Intent(adminActivity.this,LoginActivity.class);
                startActivity(loginPage);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();

                if(user.toLowerCase().equals("admin") && pwd.toLowerCase().equals("admin"))
                {
                    Toast.makeText(adminActivity.this, "Admin Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent adminMenuPage = new Intent(adminActivity.this,adminMenuActivity.class);
                    startActivity(adminMenuPage);
                }
                else
                {
                    Toast.makeText(adminActivity.this, "Admin Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
    }
}