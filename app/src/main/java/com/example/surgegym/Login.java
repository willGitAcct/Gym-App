package com.example.surgegym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    public void back(View view){
        Intent back = new Intent(Login.this, LandingPage.class);
        startActivity(back);
    }

    public void backSignUp(View view){
        Intent backSignUp = new Intent(Login.this, SignUp.class);
        startActivity(backSignUp);
    }

    public void goToPassChange(View view){
        startActivity(new Intent(getApplicationContext(), Password.class));
    }

    public void googleLogin(View view){

    }

    public void facebookLogin(View view){

    }


    public void login(View view){


        EditText email = (EditText) findViewById(R.id.inputEmail);
        EditText password = (EditText) findViewById(R.id.inputPassword);

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();



        if(emailString.isEmpty()){
            email.setError("Member Number required!");
            email.requestFocus();
            return;
        }
        if(passwordString.isEmpty()){
            password.setError("Password Required!");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent mainMenu = new Intent(Login.this, MainPage.class);
                    startActivity(mainMenu);
                }else{
                    Toast.makeText(Login.this, "Error, please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

    }
}