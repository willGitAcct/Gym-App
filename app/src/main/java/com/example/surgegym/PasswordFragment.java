package com.example.surgegym;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordFragment extends Fragment {
    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        Button passwordChangeFragment = (Button) view.findViewById(R.id.changepassFragment);
        EditText inputEmailFragment = (EditText) view.findViewById(R.id.inputEmailFragment);

        passwordChangeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmailFragment.getText().toString();

                if(email.isEmpty()){
                    inputEmailFragment.setError("Email is required!");
                    inputEmailFragment.requestFocus();
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Password Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Try again; possibly an incorrect email input.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }
}