package com.travel.app.layouts.admin.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.travel.app.R;
import com.travel.app.model.Bus;
import com.travel.app.tools.SelectListener;

import java.util.HashMap;
import java.util.List;

public class GetBusesAdapter extends RecyclerView.Adapter<GetBusesViewHolder> {

    Context context;
    List<Bus> buses;
    HashMap<String, String> session;

    public GetBusesAdapter(Context context, List<Bus> buses, HashMap<String, String> session) {
        this.context = context;
        this.buses = buses;
        this.session = session;
    }

    @NonNull
    @Override
    public GetBusesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetBusesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_get_buses_adapter_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetBusesViewHolder holder, int position) {
        holder.getBuses_registrationNumber.setText(buses.get(position).getRegistrationNumber());
        holder.getBuses_sourceCity.setText(buses.get(position).getSourceCity());
        holder.getBuses_destinationCity.setText(buses.get(position).getDestinationCity());
        holder.getBuses_pricePerSeat.setText("Rs." + buses.get(position).getPricePerSeat() + "/seat");
        holder.getBuses_duration.setText(buses.get(position).getDuration());
        holder.getBuses_startTime.setText(buses.get(position).getStartTime());
        holder.getBuses_endTime.setText(buses.get(position).getEndTime());
        holder.getBuses_seatStatus.setText(buses.get(position).getOccupiedSeats().size()+"/"+buses.get(position).getMaxSeats()+" booked");
        holder.getBuses_busType.setText(buses.get(position).getBusType());
        holder.getBusesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bus bus = buses.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, BusDetails.class);
                session.put("bus", new Gson().toJson(bus));
                intent.putExtra("session", session);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buses.size();
    }
}