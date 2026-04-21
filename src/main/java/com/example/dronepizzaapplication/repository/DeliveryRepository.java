package com.example.dronepizzaapplication.repository;

import com.example.dronepizzaapplication.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // Find alle leveringer der ikke er afsluttet (actualTime er null)
    List<Delivery> findByActualTimeIsNull();

    // Find alle leveringer der mangler en drone
    List<Delivery> findByDroneIsNullAndActualTimeIsNull();
}
