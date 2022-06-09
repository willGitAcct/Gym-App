package com.example.surgegym;

import org.junit.Test;

import static org.junit.Assert.*;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;


import java.util.Random;

public class ExampleUnitTest {
    FirebaseUser user;

    @Test
    public void randomMemberNo() {
        Random rand = new Random();
        int max = 999999;
        int min = 100000;
        int member_number1 = (int)Math.floor(Math.random()*(max-min+1)+min);
        int member_number2 = (int)Math.floor(Math.random()*(max-min+1)+min);

        assertNotEquals(member_number1, member_number2);
    } //Test passed if both numbers are not the same since they are suppose to be random.

    @Test
    public void randomPassword(){
        int passwordmax = 9999999;
        int passwordmin = 1000000;
        int password_number1 = (int)Math.floor(Math.random()*(passwordmax-passwordmin+1)+passwordmin);
        int password_number2 = (int)Math.floor(Math.random()*(passwordmax-passwordmin+1)+passwordmin);

        assertNotEquals(password_number1,password_number2);
    } //Test passed if both numbers are not the same since they are suppose to be random.
}