package com.travel.app.layouts.admin.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class GetBus extends AppCompatActivity {

    private TextInputEditText getBus_registrationNumberTxt;
    private Button getBus_submitBtn;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bus_page);

        //Initialize Resources
        getBus_registrationNumberTxt = findViewById(R.id.getBus_registrationNumberTxt);
        getBus_submitBtn = findViewById(R.id.getBus_submitBtn);
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");

        //Setting Behaviours
        getBus_submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String busGetUrl = session.get("transportManagementUrl") + "/" + getBus_registrationNumberTxt.getText().toString();
                //Add bus get request
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build();
                AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
                ANRequest request = AndroidNetworking.get(busGetUrl)
                        .build();
                request.getAsObject(Bus.class, new ParsedRequestListener<Bus>() {
                    @Override
                    public void onResponse(Bus response) {
                        Intent intent = new Intent(GetBus.this, BusDetails.class);
                        session.put("bus", new GsonBuilder().create().toJson(response));
                        intent.putExtra("session", session);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        ErrorFormat error = new Gson().fromJson(anError.getErrorBody(), ErrorFormat.class);
                        Toast.makeText(GetBus.this, error.getMessage(), Toast.LENGTH_LONG).show();
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