package com.example.mypro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;


public class loginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, btnGoToRegister;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);
        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (db.checkUser(email, password)) {
                SharedPreferences preferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("EMAIL", email);
                editor.apply();
                Toast.makeText(loginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginActivity.this, ProfileActivity.class));
                finish();
            } else {
                Toast.makeText(loginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoToRegister.setOnClickListener(view -> {
            startActivity(new Intent(loginActivity.this, RegisterActivity.class));
            finish();
        });
    }
}