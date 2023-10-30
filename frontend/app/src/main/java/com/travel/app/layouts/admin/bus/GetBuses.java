package com.travel.app.layouts.admin.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.travel.app.R;
import com.travel.app.model.Bus;

import java.util.HashMap;
import java.util.List;

public class GetBuses extends AppCompatActivity {

    private RecyclerView busesRecyclerView;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_buses_page);

        //Initializing Resources
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        busesRecyclerView = findViewById(R.id.busesRecyclerView);

        //Fetching Bus List from Session
        List<Bus> buses = (List<Bus>) new Gson().fromJson(session.get("buses"), List.class);

        //Setting view on Recycler View
        busesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        busesRecyclerView.setAdapter(new GetBusesAdapter(this, buses));
    }
}