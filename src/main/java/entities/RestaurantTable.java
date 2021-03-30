package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tabless")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer tableId;

    private Integer capacity;

    @Column(name = "is_vacant")
    private Boolean isVacant;

    private String location;

    @ManyToMany(mappedBy = "restaurantTables")
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurants;

    @OneToMany(mappedBy = "restaurantTable")
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "table_staff",
            joinColumns = {@JoinColumn(name = "table_id")},
            inverseJoinColumns = {@JoinColumn(name = "staff_id")}
    )
    private Set<Staff> staff = new HashSet<>();

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getVacant() {
        return isVacant;
    }

    public void setVacant(Boolean vacant) {
        isVacant = vacant;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Restaurant getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurant restaurants) {
        this.restaurants = restaurants;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "RestaurantTable{" +
                "tableId=" + tableId +
                ", capacity=" + capacity +
                ", isVacant=" + isVacant +
                ", location='" + location + '\'' +
                '}';
    }
}
