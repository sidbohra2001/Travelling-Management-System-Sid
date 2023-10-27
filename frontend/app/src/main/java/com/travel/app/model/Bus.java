package com.travel.app.model;

import java.util.List;

public class Bus {
    private String registrationNumber;
    private String busType;
    private String pricePerSeat;
    private String organizationName;
    private String driverName;
    private String conductorName;
    private String driverPhoneNumber;
    private String conductorPhoneNumber;
    private String sourceCity;
    private String startTime;
    private String destinationCity;
    private String endTime;
    private String duration;
    private int maxSeats;
    private String availabilityStatus;
    private List<Integer> occupiedSeats;

    @Override
    public String toString() {
        return "Bus{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", busType='" + busType + '\'' +
                ", pricePerSeat='" + pricePerSeat + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", driverName='" + driverName + '\'' +
                ", conductorName='" + conductorName + '\'' +
                ", driverPhoneNumber='" + driverPhoneNumber + '\'' +
                ", conductorPhoneNumber='" + conductorPhoneNumber + '\'' +
                ", sourceCity='" + sourceCity + '\'' +
                ", startTime='" + startTime + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration='" + duration + '\'' +
                ", maxSeats=" + maxSeats +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", occupiedSeats=" + occupiedSeats +
                '}';
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(String pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getConductorName() {
        return conductorName;
    }

    public void setConductorName(String conductorName) {
        this.conductorName = conductorName;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public String getConductorPhoneNumber() {
        return conductorPhoneNumber;
    }

    public void setConductorPhoneNumber(String conductorPhoneNumber) {
        this.conductorPhoneNumber = conductorPhoneNumber;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public List<Integer> getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(List<Integer> occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }
}
