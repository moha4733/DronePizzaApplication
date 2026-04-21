package com.example.dronepizzaapplication.service;

import com.example.dronepizzaapplication.entity.Station;
import com.example.dronepizzaapplication.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository){
                this.stationRepository = stationRepository;
    }

    List<Station> findAll(){
        return stationRepository.findAll();
    }

    public Station findById(Long id){
        return stationRepository.findById(id).orElse(null);
    }
}
