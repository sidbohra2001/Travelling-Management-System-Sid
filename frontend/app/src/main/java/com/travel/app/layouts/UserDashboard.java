package com.travel.app.layouts;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.travel.app.R;
import com.travel.app.model.Bus;
import com.travel.app.values.Values;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends AppCompatActivity {

    private TextView serverResponse;
    private RestTemplate rest = new RestTemplate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_page);

        serverResponse = findViewById(R.id.serverResponse);

        String response = rest.exchange(Values.BUS_URL+"/hello", HttpMethod.GET, null, String.class).getBody();
        serverResponse.setText(response);
    }
}