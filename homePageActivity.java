package com.project.MyBus;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;
import android.app.AlertDialog;

public class homePageActivity extends AppCompatActivity {

    Button mButtonBookTickets;
    Button mButtonViewTickets;
    Button mButtonLogOut;
    DatabaseHelper db;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mButtonBookTickets = (Button) findViewById(R.id.button_book_tickets);
        mButtonViewTickets = (Button) findViewById(R.id.button_view_tickets);
        mButtonLogOut = (Button) findViewById(R.id.button_logout);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        user = intent.getStringExtra("username");

        mButtonBookTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookTickets = new Intent(homePageActivity.this,bookTicketsActivity.class);
                bookTickets.putExtra("username",user);
                startActivity(bookTickets);
            }
        });

        mButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginPage = new Intent(homePageActivity.this,LoginActivity.class);
                startActivity(loginPage);
            }
        });

        mButtonViewTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getAllData(user);
                if(res.getCount() == 0) {
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("BUS: "+ res.getString(1)+"\n");
                    buffer.append("Source: "+ res.getString(2)+"\n");
                    buffer.append("Destination: "+ res.getString(3)+"\n");
                    buffer.append("Seats: "+ res.getString(4)+"\n");
                    buffer.append("Date: "+ res.getString(5)+"\n");
                    buffer.append("Time: "+ res.getString(6)+"\n\n");
                }

                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onBackPressed()
    {
    }
}