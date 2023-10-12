package com.travel.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.travel.app.layouts.UserLogin;

public class MainActivity extends AppCompatActivity {

    private Button userLoginBtn, adminLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLoginBtn = findViewById(R.id.userLoginBtn);
        adminLoginBtn = findViewById(R.id.adminLoginBtn);
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
            }
        });
    }
}