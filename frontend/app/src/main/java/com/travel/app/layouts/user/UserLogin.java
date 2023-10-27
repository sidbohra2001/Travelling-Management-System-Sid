package com.travel.app.layouts.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.travel.app.MainActivity;
import com.travel.app.R;

import java.util.HashMap;

public class UserLogin extends AppCompatActivity {

    private TextView registerHereBtn;
    private TextInputEditText userUsernameInputBox, userPasswordInputBox;
    private CardView card;
    private Button userLoginBtn;
    private String gatewayUrl;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_page);

        //Retrieving Intent Extras
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        gatewayUrl = session.get("gatewayUrl");

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
                session.put("username", userUsernameInputBox.getText().toString());
                intent.putExtra("session", session);
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