package com.example.surgegym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.surgegym.databinding.ActivityMainpageBinding;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Map;

public class MainPage extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ActivityMainpageBinding binding;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("164177517014-8lcp40ugu04frqsr8g888t0dt8nla56h.apps.googleusercontent.com")
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeNav:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.classesNav:
                    replaceFragment(new ClassesFragment());
                    break;

                case R.id.locationsNav:
                    replaceFragment(new MapsFragment());
                    break;

                case R.id.trainersNav:
                    replaceFragment(new TrainersFragment());
                    break;

                case R.id.partnersNav:
                    replaceFragment((new PartnersFragment()));
                    break;
            }

            return true;
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.paymentNav:
                replaceFragment(new PaymentFragment());
                break;

            case R.id.passwordNav:
                replaceFragment(new PasswordFragment());
                break;

            case R.id.logoutNav:
                Toast.makeText(this, "Signing out..", Toast.LENGTH_SHORT).show();
                //FirebaseAuth.getInstance().signOut();
//                LoginManager.getInstance().logOut();//works! woo
//                startActivity(new Intent(this, LandingPage.class));
                signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                FirebaseAuth.getInstance().signOut();

                //mAuth.signOut();
                LoginManager.getInstance().logOut();//works! woo
                startActivity(new Intent(getApplicationContext(), LandingPage.class));
            }
        });
    }

}