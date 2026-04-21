package com.example.dronepizzaapplication.controller;


import com.example.dronepizzaapplication.entity.Station;
import com.example.dronepizzaapplication.repository.StationRepository;
import com.example.dronepizzaapplication.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationRepository stationRepository;

    public StationController(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
}
