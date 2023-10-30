package com.travel.app.layouts.admin.bus;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travel.app.R;

public class GetBusesViewHolder extends RecyclerView.ViewHolder{
    TextView getBuses_registrationNumber, getBuses_sourceCity, getBuses_destinationCity, getBuses_pricePerSeat, getBuses_duration, getBuses_startTime,
            getBuses_endTime, getBuses_seatStatus, getBuses_busType;

    public GetBusesViewHolder(@NonNull View itemView) {
        super(itemView);
        getBuses_registrationNumber = itemView.findViewById(R.id.getBuses_registrationNumber);
        getBuses_sourceCity = itemView.findViewById(R.id.getBuses_sourceCity);
        getBuses_destinationCity = itemView.findViewById(R.id.getBuses_destinationCity);
        getBuses_pricePerSeat = itemView.findViewById(R.id.getBuses_pricePerSeat);
        getBuses_duration = itemView.findViewById(R.id.getBuses_duration);
        getBuses_startTime = itemView.findViewById(R.id.getBuses_startTime);
        getBuses_endTime = itemView.findViewById(R.id.getBuses_endTime);
        getBuses_seatStatus = itemView.findViewById(R.id.getBuses_seatStatus);
        getBuses_busType = itemView.findViewById(R.id.getBuses_busType);
    }
}
