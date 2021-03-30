package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer reservationId;

    @Column(name = "reservation_time")
    private LocalDateTime reservationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToMany
    @JoinTable(
            name = "table_reservation",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_id")}
    )
    private Set<RestaurantTable> restaurantTables = new HashSet<>();

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Set<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(Set<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", reservationTime=" + reservationTime +
                ", users=" + users +
                '}';
    }
}
