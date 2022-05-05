package com.example.chatsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chatsystem.R;

public class ChooseRoleActivity extends AppCompatActivity implements View.OnClickListener{

    private Button studentButton, tutorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        studentButton = (Button) findViewById(R.id.studentButton);
        studentButton.setOnClickListener(this);
        tutorButton = (Button) findViewById(R.id.tutorButton);
        tutorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.studentButton:
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.tutorButton:
                Toast.makeText(ChooseRoleActivity.this, "Only student user can use this application at the moment.", Toast.LENGTH_SHORT).show();
        }
    }
}