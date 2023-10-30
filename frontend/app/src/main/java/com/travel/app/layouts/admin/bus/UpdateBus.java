package com.travel.app.layouts.admin.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travel.app.R;
import com.travel.app.model.Bus;
import com.travel.app.model.ErrorFormat;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class UpdateBus extends AppCompatActivity {

    private TextInputEditText updateBus_busRegistrationNumber, updateBus_busPricePerSeat, updateBus_busOrganizationName, updateBus_busDriverName, updateBus_busConductorName,
            updateBus_busDriverPhoneNumber, updateBus_busConductorPhoneNumber, updateBus_busSourceCity, updateBus_busDestinationCity;
    private TimePicker updateBus_startTimePicker, updateBus_endTimePicker;
    private RadioGroup busTypeRadioGrp;
    private RadioButton sittingRadioBtn, sleeperRadioBtn;
    private Bus bus;
    private Button updateBus_submitBtn;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bus_page);

        //Intializing Resources
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        busTypeRadioGrp = findViewById(R.id.busTypeRadioGrp);
        sittingRadioBtn = findViewById(R.id.sittingRadioBtn);
        sleeperRadioBtn = findViewById(R.id.sleeperRadioBtn);
        updateBus_busRegistrationNumber = findViewById(R.id.updateBus_busRegistrationNumber);
        updateBus_busPricePerSeat = findViewById(R.id.updateBus_busPricePerSeat);
        updateBus_busOrganizationName = findViewById(R.id.updateBus_busOrganizationName);
        updateBus_busDriverName = findViewById(R.id.updateBus_busDriverName);
        updateBus_busConductorName = findViewById(R.id.updateBus_busConductorName);
        updateBus_busDriverPhoneNumber = findViewById(R.id.updateBus_busDriverPhoneNumber);
        updateBus_busConductorPhoneNumber = findViewById(R.id.updateBus_busConductorPhoneNumber);
        updateBus_busSourceCity = findViewById(R.id.updateBus_busSourceCity);
        updateBus_busDestinationCity = findViewById(R.id.updateBus_busDestinationCity);
        updateBus_startTimePicker = findViewById(R.id.updateBus_startTimePicker);
        updateBus_endTimePicker = findViewById(R.id.updateBus_endTimePicker);
        updateBus_submitBtn = findViewById(R.id.updateBus_submitBtn);

        //Setting behaviour
        sittingRadioBtn.setOnClickListener(isChecked -> Toast.makeText(this, sittingRadioBtn.getText().toString(), Toast.LENGTH_SHORT).show());
        sleeperRadioBtn.setOnClickListener(isChecked -> Toast.makeText(this, sleeperRadioBtn.getText().toString(), Toast.LENGTH_SHORT).show());
        updateBus_startTimePicker.setIs24HourView(true);
        updateBus_endTimePicker.setIs24HourView(true);
        updateBus_startTimePicker.setHour(0);
        updateBus_startTimePicker.setMinute(0);
        updateBus_endTimePicker.setHour(0);
        updateBus_endTimePicker.setMinute(0);

        //Setting onClickListeners
        updateBus_submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Adding Data to Bus object.
                bus = new Bus();
                bus.setRegistrationNumber(updateBus_busRegistrationNumber.getText().toString());
                bus.setBusType(busTypeRadioGrp.getCheckedRadioButtonId() == R.id.sleeperRadioBtn ? "SLEEPER" : "SITTING");
                bus.setPricePerSeat(updateBus_busPricePerSeat.getText().toString());
                bus.setOrganizationName(updateBus_busOrganizationName.getText().toString());
                bus.setDriverName(updateBus_busDriverName.getText().toString());
                bus.setConductorName(updateBus_busConductorName.getText().toString());
                bus.setDriverPhoneNumber(updateBus_busDriverPhoneNumber.getText().toString());
                bus.setConductorPhoneNumber(updateBus_busConductorPhoneNumber.getText().toString());
                bus.setSourceCity(updateBus_busSourceCity.getText().toString());
                bus.setDestinationCity(updateBus_busDestinationCity.getText().toString());
                bus.setStartTime(String.valueOf(LocalTime.of(updateBus_startTimePicker.getHour(),updateBus_startTimePicker.getMinute())).replace(":",""));
                bus.setEndTime(String.valueOf(LocalTime.of(updateBus_endTimePicker.getHour(),updateBus_endTimePicker.getMinute())).replace(":",""));
                String busAddUrl = session.get("transportManagementUrl") + "/";

                //Add bus post request
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build();
                AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
                ANRequest request = AndroidNetworking.put(busAddUrl)
                        .addApplicationJsonBody(bus)
                        .build();
                request.getAsObject(Bus.class, new ParsedRequestListener<Bus>() {
                    @Override
                    public void onResponse(Bus response) {
                        Intent intent = new Intent(UpdateBus.this, BusDetails.class);
                        session.put("bus", new GsonBuilder().create().toJson(response));
                        intent.putExtra("session", session);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        ErrorFormat error = new Gson().fromJson(anError.getErrorBody(), ErrorFormat.class);
                        Toast.makeText(UpdateBus.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("ERROR : ", error.getMessage());
                    }
                });

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        session.remove("transportManagementUrl");
    }

}