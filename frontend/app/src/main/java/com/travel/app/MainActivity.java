package com.travel.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.travel.app.layouts.admin.AdminLogin;
import com.travel.app.layouts.user.UserLogin;
import com.travel.app.values.Values;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button userBtn, adminBtn, scanQRBtn, urlSubmitBtn;
    private CardView enterUrlCard;
    private TextInputEditText domainNameInput, portNumberInput;
    private TextView orSeparator;
    private String gatewayUrl;
    private HashMap<String, String> session = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Resource assignment
        userBtn = findViewById(R.id.userBtn);
        adminBtn = findViewById(R.id.adminBtn);
        scanQRBtn = findViewById(R.id.scanQRBtn);
        enterUrlCard = findViewById(R.id.enterUrlCard);
        orSeparator = findViewById(R.id.orSeparator);
        domainNameInput = findViewById(R.id.domainNameInput);
        portNumberInput = findViewById(R.id.portNumberInput);
        urlSubmitBtn = findViewById(R.id.urlSubmitBtn);

        //Setting initial visibility
        userBtn.setVisibility(View.VISIBLE);
        adminBtn.setVisibility(View.VISIBLE);
        scanQRBtn.setVisibility(View.GONE);
        enterUrlCard.setVisibility(View.GONE);
        orSeparator.setVisibility(View.GONE);

        if (Values.GATEWAY_URL.isEmpty()) {
            userBtn.setVisibility(View.GONE);
            adminBtn.setVisibility(View.GONE);
            scanQRBtn.setVisibility(View.VISIBLE);
            orSeparator.setVisibility(View.VISIBLE);
            enterUrlCard.setVisibility(View.VISIBLE);
        }

        //Setting Button onClickListener
        scanQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQR();
            }
        });

        urlSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String domainName = domainNameInput.getText().toString();
                String portNumber = portNumberInput.getText().toString();
                gatewayUrl = "http://" + domainName + ":" + portNumber;
                Values.GATEWAY_URL = gatewayUrl;
                session.put("gatewayUrl", gatewayUrl);
                if (domainName.isEmpty() && portNumber.isEmpty())
                    Toast.makeText(getApplicationContext(), "Domain Name and Port Number cannot be Empty !!!", Toast.LENGTH_SHORT).show();
                else {
                    userBtn.setVisibility(View.VISIBLE);
                    adminBtn.setVisibility(View.VISIBLE);
                    scanQRBtn.setVisibility(View.GONE);
                    enterUrlCard.setVisibility(View.GONE);
                    orSeparator.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), Values.GATEWAY_URL, Toast.LENGTH_SHORT).show();
                }
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                Toast.makeText(MainActivity.this, "Welcome to User Login", Toast.LENGTH_SHORT).show();
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                Toast.makeText(MainActivity.this, "Welcome to Admin Login", Toast.LENGTH_SHORT).show();
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Kept Empty to disable the back key functioning at home screen.
    }

    private void scanQR() {
        ScanOptions intentIntegrator = new ScanOptions();
        intentIntegrator.setOrientationLocked(false)
                .setBeepEnabled(true)
                .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                .setPrompt("Scan Server QR Code to get Server URL")
                .setTorchEnabled(false);
        startActivityIfNeeded(intentIntegrator.createScanIntent(getApplicationContext()), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            ScanIntentResult intentResult = ScanIntentResult.parseActivityResult(resultCode, data);
            gatewayUrl = intentResult.getContents();
            Values.GATEWAY_URL = gatewayUrl;
            if (!gatewayUrl.startsWith("Server "))
                Toast.makeText(this, "Invalid QR code content !!!", Toast.LENGTH_SHORT).show();
            else {
                userBtn.setVisibility(View.VISIBLE);
                adminBtn.setVisibility(View.VISIBLE);
                scanQRBtn.setVisibility(View.GONE);
                enterUrlCard.setVisibility(View.GONE);
                orSeparator.setVisibility(View.GONE);
                gatewayUrl = gatewayUrl.substring(7);
                session.put("gatewayUrl", gatewayUrl);
                Toast.makeText(this, gatewayUrl, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}