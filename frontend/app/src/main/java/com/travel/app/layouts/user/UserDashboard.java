package com.travel.app.layouts.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.travel.app.R;

import java.util.HashMap;

public class UserDashboard extends AppCompatActivity {

    private String gatewayUrl, username, transportUrl, bookingUrl;
    private TextView userUserNameDisp;
    private CardView busServiceBtn, trainServiceBtn, flightServiceBtn, showBookingsBtn;
    private HashMap<String, String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_page);

        //Initializing Resources
        session = (HashMap<String, String>) getIntent().getSerializableExtra("session");
        userUserNameDisp = findViewById(R.id.userUserNameDisp);
        busServiceBtn = findViewById(R.id.busServiceBtn);
        trainServiceBtn = findViewById(R.id.trainServiceBtn);
        flightServiceBtn = findViewById(R.id.flightServiceBtn);
        showBookingsBtn = findViewById(R.id.showBookingsBtn);
        username = session.get("username");
        gatewayUrl = session.get("gatewayUrl");
        bookingUrl = gatewayUrl + "/booking";

        //Setting behaviours
        userUserNameDisp.setText(username);
        Toast.makeText(this, username + " " + gatewayUrl, Toast.LENGTH_SHORT).show();
        busServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportUrl = gatewayUrl + "/bus";
                Toast.makeText(UserDashboard.this, "Welcome To Bus Services", Toast.LENGTH_SHORT).show();
            }
        });

        trainServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportUrl = gatewayUrl + "/train";
                Toast.makeText(UserDashboard.this, "Welcome To Train Services", Toast.LENGTH_SHORT).show();
            }
        });

        flightServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportUrl = gatewayUrl + "/flight";
                Toast.makeText(UserDashboard.this, "Welcome To Flight Services", Toast.LENGTH_SHORT).show();
            }
        });

        showBookingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingUrl = gatewayUrl + "/bookings";
                Toast.makeText(UserDashboard.this, "Welcome To your Bookings", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    public void extra(){
//        serverResponse = findViewById(R.id.serverResponse);
//        gatewayUrl = getIntent().getExtras().getString("gatewayUrl");
//        //Sample
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest request = new StringRequest(
//                Request.Method.GET,
//                gatewayUrl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        serverResponse.setText(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        serverResponse.setText(error.getMessage());
//                    }
//                }
//        );
//        try {
//            JsonObjectRequest postBus = new JsonObjectRequest(
//                    Request.Method.POST,
//                    gatewayUrl,
//                    new JSONObject("""
//                            {
//                                "registrationNumber": "TR45WH7573",
//                                "busType": "SITTING",
//                                "pricePerSeat": "284",
//                                "organizationName": "Capgemini",
//                                "driverName": "Surang Bandre",
//                                "conductorName": "Peshab Singh",
//                                "driverPhoneNumber": "+10512093934",
//                                "conductorPhoneNumber": "+29184697553",
//                                "sourceCity": "Pune",
//                                "startTime": "0105",
//                                "destinationCity": "Bangalore",
//                                "endTime": "1212"
//                            }
//                            """),
//                    new Response.Listener<JSONObject>(){
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            serverResponse.setText(response.toString());
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            serverResponse.setText(error.getMessage());
//                        }
//                    });
//            queue.add(postBus);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
};