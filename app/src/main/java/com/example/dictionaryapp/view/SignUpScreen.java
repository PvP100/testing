package com.example.dictionaryapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.StringUltil.StringURL;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtUsername, edtPassword, edtConfirmPass, edtEmail, edtPhoneNumber;
    Button btnSignUp;
    RadioGroup radioGroupSignUp;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        mapping();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getText().toString().trim().equals(edtConfirmPass.getText().toString().trim())) {
                    dangKy(StringURL.signUpURL);
                }
            }
        });


    }

    private void mapping() {
        radioGroupSignUp = findViewById(R.id.radioGroupSignUp);
        edtConfirmPass = findViewById(R.id.edtRePassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtUsername = findViewById(R.id.edtUsernameSignUp);
        edtPassword = findViewById(R.id.edtPasswordSignUp);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private String checkGender() {
        int radioId = radioGroupSignUp.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        return radioButton.getText().toString();
    }

    private void dangKy(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Thành công")) {
                    Toast.makeText(SignUpScreen.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpScreen.this, SignInScreen.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpScreen.this, "Xảy ra lỗi" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", edtUsername.getText().toString().trim());
                params.put("email", edtEmail.getText().toString().trim());
                params.put("password", edtPassword.getText().toString().trim());
                params.put("phoneNumber", edtPhoneNumber.getText().toString().trim());
                params.put("firstName", edtFirstName.getText().toString().trim());
                params.put("lastName", edtLastName.getText().toString().trim());
                params.put("avatar", "http://192.168.1.14/MusicApp/Avatar/defaultAvatar.png");
                params.put("fullName", edtLastName.getText().toString().trim() + " " + edtFirstName.getText().toString().trim());
                params.put("gender", checkGender());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}