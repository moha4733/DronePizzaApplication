package com.example.dronepizzaapplication.controller;


import com.example.dronepizzaapplication.entity.Delivery;
import com.example.dronepizzaapplication.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    // liste af alle leveringer der ikke er leveret
    @GetMapping
    public List<Delivery> getActiveDeliveries() {
        return deliveryService.findActiveDeliveries();
    }

    // liste af leveringer der mangler drone
    @GetMapping("/queue")
    public List<Delivery> getDeliveriesMissingDrone() {
        return deliveryService.findDeliveriesMissingDrone();
    }

    // opret ny levering
    @PostMapping("/add")
    public ResponseEntity<?> addDelivery(@RequestBody Map<String, Object> request) {
        try {
            Long pizzaId = Long.valueOf(request.get("pizzaId").toString());
            String address = request.get("address").toString();

            Delivery newDelivery = deliveryService.createDelivery(pizzaId, address);
            return ResponseEntity.ok(newDelivery);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //tildel drone til levering
    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleDelivery(@RequestParam Long deliveryId, @RequestParam Long droneId) {
        try {
            Delivery delivery = deliveryService.assignDrone(deliveryId, droneId);
            return ResponseEntity.ok(delivery);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // afslut levering
    @PostMapping("/finish")
    public ResponseEntity<?> finishDelivery(@RequestParam Long deliveryId) {
        try {
            Delivery delivery = deliveryService.finishDelivery(deliveryId);
            return ResponseEntity.ok(delivery);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
