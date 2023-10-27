package com.travel.app.layouts.admin.bus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travel.app.R;
import com.travel.app.model.Bus;

import org.json.JSONObject;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class AddBus extends AppCompatActivity {

    private TextInputEditText addBus_busRegistrationNumber, addBus_busPricePerSeat, addBus_busOrganizationName, addBus_busDriverName, addBus_busConductorName,
            addBus_busDriverPhoneNumber, addBus_busConductorPhoneNumber, addBus_busSourceCity, addBus_busDestinationCity;
    private TimePicker addBus_startTimePicker, addBus_endTimePicker;
    private RadioGroup busTypeRadioGrp;
    private RadioButton sittingRadioBtn, sleeperRadioBtn;
    private Bus bus;
    private Button addBus_submitBtn;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_page);

        //Intializing Resources
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        busTypeRadioGrp = findViewById(R.id.busTypeRadioGrp);
        sittingRadioBtn = findViewById(R.id.sittingRadioBtn);
        sleeperRadioBtn = findViewById(R.id.sleeperRadioBtn);
        addBus_busRegistrationNumber = findViewById(R.id.addBus_busRegistrationNumber);
        addBus_busPricePerSeat = findViewById(R.id.addBus_busPricePerSeat);
        addBus_busOrganizationName = findViewById(R.id.addBus_busOrganizationName);
        addBus_busDriverName = findViewById(R.id.addBus_busDriverName);
        addBus_busConductorName = findViewById(R.id.addBus_busConductorName);
        addBus_busDriverPhoneNumber = findViewById(R.id.addBus_busDriverPhoneNumber);
        addBus_busConductorPhoneNumber = findViewById(R.id.addBus_busConductorPhoneNumber);
        addBus_busSourceCity = findViewById(R.id.addBus_busSourceCity);
        addBus_busDestinationCity = findViewById(R.id.addBus_busDestinationCity);
        addBus_startTimePicker = findViewById(R.id.addBus_startTimePicker);
        addBus_endTimePicker = findViewById(R.id.addBus_endTimePicker);
        addBus_submitBtn = findViewById(R.id.addBus_submitBtn);

        //Setting behaviour
        sittingRadioBtn.setOnClickListener(isChecked -> Toast.makeText(this, sittingRadioBtn.getText().toString(), Toast.LENGTH_SHORT).show());
        sleeperRadioBtn.setOnClickListener(isChecked -> Toast.makeText(this, sleeperRadioBtn.getText().toString(), Toast.LENGTH_SHORT).show());
        addBus_startTimePicker.setIs24HourView(true);
        addBus_endTimePicker.setIs24HourView(true);
        addBus_startTimePicker.setHour(0);
        addBus_startTimePicker.setMinute(0);
        addBus_endTimePicker.setHour(0);
        addBus_endTimePicker.setMinute(0);

        //Setting onClickListeners
        addBus_submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Adding Data to Bus object.
                bus = new Bus();
                bus.setRegistrationNumber(addBus_busRegistrationNumber.getText().toString());
                bus.setBusType(busTypeRadioGrp.getCheckedRadioButtonId() == R.id.sleeperRadioBtn ? "SLEEPER" : "SITTING");
                bus.setPricePerSeat(addBus_busPricePerSeat.getText().toString());
                bus.setOrganizationName(addBus_busOrganizationName.getText().toString());
                bus.setDriverName(addBus_busDriverName.getText().toString());
                bus.setConductorName(addBus_busConductorName.getText().toString());
                bus.setDriverPhoneNumber(addBus_busDriverPhoneNumber.getText().toString());
                bus.setConductorPhoneNumber(addBus_busConductorPhoneNumber.getText().toString());
                bus.setSourceCity(addBus_busSourceCity.getText().toString());
                bus.setDestinationCity(addBus_busDestinationCity.getText().toString());
                bus.setStartTime(String.valueOf(LocalTime.of(addBus_startTimePicker.getHour(),addBus_startTimePicker.getMinute())).replace(":",""));
                bus.setEndTime(String.valueOf(LocalTime.of(addBus_endTimePicker.getHour(),addBus_endTimePicker.getMinute())).replace(":",""));
                String busAddUrl = session.get("transportManagementUrl") + "/";

                //Add bus post request
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build();
                AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
                ANRequest request = AndroidNetworking.post(busAddUrl)
                        .addApplicationJsonBody(bus)
                        .build();
                request.getAsObject(Bus.class, new ParsedRequestListener<Bus>() {
                    @Override
                    public void onResponse(Bus response) {
                        Intent intent = new Intent(AddBus.this, BusDetails.class);
                        session.put("bus", new GsonBuilder().create().toJson(response));
                        intent.putExtra("session", session);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AddBus.this, new Gson().fromJson(anError.getErrorBody(), String.class), Toast.LENGTH_SHORT).show();
                        Log.e("ERROR : ", new Gson().fromJson(anError.getErrorBody(), String.class));
                    }
                });
                //Building Intent

            }
        });
    }
}