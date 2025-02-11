package com.example.mypro;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;


public class RegisterActivity extends AppCompatActivity {
    EditText etUsername, etEmail, etPassword, etFname;
    Button btnRegister, btnGoToLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFname = findViewById(R.id.etFname);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoToLogin = findViewById(R.id.btnGoToLogin);
        db = new DatabaseHelper(this);

        btnRegister.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String fname = etFname.getText().toString();

            if (db.insertUser(username, email, password, fname)) {
                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, loginActivity.class));
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoToLogin.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, loginActivity.class));
            finish();
        });
    }
}