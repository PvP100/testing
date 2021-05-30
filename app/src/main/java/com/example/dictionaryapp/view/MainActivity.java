package com.example.dictionaryapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dictionaryapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnSignUpScreen, btnSignInScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
    }

    private void mapping() {
        btnSignInScreen = findViewById(R.id.btnSignInScreen);
        btnSignUpScreen = findViewById(R.id.btnSignUpScreen);
    }

    public void signInClick(View view) {
        startActivity(new Intent(this, SignInScreen.class));
    }

    public void signUpClick(View view) {
        startActivity(new Intent(this, SignUpScreen.class));
    }
}