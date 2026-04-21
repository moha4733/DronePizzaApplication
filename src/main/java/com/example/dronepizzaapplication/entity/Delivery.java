package com.example.dronepizzaapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private LocalDateTime exceptedTime;
    private LocalDateTime actualTime;


    public Delivery(Long id , String adresse , LocalDateTime exceptedTime , LocalDateTime actualTime){
        this.id = id;
        this.adresse = adresse;
        this.exceptedTime = exceptedTime;
        this.actualTime= actualTime;
    }


    public Delivery(){}


    @ManyToOne
    private Pizza pizza;


    @ManyToOne
    @JsonBackReference
    private Drone drone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDateTime getExceptedTime() {
        return exceptedTime;
    }

    public void setExceptedTime(LocalDateTime exceptedTime) {
        this.exceptedTime = exceptedTime;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
