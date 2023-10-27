package com.travel.app.layouts.admin.bus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.travel.app.R;

import java.util.HashMap;

public class AdminBusManagement extends AppCompatActivity {

    private Button addBusBtn, updateBusBtn, getBusBtn, getBusesBtn, deleteBusBtn;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_bus_management_page);

        //Initiating Resources
        addBusBtn = findViewById(R.id.addBusBtn);
        updateBusBtn = findViewById(R.id.updateBusBtn);
        getBusBtn = findViewById(R.id.getBusBtn);
        getBusesBtn = findViewById(R.id.getBusesBtn);
        deleteBusBtn = findViewById(R.id.deleteBusBtn);
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");

        //Setting onClickListener
        addBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBusManagement.this, AddBus.class);
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });
    }
}