package pl.bm.bmyszkiewiczprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bm.bmyszkiewiczprojekt.entity.AppUser;
import pl.bm.bmyszkiewiczprojekt.entity.Order;
import pl.bm.bmyszkiewiczprojekt.entity.Pizza;
import pl.bm.bmyszkiewiczprojekt.entity.Status;
import pl.bm.bmyszkiewiczprojekt.repository.AppUserRepository;
import pl.bm.bmyszkiewiczprojekt.repository.OrderRepository;
import pl.bm.bmyszkiewiczprojekt.repository.PizzaRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private AppUserRepository appUserRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository, AppUserRepository appUserRepository) {
        this.orderRepository = orderRepository;
        this.appUserRepository = appUserRepository;
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
        AppUser appUser = appUserRepository.findByUsername(username);
        Order order = new Order(LocalDateTime.now(), price, Status.REALIZACJA, description.toString(), appUser);
        orderRepository.save(order);

    }
}
