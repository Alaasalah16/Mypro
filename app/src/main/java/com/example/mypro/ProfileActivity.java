package com.example.mypro;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    TextView tvWelcome;
    Button btnLogout;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        db = new DatabaseHelper(this);

        SharedPreferences preferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        String email = preferences.getString("EMAIL", "");

        if (email != null) {
            String fname = db.getUserFullName(email);
            tvWelcome.setText("Welcome, " + fname);
        }

        btnLogout.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(ProfileActivity.this, loginActivity.class));
            finish();
        });
    }
}