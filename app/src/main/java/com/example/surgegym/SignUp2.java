package com.example.surgegym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SignUp2 extends AppCompatActivity {
    String stringFirstName, stringLastName, stringEmail, stringPhoneNumber, stringDateBirth , stringGender ,stringAddress, packageChoice;

    private FirebaseAuth mAuth;

    public void back(View view){
        Intent intent = new Intent(SignUp2.this, SignUp.class);

        intent.putExtra("FNAME2", stringFirstName);
        intent.putExtra("LNAME2", stringLastName);
        intent.putExtra("EMAIL2", stringEmail);
        intent.putExtra("PHONE2", stringPhoneNumber);
        intent.putExtra("BIRTH2", stringDateBirth);
        intent.putExtra("GENDER2", stringGender);
        intent.putExtra("ADDRESS2", stringAddress);

        startActivity(intent);
    }

    public void SignUp(View view){
        Spinner spinnerPackage = findViewById(R.id.packageSpinner);
        packageChoice = spinnerPackage.getSelectedItem().toString();

        Random rand = new Random();
        int max = 999999;
        int min = 100000;
        int member_number = (int)Math.floor(Math.random()*(max-min+1)+min); //random username
        String memberString = Integer.toString(member_number);

        int passwordmax = 9999999;
        int passwordmin = 1000000;
        int password_number = (int)Math.floor(Math.random()*(passwordmax-passwordmin+1)+passwordmin); //random username
        String passwordString = Integer.toString(password_number);

        //creating the account on firebase here
        //had to make a new User class with your variables, its the only way I know how haha
        mAuth.createUserWithEmailAndPassword(stringEmail, passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(stringFirstName, stringLastName, stringEmail, stringPhoneNumber, stringDateBirth, stringGender, stringAddress, packageChoice, member_number);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUp2.this, "Registration successful!", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(SignUp2.this, Confirmation.class);
                                                intent.putExtra("MemberNum", memberString);
                                                intent.putExtra("TempPass", passwordString);
                                                startActivity(intent);

                                            } else {
                                                Toast.makeText(SignUp2.this, "Error :( Failed to register, try again", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }//end if
                        else {
                            Toast.makeText(SignUp2.this, "Failed to register. Check that your email isn't already used.", Toast.LENGTH_SHORT).show();
                        }
                    }//end onComplete
                });//end createuser

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        Intent intent = getIntent();
        stringFirstName = intent.getStringExtra("FNAME");
        stringLastName = intent.getStringExtra("LNAME");
        stringEmail = intent.getStringExtra("EMAIL");
        stringPhoneNumber = intent.getStringExtra("PHONE");
        stringDateBirth = intent.getStringExtra("BIRTH");
        stringGender = intent.getStringExtra("GENDER");
        stringAddress = intent.getStringExtra("ADDRESS");

        TextView textFName = (TextView) findViewById(R.id.textFName);
        textFName.setText("First Name: " + stringFirstName);

        TextView textLName = (TextView) findViewById(R.id.textLName);
        textLName.setText("Last Name: " + stringLastName);

        TextView textEmail = (TextView) findViewById(R.id.textEmail);
        textEmail.setText("Email: " + stringEmail);

        TextView textPhone = (TextView) findViewById(R.id.textPhone);
        textPhone.setText("Phone Number: " + stringPhoneNumber);

        TextView textDateBirth = (TextView) findViewById(R.id.textDateBirth);
        textDateBirth.setText("Date of Birth: " + stringDateBirth);

        TextView textGender = (TextView) findViewById(R.id.textGender);
        textGender.setText("Gender: " + stringGender);

        //Bug where I cant change the id for some reason
        TextView textAddress = (TextView) findViewById(R.id.textGender2);
        textAddress.setText("Address: " + stringAddress);

        Spinner spinnerPackage = findViewById(R.id.packageSpinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.Packages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPackage.setAdapter(adapter);

        //Commented out to avoid crashing
        //mAuth = FirebaseAuth.getInstance();

    }
}