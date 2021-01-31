package com.project.MyBus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adminMenuActivity extends AppCompatActivity {

    Button mButtonAddBus;
    Button mButtonRemoveBus;
    Button mButtonRemoveUser;
    Button mButtonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        mButtonAddBus = (Button) findViewById(R.id.button_add_bus);
        mButtonRemoveBus = (Button) findViewById(R.id.button_remove_bus);
        mButtonRemoveUser = (Button) findViewById(R.id.button_remove_user);
        mButtonLogOut = (Button) findViewById(R.id.button_logout);

        mButtonAddBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addBusPage = new Intent(adminMenuActivity.this,addBusActivity.class);
                startActivity(addBusPage);
            }
        });

        mButtonRemoveBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addBusPage = new Intent(adminMenuActivity.this,removeBusActivity.class);
                startActivity(addBusPage);
            }
        });


        mButtonRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addBusPage = new Intent(adminMenuActivity.this,removeUserActivity.class);
                startActivity(addBusPage);
            }
        });

        mButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginPage = new Intent(adminMenuActivity.this,LoginActivity.class);
                startActivity(loginPage);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
    }
}