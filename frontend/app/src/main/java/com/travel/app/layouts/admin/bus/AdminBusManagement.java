package com.travel.app.layouts.admin.bus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travel.app.R;
import com.travel.app.model.Bus;
import com.travel.app.model.ErrorFormat;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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

        updateBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBusManagement.this, UpdateBus.class);
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });

        getBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBusManagement.this, GetBus.class);
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });

        getBusesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String busesGetUrl = session.get("transportManagementUrl")+"/all";
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build();
                AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
                ANRequest request = AndroidNetworking.get(busesGetUrl)
                        .build();
                request.getAsObject(List.class, new ParsedRequestListener<List<Bus>>() {
                    @Override
                    public void onResponse(List<Bus> response) {
                        Intent intent = new Intent(AdminBusManagement.this, BusDetails.class);
                        session.put("buses", new GsonBuilder().create().toJson(response));
                        intent.putExtra("session", session);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        ErrorFormat error = new Gson().fromJson(anError.getErrorBody(), ErrorFormat.class);
                        Toast.makeText(AdminBusManagement.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("ERROR : ", error.getMessage());
                    }
                });
            }
        });

        deleteBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBusManagement.this, DeleteBus.class);
                intent.putExtra("session", session);
                startActivity(intent);
            }
        });
    }
}