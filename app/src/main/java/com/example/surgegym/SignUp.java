package com.example.surgegym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    public void back(View view){
        Intent back = new Intent(SignUp.this, LandingPage.class);
        startActivity(back);
    }

    public void next(View view){
        final EditText firstname = (EditText)findViewById(R.id.inputFirstName);
        final EditText lastname = (EditText)findViewById(R.id.inputLastName);
        final EditText email = (EditText) findViewById(R.id.inputEmail);
        final EditText confirm_email = (EditText) findViewById(R.id.inputConfirmEmail);
        final EditText phone_number = (EditText) findViewById(R.id.inputPhone);
        final EditText date_birth = (EditText) findViewById(R.id.inputDate);
        final Spinner gender = (Spinner) findViewById(R.id.inputGender);

        final EditText address = (EditText) findViewById(R.id.inputAddress);
        final EditText apt = (EditText) findViewById(R.id.inputAptNum);
        final EditText city = (EditText) findViewById(R.id.inputCity);
        final EditText postal_code = (EditText) findViewById(R.id.inputPostalCode);
        final Spinner province = (Spinner) findViewById(R.id.inputProvince);

        String stringFirstName = firstname.getText().toString();
        String stringLastName = lastname.getText().toString();
        String stringEmail = email.getText().toString();
        String stringPhoneNumber = phone_number.getText().toString();
        String stringDateBirth= date_birth.getText().toString();
        String stringGender = gender.getSelectedItem().toString();

        String stringAddress = address.getText().toString() + " " + apt.getText().toString() + ", " + city.getText().toString() + ", " + postal_code.getText().toString() + ", " + province.getSelectedItem().toString();

        if(stringGender.equalsIgnoreCase("Gender:") != true
                && province.getSelectedItem().toString().equalsIgnoreCase("Province:") != true
                && email.getText().toString().equalsIgnoreCase(confirm_email.getText().toString()) == true){

            Intent intent = new Intent(SignUp.this, SignUp2.class);

            intent.putExtra("FNAME", stringFirstName);
            intent.putExtra("LNAME", stringLastName);
            intent.putExtra("EMAIL", stringEmail);
            intent.putExtra("PHONE", stringPhoneNumber);
            intent.putExtra("BIRTH", stringDateBirth);
            intent.putExtra("GENDER", stringGender);
            intent.putExtra("ADDRESS", stringAddress);

            startActivity(intent);


        } else {
            Toast.makeText(getApplicationContext(),"Please Enter Valid Information", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner spinnerGender = findViewById(R.id.inputGender);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGender.setAdapter(adapter);

        Spinner spinnerProvince = findViewById(R.id.inputProvince);
        adapter = ArrayAdapter.createFromResource(this, R.array.Province, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerProvince.setAdapter(adapter);

        final EditText firstname = (EditText)findViewById(R.id.inputFirstName);
        final EditText lastname = (EditText)findViewById(R.id.inputLastName);
        final EditText email = (EditText) findViewById(R.id.inputEmail);
        final EditText confirm_email = (EditText) findViewById(R.id.inputConfirmEmail);
        final EditText phone_number = (EditText) findViewById(R.id.inputPhone);
        final EditText date_birth = (EditText) findViewById(R.id.inputDate);
        final Spinner gender = (Spinner) findViewById(R.id.inputGender);

        final EditText address = (EditText) findViewById(R.id.inputAddress);
        final EditText apt = (EditText) findViewById(R.id.inputAptNum);
        final EditText city = (EditText) findViewById(R.id.inputCity);
        final EditText postal_code = (EditText) findViewById(R.id.inputPostalCode);
        final Spinner province = (Spinner) findViewById(R.id.inputProvince);

        Intent intent = getIntent();
        firstname.setText(intent.getStringExtra("FNAME2"));
        lastname.setText(intent.getStringExtra("LNAME2"));
        email.setText(intent.getStringExtra("EMAIL2"));
        confirm_email.setText((intent.getStringExtra("EMAIL2")));
        phone_number.setText(intent.getStringExtra("PHONE2"));
        date_birth.setText(intent.getStringExtra("BIRTH2"));

    }
}