package com.travel.app.layouts.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.travel.app.R;
import com.travel.app.layouts.admin.bus.AdminBusManagement;

import java.util.HashMap;

public class AdminDashboard extends AppCompatActivity {

    private HashMap<String, String> session;
    private String gatewayUrl, transportManagementUrl, bookingsManagementUrl;
    private TextView adminUserNameDisp;
    private CardView busManagementBtn, trainManagementBtn, flightManagementBtn, bookingsManagementBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard_page);

        //Initializing Resources
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        gatewayUrl = session.get("gatewayUrl");
        adminUserNameDisp = findViewById(R.id.adminUserNameDisp);
        busManagementBtn = findViewById(R.id.busManagementBtn);
        trainManagementBtn = findViewById(R.id.trainManagementBtn);
        flightManagementBtn = findViewById(R.id.flightManagementBtn);
        bookingsManagementBtn = findViewById(R.id.bookingsManagementBtn);
        Log.e("E:", gatewayUrl);

        //Setting behaviours
        adminUserNameDisp.setText(session.get("adminName"));
        busManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportManagementUrl = gatewayUrl + "/bus";
                session.put("transportManagementUrl", transportManagementUrl);
                Intent intent = new Intent(AdminDashboard.this, AdminBusManagement.class);
                intent.putExtra("session", session);
                Toast.makeText(AdminDashboard.this, "Welcome To Bus Management", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        trainManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportManagementUrl = gatewayUrl + "/train";
                Toast.makeText(AdminDashboard.this, "Welcome To Train Management", Toast.LENGTH_SHORT).show();
            }
        });

        flightManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportManagementUrl = gatewayUrl + "/flight";
                Toast.makeText(AdminDashboard.this, "Welcome To Flight Management", Toast.LENGTH_SHORT).show();
            }
        });

        bookingsManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingsManagementUrl = gatewayUrl + "/bookings";
                Toast.makeText(AdminDashboard.this, "Welcome To Bookings Management", Toast.LENGTH_SHORT).show();
            }
        });

    }
}