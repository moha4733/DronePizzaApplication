package com.example.dronepizzaapplication.repository;

import com.example.dronepizzaapplication.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
