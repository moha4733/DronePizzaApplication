package com.example.dronepizzaapplication.service;

import com.example.dronepizzaapplication.entity.Drone;
import com.example.dronepizzaapplication.entity.Station;
import com.example.dronepizzaapplication.repository.DroneRepository;
import com.example.dronepizzaapplication.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final StationRepository stationRepository;


    public DroneService(DroneRepository droneRepository, StationRepository stationRepository){
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }

    public List<Drone> findAll(){
        return droneRepository.findAll();
    }

    public Drone createDrone(){
         //     // Find station med færrest droner
        List<Station> stations = stationRepository.findAll();

       if (stations.isEmpty()) {
           throw new RuntimeException("ingen stationer i databasen");

       }

       // Find station med færrest droner
        Station bestStation = stations.stream()
                .min(Comparator.comparing(station -> droneRepository.countByStation(station)))
                .orElseThrow(() -> new RuntimeException("kunne ikke finde station"));

           // Opret ny drone
       Drone drone = new Drone();
       drone.setSerialNumber(UUID.randomUUID().toString());
       drone.setStatus("i drift");
       drone.setStation(bestStation);

       return droneRepository.save(drone);
        
    }
     // Skift status på drone
    public Drone changeStatus(Long droneId, String newStatus){
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new RuntimeException("drone ikke fundet"));

        drone.setStatus(newStatus);
        return droneRepository.save(drone);
    }

}
