package com.example.surgegym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LandingPage extends AppCompatActivity {

    public void login(View view){
        Intent login = new Intent(LandingPage.this, Login.class);
        startActivity(login);
    }

    public void signup(View view){
        Intent signup = new Intent(LandingPage.this, SignUp.class);
        startActivity(signup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        //Intent intent = new Intent(LandingPage.this, Main.class);
        //startActivity(intent);
    }
}