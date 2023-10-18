package com.travel.app.layouts;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.travel.app.MainActivity;
import com.travel.app.R;

import java.util.concurrent.Executor;

public class UserLogin extends AppCompatActivity {

    private TextView registerHereBtn;
    private TextInputEditText userUsernameInputBox, userPasswordInputBox;
    private CardView card;
    private Button userLoginBtn;
    private String gatewayUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_page);

        //Retrieving Intent Extras
        gatewayUrl = getIntent().getExtras().getString("gatewayUrl");

        //Initializing Resources
        registerHereBtn = findViewById(R.id.registerHereBtn);
        userUsernameInputBox = findViewById(R.id.userUsernameInputBox);
        userPasswordInputBox = findViewById(R.id.userPasswordInputBox);
        userLoginBtn = findViewById(R.id.userLoginBtn);

        //Setting On Click Listener
        registerHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserRegistration.class);
                startActivity(intent);
            }
        });

        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : authenticate user

                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                intent.putExtra("username", userUsernameInputBox.getText().toString());
                intent.putExtra("gatewayUrl", gatewayUrl);
                Toast.makeText(UserLogin.this, gatewayUrl, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}