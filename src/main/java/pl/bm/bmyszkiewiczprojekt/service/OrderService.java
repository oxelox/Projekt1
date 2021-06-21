package pl.bm.bmyszkiewiczprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bm.bmyszkiewiczprojekt.entity.*;
import pl.bm.bmyszkiewiczprojekt.repository.AppUserRepository;
import pl.bm.bmyszkiewiczprojekt.repository.DelivererRepository;
import pl.bm.bmyszkiewiczprojekt.repository.OrderRepository;
import pl.bm.bmyszkiewiczprojekt.repository.PizzaRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private AppUserRepository appUserRepository;
    private DelivererRepository delivererRepository;
    private Random rand = new Random();


    @Autowired
    public OrderService(OrderRepository orderRepository, AppUserRepository appUserRepository, DelivererRepository delivererRepository) {
        this.orderRepository = orderRepository;
        this.appUserRepository = appUserRepository;
        this.delivererRepository = delivererRepository;
    }


    public void order( List<Pizza> pizzas, String username ){
        BigDecimal price = new BigDecimal("0");
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < pizzas.size(); i++) {
            price = price.add(pizzas.get(i).getPrice());
            description.append(pizzas.get(i).getName());
            if(i< pizzas.size()-1){
                description.append(", ");
            }

        }
        int size = delivererRepository.findAll().size();
        int delivererId = rand.nextInt(size);
        Deliverer byId = delivererRepository.findById(Long.valueOf(delivererId)).get();
        AppUser appUser = appUserRepository.findByUsername(username);
        Order order = new Order(LocalDateTime.now(), price, Status.REALIZACJA, description.toString(), appUser, byId);
        orderRepository.save(order);

    }
    public void changeStatus(Long id){
        Order byId = orderRepository.findById(id).get();
        if(byId.getStatus().equals(Status.REALIZACJA)){
            byId.setStatus(Status.DOSTAWA);
            System.out.println("zmiana na dostawa");
        }else if(byId.getStatus().equals(Status.DOSTAWA)){
            byId.setStatus(Status.KONIEC);
            System.out.println("zmiana na koniec");
        }else{
            System.out.println("Error");
        }
        orderRepository.save(byId);

    }
}
