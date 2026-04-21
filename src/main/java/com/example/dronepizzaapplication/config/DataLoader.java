package com.example.dronepizzaapplication.config;

import com.example.dronepizzaapplication.entity.Delivery;
import com.example.dronepizzaapplication.entity.Drone;
import com.example.dronepizzaapplication.entity.Pizza;
import com.example.dronepizzaapplication.entity.Station;
import com.example.dronepizzaapplication.repository.DeliveryRepository;
import com.example.dronepizzaapplication.repository.DroneRepository;
import com.example.dronepizzaapplication.repository.PizzaRepository;
import com.example.dronepizzaapplication.repository.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final StationRepository stationRepository;
    private final DeliveryRepository deliveryRepository;
    private final PizzaRepository pizzaRepository;
    private final DroneRepository droneRepository;

    public DataLoader(StationRepository stationRepository ,
                      DeliveryRepository deliveryRepository ,
                      PizzaRepository pizzaRepository ,
                      DroneRepository droneRepository){
        this.stationRepository = stationRepository;
        this.deliveryRepository = deliveryRepository;
        this.pizzaRepository = pizzaRepository;
        this.droneRepository = droneRepository;

    }

    @Override
    public void run(String ... args) throws Exception{
        // 1. OPRET 3 STATIONER (centrum af København området)
        Station station1 = new Station();
        station1.setName("Nørreport Station");
        station1.setLatitude(55.6836);  // 55°41'N
        station1.setLongitude(12.5714); // 12°34'E
        stationRepository.save(station1);

        Station station2 = new Station();
        station2.setName("Frederiksberg Station");
        station2.setLatitude(55.6725);
        station2.setLongitude(12.5328);
        stationRepository.save(station2);

        Station station3 = new Station();
        station3.setName("Østerport Station");
        station3.setLatitude(55.6942);
        station3.setLongitude(12.5869);
        stationRepository.save(station3);

        System.out.println("✅ Oprettet 3 stationer");

        // 2. OPRET 5 PIZZAER
        Pizza pizza1 = new Pizza();
        pizza1.setName("Margherita");
        pizza1.setPrice(79);
        pizzaRepository.save(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.setName("Pepperoni");
        pizza2.setPrice(89);
        pizzaRepository.save(pizza2);

        Pizza pizza3 = new Pizza();
        pizza3.setName("Vesuvio");
        pizza3.setPrice(85);
        pizzaRepository.save(pizza3);

        Pizza pizza4 = new Pizza();
        pizza4.setName("Capricciosa");
        pizza4.setPrice(95);
        pizzaRepository.save(pizza4);

        Pizza pizza5 = new Pizza();
        pizza5.setName("Quattro Stagioni");
        pizza5.setPrice(99);
        pizzaRepository.save(pizza5);

        System.out.println("✅ Oprettet 5 pizzaer");

        // 3. OPRRET ET PAR DRONER (så der er noget at teste med)
        Drone drone1 = new Drone();
        drone1.setSerialNumber(java.util.UUID.randomUUID().toString());
        drone1.setStatus("i drift");
        drone1.setStation(station1);
        droneRepository.save(drone1);

        Drone drone2 = new Drone();
        drone2.setSerialNumber(java.util.UUID.randomUUID().toString());
        drone2.setStatus("i drift");
        drone2.setStation(station2);
        droneRepository.save(drone2);

        Drone drone3 = new Drone();
        drone3.setSerialNumber(java.util.UUID.randomUUID().toString());
        drone3.setStatus("ude af drift");
        drone3.setStation(station1);
        droneRepository.save(drone3);

        System.out.println("✅ Oprettet 3 droner");

        // 4. OPRRET ET PAR LEVERINGER (til test)
        Delivery delivery1 = new Delivery();
        delivery1.setAdresse("Nørregade 10, København");
        delivery1.setExceptedTime(LocalDateTime.now().plusMinutes(30));
        delivery1.setActualTime(null);
        delivery1.setPizza(pizza1);
        delivery1.setDrone(null);
        deliveryRepository.save(delivery1);

        Delivery delivery2 = new Delivery();
        delivery2.setAdresse("Vesterbrogade 25, København");
        delivery2.setExceptedTime(LocalDateTime.now().plusMinutes(45));
        delivery2.setActualTime(null);
        delivery2.setPizza(pizza2);
        delivery2.setDrone(drone1);
        deliveryRepository.save(delivery2);

    }

}
