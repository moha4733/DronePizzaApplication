package com.example.dronepizzaapplication.service;

import com.example.dronepizzaapplication.entity.Delivery;
import com.example.dronepizzaapplication.entity.Drone;
import com.example.dronepizzaapplication.entity.Pizza;
import com.example.dronepizzaapplication.repository.DeliveryRepository;
import com.example.dronepizzaapplication.repository.DroneRepository;
import com.example.dronepizzaapplication.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DroneRepository droneRepository;
    private final PizzaRepository pizzaRepository;

    public DeliveryService(DeliveryRepository deliveryRepository , DroneRepository droneRepository ,
                           PizzaRepository pizzaRepository){
        this.deliveryRepository = deliveryRepository;
        this.droneRepository = droneRepository;
        this.pizzaRepository = pizzaRepository;
    }

    // Hent alle leveringer der ikke er afsluttet
    public List<Delivery> findActiveDeliveries(){
        return deliveryRepository.findByActualTimeIsNull();
    }

    // Hent leveringer der mangler drone
    public List<Delivery> findDeliveriesMissingDrone() {
        return deliveryRepository.findByDroneIsNullAndActualTimeIsNull();
    }

    // Opret ny levering
    public Delivery createDelivery(Long pizzaId, String address) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new RuntimeException("Pizza ikke fundet"));

        Delivery delivery = new Delivery();
        delivery.setPizza(pizza);
        delivery.setAdresse(address);
        delivery.setExceptedTime(LocalDateTime.now().plusMinutes(30));
        delivery.setActualTime(null);
        delivery.setDrone(null);

        return deliveryRepository.save(delivery);
    }

    // Tildel drone til levering
    public Delivery assignDrone(Long deliveryId, Long droneId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Levering ikke fundet"));

        // Tjek om levering allerede har en drone
        if (delivery.getDrone() != null) {
            throw new RuntimeException("Levering har allerede en drone");
        }

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new RuntimeException("Drone ikke fundet"));

        // Tjek om dronen er i drift
        if (!"i drift".equals(drone.getStatus())) {
            throw new RuntimeException("Dronen er ikke i drift");
        }

        delivery.setDrone(drone);
        return deliveryRepository.save(delivery);
    }

    // Afslut levering
    public Delivery finishDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Levering ikke fundet"));

        // Tjek om levering har en drone
        if (delivery.getDrone() == null) {
            throw new RuntimeException("Levering har ingen drone");
        }

        delivery.setActualTime(LocalDateTime.now());
        return deliveryRepository.save(delivery);
    }


}
