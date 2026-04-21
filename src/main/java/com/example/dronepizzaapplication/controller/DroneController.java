package com.example.dronepizzaapplication.controller;

import com.example.dronepizzaapplication.entity.Drone;
import com.example.dronepizzaapplication.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService){
        this.droneService= droneService;
    }

    //liste af alle droner
    @GetMapping
    public List<Drone> getAllDrones(){
       return droneService.findAll();
    }

    //opret ny drone
    @PostMapping("/add")
    public ResponseEntity<?> addDrone(){
        try {
            Drone newDrone = droneService.createDrone();
            return ResponseEntity.ok(newDrone);
        }   catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //sæt drone til "i drift"
    @PostMapping("/enable")
    public ResponseEntity<?> enableDrone(@RequestParam Long droneId){
        try {
            Drone drone = droneService.changeStatus(droneId," i drift");
            return ResponseEntity.ok(drone);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   // sæt drone til "ude af drift"
    @PostMapping("/disable")
    public ResponseEntity<?> disableDrone(@RequestParam Long droneId ){
        try {
            Drone disableDrone = droneService.changeStatus(droneId, "ude af drift");
            return ResponseEntity.ok(disableDrone);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("retire")
    public ResponseEntity<?> retireDrone(@RequestParam Long droneId){
        try {
            Drone retireDrone = droneService.changeStatus(droneId, "udfastet");
            return ResponseEntity.ok(retireDrone);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
