package pl.bm.bmyszkiewiczprojekt.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


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
    private String description;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne()
    private Deliverer deliverer;

    public Order() {
    }

    public Order(LocalDateTime orderDate, BigDecimal price, Status status, String description, AppUser appUser, Deliverer deliverer) {
        this.orderDate = orderDate;
        this.price = price;
        this.status = status;
        this.description = description;
        this.appUser = appUser;
        this.deliverer = deliverer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", deliverer=" + deliverer +
                '}';
    }
}
