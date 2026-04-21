package com.example.dronepizzaapplication.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {

    @Id
    @GeneratedValue
    private Long id;
    private String serialNumber;
    private String status;

    public Drone(Long id , String serialNumber , String status){
        this.id = id;
        this.serialNumber = serialNumber;
        this.status = status;

    }

    public Drone(){}

    @ManyToOne
    private Station station;

    @OneToMany
    private List<Delivery> deliveries = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
