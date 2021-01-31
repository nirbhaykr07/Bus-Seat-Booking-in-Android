package com.project.MyBus;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.annotation.SuppressLint;

public class showBusActivity extends AppCompatActivity implements OnItemSelectedListener{

    Spinner spinAvailableBus;
    SQLiteDatabase db;
    Button mButtonBookSeats;
    Button mButtonBookCancel;
    EditText mTextBusAvailSeats;
    EditText mTextBusBookSeats;
    EditText mTextBusTime;
    String src,dest,user,date,time;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bus);

        Intent intent = getIntent();

        src = intent.getStringExtra("source");
        dest = intent.getStringExtra("destination");
        date = intent.getStringExtra("date");
        user = intent.getStringExtra("username");

        mTextBusAvailSeats = (EditText) findViewById(R.id.bus_AvailableSeats);
        mTextBusAvailSeats.setEnabled(false);
        mTextBusBookSeats = (EditText) findViewById(R.id.bus_seats);
        mTextBusTime = (EditText) findViewById(R.id.bus_time);
        mTextBusTime.setEnabled(false);
        mButtonBookSeats = (Button) findViewById(R.id.button_book);
        mButtonBookCancel = (Button) findViewById(R.id.button_Cancel);
        spinAvailableBus = (Spinner) findViewById(R.id.spinnerBus);
        spinAvailableBus.setOnItemSelectedListener(this);
        loadSpinnerInfo();

        spinAvailableBus.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                mTextBusAvailSeats.setText(db.getSelectedSeat(spinAvailableBus.getSelectedItem().toString()));
                time = db.getSelectedTime(spinAvailableBus.getSelectedItem().toString());
                mTextBusTime.setText(time);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mButtonBookSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                if(db.bookTickets(spinAvailableBus.getSelectedItem().toString(), mTextBusBookSeats.getText().toString(), user, src, dest, date, time))
                {
                    Toast.makeText(showBusActivity.this, "Seat(s) Successfully Booked from "+ src+" to " + dest, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(showBusActivity.this, "Seat(s) Successfully Booked "+ src+" " + dest+" "+date+" username:"+user+" :", Toast.LENGTH_SHORT).show();
                    Intent ticketsPage = new Intent(showBusActivity.this,bookTicketsActivity.class);
                    ticketsPage.putExtra("username",user);
                    startActivity(ticketsPage);
                }
                else
                    Toast.makeText(showBusActivity.this, "Seat(s) Booking Error", Toast.LENGTH_SHORT).show();
            }
        });

        mButtonBookCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticketsPage = new Intent(showBusActivity.this,bookTicketsActivity.class);
                ticketsPage.putExtra("username",user);
                startActivity(ticketsPage);
            }
        });
    }

    private void loadSpinnerInfo()
    {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        //Toast.makeText(showBusActivity.this, " "+ src+" " + dest+" " + date, Toast.LENGTH_SHORT).show();
        List<String> source = db.getAvailableBus(src, dest, date);
        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, source);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAvailableBus.setAdapter(sourceAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onBackPressed()
    {
    }
}