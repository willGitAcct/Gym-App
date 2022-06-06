package com.example.surgegym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {
    String memberNum;
    String tempPass;
    public void welcomeBack(View view){
        Intent intent = new Intent(Confirmation.this, LandingPage.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        memberNum = intent.getStringExtra("MemberNum");
        tempPass = intent.getStringExtra("TempPass");

        TextView memberNumView = (TextView) findViewById(R.id.memberNum);
        TextView tempPassView = (TextView) findViewById(R.id.password);

        memberNumView.setText(memberNum);
        tempPassView.setText(tempPass);

    }
}