package com.project.MyBus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class removeBusActivity extends AppCompatActivity {

    Button mButtonRemoveBusOK;
    Button mButtonRemoveBusCancel;
    EditText mTextBusID;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bus);

        mButtonRemoveBusOK = (Button) findViewById(R.id.button_OK);
        mButtonRemoveBusCancel = (Button) findViewById(R.id.button_Cancel);
        mTextBusID = (EditText) findViewById(R.id.edittext_busid);
        db = new DatabaseHelper(this);

        mButtonRemoveBusOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busID = mTextBusID.getText().toString().trim();
                Boolean res = db.removeBus(busID);

                if(res == true)
                {
                    Toast.makeText(removeBusActivity.this, "Bus Successfully Removed", Toast.LENGTH_SHORT).show();
                    Intent adminMenuPage = new Intent(removeBusActivity.this,adminMenuActivity.class);
                    startActivity(adminMenuPage);
                }
                else
                {
                    Toast.makeText(removeBusActivity.this, "Bus Remove Error", Toast.LENGTH_SHORT).show();
                }
            }
        });



        mButtonRemoveBusCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminMenuPage = new Intent(removeBusActivity.this,adminMenuActivity.class);
                startActivity(adminMenuPage);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
    }
}