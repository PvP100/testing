package com.example.dictionaryapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.StringUltil.StringURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInScreen extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSignIn;
    TextView txtvSignUpClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        mapping();
    }

    private void mapping() {
        txtvSignUpClick = findViewById(R.id.txtvSignUp);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        btnSignIn = findViewById(R.id.btnSignIn);
    }

    public void txtvSignUpClick(View view) {
        startActivity(new Intent(SignInScreen.this, SignUpScreen.class));
    }

    public void signIn(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, StringURL.readClientData, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Boolean check = false;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String username = jsonObject.getString("username");
                        String password = jsonObject.getString("password");
                        
                        if (username.equalsIgnoreCase(edtUsername.getText().toString().trim()) && password.equals(edtPassword.getText().toString().trim())) {
                            check = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (check == true) {
                    Toast.makeText(SignInScreen.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInScreen.this, MainScreen.class));
                } else {
                    Toast.makeText(SignInScreen.this, "Tên đăng nhập hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}