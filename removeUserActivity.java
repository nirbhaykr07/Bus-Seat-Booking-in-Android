package com.project.MyBus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class removeUserActivity extends AppCompatActivity {

    Button mButtonRemoveUserOK;
    Button mButtonRemoveUserCancel;
    EditText mTextUserID;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);

        mButtonRemoveUserOK = (Button) findViewById(R.id.button_OK);
        mButtonRemoveUserCancel = (Button) findViewById(R.id.button_Cancel);
        mTextUserID = (EditText) findViewById(R.id.edittext_userid);
        db = new DatabaseHelper(this);

        mButtonRemoveUserOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = mTextUserID.getText().toString().trim();
                Boolean res = db.removeUser(userID);

                if(res == true)
                {
                    Toast.makeText(removeUserActivity.this, "User Successfully Removed", Toast.LENGTH_SHORT).show();
                    Intent adminMenuPage = new Intent(removeUserActivity.this,adminMenuActivity.class);
                    startActivity(adminMenuPage);
                }
                else
                {
                    Toast.makeText(removeUserActivity.this, "User Remove Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonRemoveUserCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminMenuPage = new Intent(removeUserActivity.this,adminMenuActivity.class);
                startActivity(adminMenuPage);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
    }
}