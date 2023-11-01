package com.travel.app.layouts.admin.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.travel.app.R;
import com.travel.app.model.Bus;

import java.util.ArrayList;
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
        List<Bus> buses = new Gson().fromJson(session.get("buses"), new TypeToken<List<Bus>>() {}.getType());

        //Setting view on Recycler View
        busesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        busesRecyclerView.setAdapter(new GetBusesAdapter(getApplicationContext(), buses, session));

    }
}