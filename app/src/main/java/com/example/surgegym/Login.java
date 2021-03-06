package com.example.surgegym;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class Login extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FirebaseAuth mAuth;
    CallbackManager callbackManager;
    FirebaseAuth.AuthStateListener authStateListener;
    AccessTokenTracker accessTokenTracker;
    LoginButton fblogin;
    ImageButton google;

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

    public void googleLogin(){
        Intent mainMenu = gsc.getSignInIntent();
        startActivityForResult(mainMenu, 1000);
    }

    public void facebookLogin(View view){

    }


    public void login(View view){


        EditText email = (EditText) findViewById(R.id.inputEmail);
        EditText password = (EditText) findViewById(R.id.inputPassword);

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        email.requestFocus();


        if(emailString.isEmpty()){
            email.setError("Email required!");
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

        //google stuff
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("164177517014-8lcp40ugu04frqsr8g888t0dt8nla56h.apps.googleusercontent.com")
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        google = (ImageButton) findViewById(R.id.googleBtn);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogin();
            }
        });

        //fb stuff
        FacebookSdk.sdkInitialize(getApplicationContext());
        fblogin = (LoginButton) findViewById(R.id.facebookBtn);
        fblogin.setReadPermissions(Arrays.asList("email"));
        callbackManager = CallbackManager.Factory.create();
        fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Logging in with Facebook..", Toast.LENGTH_SHORT).show();
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(@Nullable AccessToken accessToken, @Nullable AccessToken accessToken1) {
                if(accessToken1== null){
                    mAuth.signOut();
                }
            }
        };
    }

    void handleFacebookToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
//                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                    startActivity(intent);
                    //navigateToHome();
                    Intent mainMenu = new Intent(Login.this, MainPage.class);
                    startActivity(mainMenu);
                }else {
                    Toast.makeText(getApplicationContext(), "authen failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> Task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //
                GoogleSignInAccount account = Task.getResult(ApiException.class);
                firebaseAuthGoog(account);
                Toast.makeText(this, "Signing in w/ Google..", Toast.LENGTH_SHORT).show();
                //navigateToHome();
            } catch (ApiException e) {
                //e.printStackTrace();
                Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        }
    }// end activityresult

    private void firebaseAuthGoog(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    //googleLogin();
                    startActivity(new Intent(getApplicationContext(), MainPage.class));

                } else {
                    Toast.makeText(Login.this, "Auth failed", Toast.LENGTH_SHORT).show();
                }
            }
        });// end firebaseauth

    }


}
