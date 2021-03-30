package utils;

import config.HibernateConfig;
import entities.Dish;
import entities.Order;
import entities.Restaurant;
import entities.RestaurantTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.impl.OrderRepositoryImpl;

import java.util.*;

public class OrderUtils {

    public static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public static Session session = sessionFactory.openSession();

    public static OrderRepositoryImpl orderRepository = new OrderRepositoryImpl(session);

    public static void findOrderWithGivenId() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the order you are looking for : ");
        int id = in.nextInt();
        Order order = orderRepository.findById(id);
        if (order != null) {
            System.out.println(order.toString());
        } else {
            System.out.println("Order with id " + id + " is not registered.");
        }
    }

    public static void deleteOrderWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the order you want to delete ");
        int id = in.nextInt();
        Order order = orderRepository.findById(id);
        if (order != null) {
            orderRepository.delete(order);
        } else {
            System.out.println("Order with id " + id + " is not registered.");
        }
    }

    public static void updateOrderWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the order you want to update ");
        int id = in.nextInt();
        Order order = orderRepository.findById(id);
        if (order != null) {
            System.out.println("Enter amount");
            int amount = in.nextInt();
            order.setAmount(amount);
            orderRepository.update(order);
        } else {
            System.out.println("Order with id " + id + " is not registered.");
        }
    }

    public static void createNewOrder() {
        try {
            Order order = new Order();
            Scanner in = new Scanner(System.in);
            System.out.println("Enter Amount:");
            int amount = in.nextInt();
            order.setAmount(amount);
            System.out.println("Enter id of restaurant where you want to order");
            int idOfRestaurant = in.nextInt();
            Restaurant restaurant = session.find(Restaurant.class, idOfRestaurant);
            System.out.println("Enter id of table where you want to sit");
            int idOfRestaurantTable = in.nextInt();
            RestaurantTable restaurantTable = session.find(RestaurantTable.class, idOfRestaurantTable);
            restaurantTable.setRestaurants(restaurant);
            order.setRestaurantTable(restaurantTable);
            int count = 0;
            List<Dish> dishes = new ArrayList<>();
            while (count < amount) {
                System.out.println("Enter id of dish you want to order");
                int id = in.nextInt();
                Dish dish = session.find(Dish.class, id);
                dishes.add(dish);
                count++;
            }
            order.setDishes(dishes);
            orderRepository.create(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectFromMenuOfOrder() {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(" \n   Click one of the option \n" +
                    "1. Find if a order with a given id exists \n" +
                    "2. Create a new order \n" +
                    "3. Read all order store \n" +
                    "4. Update a order with a given id \n" +
                    "5. Delete a order with a given id \n" +
                    "6. @Go to home page... \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    findOrderWithGivenId();
                    break;
                case 2:
                    createNewOrder();
                    break;
                case 3:
                    List<Order> orders = orderRepository.read();
                    orders.forEach(order -> System.out.println(order));
                    break;
                case 4:
                    updateOrderWithGivenId();
                    break;
                case 5:
                    deleteOrderWithGivenId();
                    break;
                case 6:
                    System.out.println("       *Welcome again to home page*     ");
                    exit = true;
                    break;
                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another");
                    break;
            }
        } while (!exit);
    }
}
