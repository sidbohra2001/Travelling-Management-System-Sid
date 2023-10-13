package com.travel.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.travel.app.layouts.AdminLogin;
import com.travel.app.layouts.UserLogin;

public class MainActivity extends AppCompatActivity {

    private Button userBtn, adminBtn, scanQRBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Resource assignment
        userBtn = findViewById(R.id.userBtn);
        adminBtn = findViewById(R.id.adminBtn);
        scanQRBtn = findViewById(R.id.scanQRBtn);

        //Setting initial visibility
        userBtn.setVisibility(View.GONE);
        adminBtn.setVisibility(View.GONE);
        scanQRBtn.setVisibility(View.VISIBLE);

        //Setting Button onClickListener
        scanQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQR();
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                Toast.makeText(MainActivity.this, "Welcome to User Login", Toast.LENGTH_SHORT).show();
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

    private void scanQR() {
        ScanOptions intentIntegrator = new ScanOptions();
        intentIntegrator.setOrientationLocked(true)
                .setBeepEnabled(true)
                .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                .setPrompt("Scan Server QR Code to get Server URL")
                .setTorchEnabled(false);
        startActivityIfNeeded(intentIntegrator.createScanIntent(getApplicationContext()), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            ScanIntentResult intentResult = ScanIntentResult.parseActivityResult(resultCode, data);
            String url = intentResult.getContents();
            if(!url.startsWith("Server ")) Toast.makeText(this, "Invalid QR code content !!!", Toast.LENGTH_SHORT).show();
            else{
                userBtn.setVisibility(View.VISIBLE);
                adminBtn.setVisibility(View.VISIBLE);
                scanQRBtn.setVisibility(View.GONE);
                url = url.substring(7);
                Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}