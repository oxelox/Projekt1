package pl.bm.bmyszkiewiczprojekt.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private LocalDate ocExpire;
    @OneToOne(mappedBy = "car")
    private Deliverer deliverer;

    public Car() {
    }

    public Car(String brand, String model, LocalDate ocExpire, Deliverer deliverer) {
        this.brand = brand;
        this.model = model;
        this.ocExpire = ocExpire;
        this.deliverer = deliverer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getOcExpire() {
        return ocExpire;
    }

    public void setOcExpire(LocalDate ocExpire) {
        this.ocExpire = ocExpire;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", ocExpire=" + ocExpire +
                ", deliverer=" + deliverer +
                '}';
    }
}
