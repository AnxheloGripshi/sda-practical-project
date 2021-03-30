package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_id")
    private Integer dishId;

    private String description;

    private String price;

    @Column(name = "kind_of_dish")
    private String kindOfDish;

    @ManyToMany(mappedBy = "dishes")
    private List<Order> orders = new ArrayList<>();

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKindOfDish() {
        return kindOfDish;
    }

    public void setKindOfDish(String kindOfDish) {
        this.kindOfDish = kindOfDish;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", kindOfDish='" + kindOfDish + '\'' +
                '}';
    }
}
