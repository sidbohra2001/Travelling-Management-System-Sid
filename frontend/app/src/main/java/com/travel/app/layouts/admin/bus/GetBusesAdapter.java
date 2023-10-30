package com.travel.app.layouts.admin.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travel.app.R;
import com.travel.app.model.Bus;

import java.util.List;

public class GetBusesAdapter extends RecyclerView.Adapter<GetBusesViewHolder> {

    Context context;
    List<Bus> buses;

    public GetBusesAdapter(Context context, List<Bus> buses) {
        this.context = context;
        this.buses = buses;
    }

    @NonNull
    @Override
    public GetBusesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetBusesViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_get_buses_adapter_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetBusesViewHolder holder, int position) {
        holder.getBuses_registrationNumber.setText(buses.get(position).getRegistrationNumber());
        holder.getBuses_sourceCity.setText(buses.get(position).getSourceCity());
        holder.getBuses_destinationCity.setText(buses.get(position).getDestinationCity());
        holder.getBuses_pricePerSeat.setText(buses.get(position).getPricePerSeat());
        holder.getBuses_duration.setText(buses.get(position).getDuration());
        holder.getBuses_startTime.setText(buses.get(position).getStartTime());
        holder.getBuses_endTime.setText(buses.get(position).getEndTime());
        holder.getBuses_seatStatus.setText(String.valueOf(buses.get(position).getMaxSeats()));
        holder.getBuses_busType.setText(buses.get(position).getBusType());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}