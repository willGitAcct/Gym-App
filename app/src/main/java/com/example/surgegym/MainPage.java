package com.example.surgegym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.surgegym.databinding.ActivityMainpageBinding;
import com.google.android.gms.maps.MapView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Map;

public class MainPage extends AppCompatActivity {

    ActivityMainpageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeNav:
                    replaceFragment(new HomeFragment());
                    ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.VISIBLE);
                    break;

                case R.id.classesNav:
                    ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.INVISIBLE);
                    replaceFragment(new ClassesFragment());
                    break;

                case R.id.locationsNav:
                    ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.INVISIBLE);
                    replaceFragment(new MapsFragment());
                    break;

                case R.id.trainersNav:
                    ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.INVISIBLE);
                    replaceFragment(new TrainersFragment());
                    break;

                case R.id.partnersNav:
                    ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.INVISIBLE);
                    replaceFragment((new PartnersFragment()));
                    break;
            }

            return true;
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userLoggedin = user.getUid().toString();

        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(userLoggedin , BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.idIVQrCode)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
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
                ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.INVISIBLE);
                replaceFragment(new PaymentFragment());
                break;

            case R.id.passwordNav:
                ((ImageView) findViewById(R.id.idIVQrCode)).setVisibility(View.INVISIBLE);
                replaceFragment(new PasswordFragment());
                break;

            case R.id.logoutNav:
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
}