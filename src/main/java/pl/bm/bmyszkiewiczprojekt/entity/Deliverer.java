package pl.bm.bmyszkiewiczprojekt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Deliverer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "deliverer")
    private List<Order> orderList;
    @OneToOne
    private Car car;


    public Deliverer() {
    }

    public Deliverer(String firstName, String lastName, List<Order> orderList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }



    @Override
    public String toString() {
        return "Deliverer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
