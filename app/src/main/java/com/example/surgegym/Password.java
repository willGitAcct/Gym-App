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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Password extends AppCompatActivity {
    FirebaseAuth mAuth;

    public void passwordBack(View view){
        Intent login = new Intent(Password.this, LandingPage.class);
        startActivity(login);
    }

    public void changePassword(View view){

        EditText emailText = (EditText) findViewById(R.id.inputEmail);
        String email = emailText.getText().toString();

        if(email.isEmpty()){
            emailText.setError("Email is required!");
            emailText.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Password.this, "Password Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Password.this, "Try again; possibly an incorrect email input.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mAuth= FirebaseAuth.getInstance();

    }
}