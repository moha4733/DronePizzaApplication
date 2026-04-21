package com.example.dronepizzaapplication.controller;

import com.example.dronepizzaapplication.entity.Pizza;
import com.example.dronepizzaapplication.repository.PizzaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping
    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pizza getPizzas(@PathVariable Long id){
        return pizzaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("pizza ikke fundet"));
    }

}
