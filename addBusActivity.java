package com.project.MyBus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addBusActivity extends AppCompatActivity {

    Button mButtonAddBusOK;
    Button mButtonAddBusCancel;
    EditText mTextBusID;
    EditText mTextBusSource;
    EditText mTextBusDest;
    EditText mTextBusSeats;
    EditText mTextBusJourneyDate;
    EditText mTextBusJourneyTime;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        mButtonAddBusOK = (Button) findViewById(R.id.button_OK);
        mButtonAddBusCancel = (Button) findViewById(R.id.button_Cancel);
        mTextBusID = (EditText) findViewById(R.id.edittext_enter_busID);
        mTextBusSource = (EditText) findViewById(R.id.edittext_bus_source);
        mTextBusDest = (EditText) findViewById(R.id.edittext_bus_destination);
        mTextBusSeats = (EditText) findViewById(R.id.edittext_bus_seats);
        mTextBusJourneyDate = (EditText) findViewById(R.id.edittext_journey_date);
        mTextBusJourneyTime = (EditText) findViewById(R.id.edittext_journey_time);
        db = new DatabaseHelper(this);

        mButtonAddBusOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busID = mTextBusID.getText().toString().trim();
                String source = mTextBusSource.getText().toString().trim();
                String destination = mTextBusDest.getText().toString().trim();
                String seatsStr = mTextBusSeats.getText().toString().trim();
                int seats=Integer.parseInt(seatsStr);
                String date = mTextBusJourneyDate.getText().toString().trim();
                String time = mTextBusJourneyTime.getText().toString().trim();
                long val = db.addBus(busID,source,destination,seats,date,time);
                if(val > 0) {
                    Toast.makeText(addBusActivity.this, "Bus Successfully Added", Toast.LENGTH_SHORT).show();
                    Intent adminMenuPage = new Intent(addBusActivity.this,adminMenuActivity.class);
                    startActivity(adminMenuPage);
                }
                else {
                    Toast.makeText(addBusActivity.this, "Bus Add Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonAddBusCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminMenuPage = new Intent(addBusActivity.this,adminMenuActivity.class);
                startActivity(adminMenuPage);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
    }
}