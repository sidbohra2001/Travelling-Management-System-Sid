package com.travel.app.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.travel.app.R;
import com.travel.app.model.Bus;
import com.travel.app.values.Values;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserDashboard extends AppCompatActivity {

    private TextView serverResponse;
    private RestTemplate rest = new RestTemplate();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_page);

        serverResponse = findViewById(R.id.serverResponse);

        List<Bus> buses = rest.exchange(Values.BUS_URL+"/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Bus>>(){}).getBody();

        serverResponse.setText(buses.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());
    }
}