package com.travel.app.layouts.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.travel.app.R;

public class UserRegistration extends AppCompatActivity {

    private TextInputEditText registerUsernameInput, registerPasswordInput, registerConfirmPasswordInput;
    private Button registerUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration_page);

        //Initializing Resources
        registerUsernameInput = findViewById(R.id.registerUsernameInput);
        registerPasswordInput = findViewById(R.id.registerPasswordInput);
        registerConfirmPasswordInput = findViewById(R.id.registerConfirmPasswordInput);
        registerUserBtn = findViewById(R.id.registerUserBtn);

        //Setting On Click Listeners
        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkCredentials())
                    Toast.makeText(UserRegistration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                    Toast.makeText(UserRegistration.this, "User Registered", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkCredentials() {
        return !registerUsernameInput.getText().toString().isEmpty() &&
                !registerPasswordInput.getText().toString().isEmpty() &&
                !registerConfirmPasswordInput.getText().toString().isEmpty() &&
                registerPasswordInput.equals(registerConfirmPasswordInput);
    }
}