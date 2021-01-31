package com.project.MyBus;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class bookTicketsActivity extends AppCompatActivity implements
        OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    TextView dateFormat;
    int year;
    int month;
    int day;
    Spinner spinSource;
    Spinner spinDest;
    SQLiteDatabase db;
    Button mButtonSearchBus;
    Button mButtonSearchCancel;
    String user;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tickets);
        mButtonSearchBus = (Button) findViewById(R.id.button_search);
        mButtonSearchCancel = (Button) findViewById(R.id.button_Cancel);
        spinSource = (Spinner) findViewById(R.id.spinnerSource);
        spinDest = (Spinner) findViewById(R.id.spinnerDest);
        spinSource.setOnItemSelectedListener(this);
        spinDest.setOnItemSelectedListener(this);
        loadSpinnerInfo();

        Intent intent = getIntent();
        user = intent.getStringExtra("username");

        dateFormat = findViewById(R.id.date_text);
        findViewById(R.id.date_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        mButtonSearchBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source = spinSource.getSelectedItem().toString();
                String dest = spinDest.getSelectedItem().toString();
                String date = dateFormat.getText().toString();
                //Toast.makeText(bookTicketsActivity.this, source +" "+ dest+" " + date, Toast.LENGTH_SHORT).show();
                Intent showBus = new Intent(bookTicketsActivity.this,showBusActivity.class);
                showBus.putExtra("source",source);
                showBus.putExtra("destination",dest);
                showBus.putExtra("date",date);
                showBus.putExtra("username",user);
                startActivity(showBus);
            }
        });

        mButtonSearchCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(bookTicketsActivity.this,homePageActivity.class);
                homePage.putExtra("username",user);
                startActivity(homePage);
            }
        });

    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void loadSpinnerInfo()
    {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<String> source = db.getBusSource();
        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, source);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSource.setAdapter(sourceAdapter);

        List<String> dest = db.getBusDest();
        ArrayAdapter<String> destAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dest);
        destAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDest.setAdapter(destAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month+1) + "/" + year;
        dateFormat.setText(date);
    }

    @Override
    public void onBackPressed()
    {
    }
}