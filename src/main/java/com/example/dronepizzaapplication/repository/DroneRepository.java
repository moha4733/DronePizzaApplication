package com.example.dronepizzaapplication.repository;

import com.example.dronepizzaapplication.entity.Drone;
import com.example.dronepizzaapplication.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone,Long> {
    // Custom query - find alle droner med en bestemt status
    List<Drone> findByStatus(String status);

    // Tæl hvor mange droner en station har
    long countByStation(Station station);
}
