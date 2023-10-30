package com.travel.app.layouts.admin.bus;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;
import com.travel.app.R;
import com.travel.app.model.Bus;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class BusDetails extends AppCompatActivity {

    private HashMap<String, String> session;
    private TextView resp_busRegistrationNo, resp_busType, resp_busPrice, resp_busOrganization, resp_busDriverName, resp_busConductorName, resp_busDriverPhone, resp_busConductorPhone, resp_busSourceCity, resp_busStartTime, resp_busDestinationCity, resp_busEndTime, resp_busDuration, resp_busMaxSeats, resp_busAvailabilityStatus, resp_busOccupiedSeats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_page);

        //Initializing Resources
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        resp_busRegistrationNo = findViewById(R.id.resp_busRegistrationNo);
        resp_busType = findViewById(R.id.resp_busType);
        resp_busPrice = findViewById(R.id.resp_busPrice);
        resp_busOrganization = findViewById(R.id.resp_busOrganization);
        resp_busDriverName = findViewById(R.id.resp_busDriverName);
        resp_busConductorName = findViewById(R.id.resp_busConductorName);
        resp_busDriverPhone = findViewById(R.id.resp_busDriverPhone);
        resp_busConductorPhone = findViewById(R.id.resp_busConductorPhone);
        resp_busSourceCity = findViewById(R.id.resp_busSourceCity);
        resp_busStartTime = findViewById(R.id.resp_busStartTime);
        resp_busDestinationCity = findViewById(R.id.resp_busDestinationCity);
        resp_busEndTime = findViewById(R.id.resp_busEndTime);
        resp_busDuration = findViewById(R.id.resp_busDuration);
        resp_busMaxSeats = findViewById(R.id.resp_busMaxSeats);
        resp_busAvailabilityStatus = findViewById(R.id.resp_busAvailabilityStatus);
        resp_busOccupiedSeats = findViewById(R.id.resp_busOccupiedSeats);

        //Emitting values in fields
        Bus bus = new Gson().fromJson(session.get("bus"), Bus.class);
        resp_busRegistrationNo.setText(bus.getRegistrationNumber());
        resp_busType.setText(bus.getBusType());
        resp_busPrice.setText(bus.getPricePerSeat());
        resp_busOrganization.setText(bus.getOrganizationName());
        resp_busDriverName.setText(bus.getDriverName());
        resp_busConductorName.setText(bus.getConductorName());
        resp_busDriverPhone.setText(bus.getDriverPhoneNumber());
        resp_busConductorPhone.setText(bus.getConductorPhoneNumber());
        resp_busSourceCity.setText(bus.getSourceCity());
        resp_busStartTime.setText(bus.getStartTime());
        resp_busDestinationCity.setText(bus.getDestinationCity());
        resp_busEndTime.setText(bus.getEndTime());
        resp_busDuration.setText(bus.getDuration());
        resp_busMaxSeats.setText(String.valueOf(bus.getMaxSeats()));
        resp_busAvailabilityStatus.setText(bus.getAvailabilityStatus());
        resp_busOccupiedSeats.setText(bus.getOccupiedSeats().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        session.remove("bus");
    }
}