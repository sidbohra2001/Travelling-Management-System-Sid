package com.travel.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.travel.app.layouts.AdminLogin;
import com.travel.app.layouts.UserLogin;

public class MainActivity extends AppCompatActivity {

    private Button userBtn, adminBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userBtn = findViewById(R.id.userBtn);
        adminBtn = findViewById(R.id.adminBtn);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                Toast.makeText(MainActivity.this, "Welcome to User Login "+5, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                Toast.makeText(MainActivity.this, "Welcome to Admin Login", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}