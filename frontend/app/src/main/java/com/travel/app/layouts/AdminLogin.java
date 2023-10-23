package com.travel.app.layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.travel.app.MainActivity;
import com.travel.app.R;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class AdminLogin extends AppCompatActivity {

    private Button adminLoginBtn;
    private HashMap<String, String> session;
    private String gatewayUrl;
    private TextInputEditText adminUsernameInputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        //Initializing Resources
        adminLoginBtn = findViewById(R.id.adminLoginBtn);
        adminUsernameInputBox = findViewById(R.id.adminUsernameInputBox);
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        gatewayUrl = session.get("gatewayUrl");

        //Setting onClickListeners
        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Authenticate Admin from Server
                biometricLogin();
            }
        });
    }

    private void biometricLogin() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            BiometricManager manager = BiometricManager.from(this);
            if (!checkBiometric(manager)) return;
            Executor executor = ContextCompat.getMainExecutor(this);
            BiometricPrompt.PromptInfo promptInfo = buildBiometricPromptInfo();
            BiometricPrompt prompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(AdminLogin.this, errString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(AdminLogin.this, "Biometric Authentication Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                    session.put("adminName", adminUsernameInputBox.getText().toString());
                    intent.putExtra("session", session);
                    startActivity(intent);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(AdminLogin.this, "Biometric Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            });
            prompt.authenticate(promptInfo);
        }
    }

    private boolean checkBiometric(BiometricManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Toast.makeText(this, "checkBiometric", Toast.LENGTH_SHORT).show();
            return manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS;
        }
        return false;
    }

    private BiometricPrompt.PromptInfo buildBiometricPromptInfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return new BiometricPrompt.PromptInfo.Builder()
                    .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                    .setTitle("Biometric Login")
                    .setSubtitle("Login through your Finger Print")
                    .setDescription("Press your fingerprint against the fingerprint sensor to authenticate.")
                    .setConfirmationRequired(false)
                    .build();
        }
        return null;
    }
}

