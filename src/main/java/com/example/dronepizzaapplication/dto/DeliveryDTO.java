package com.example.dronepizzaapplication.dto;

import java.time.LocalDateTime;

public record DeliveryDTO (Long id,
                           String address,
                           String pizzaName,
                           LocalDateTime expectedTime,
                           Long droneId,
                           boolean needsDrone) {

}
