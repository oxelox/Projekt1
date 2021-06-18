package pl.bm.bmyszkiewiczprojekt.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "orders_pizzas",
            joinColumns = {@JoinColumn(name = "pizza_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Pizza> pizzas = new HashSet<>();
    @OneToOne
    private AppUser appUser;

    public Order() {
    }

    public Order(LocalDateTime orderDate, BigDecimal price, Status status,  AppUser appUser) {
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
        this.appUser = appUser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(final Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }




    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", status=" + status +
                ", pizzas=" + pizzas +
                '}';
    }
}
