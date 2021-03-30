package utils;

import config.HibernateConfig;
import entities.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.impl.RestaurantRepositoryImpl;
import java.util.List;
import java.util.Scanner;

public class RestaurantUtils {

    private static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    private static Session session = sessionFactory.openSession();

    private static RestaurantRepositoryImpl restaurantRepository = new RestaurantRepositoryImpl(session);


    public static void findRestaurantWithGivenId() {
        System.out.println("Enter the id of the restaurant you are looking for : ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            System.out.println(restaurant.toString());
        } else {
            System.out.println("Restaurant with id " + id + " is not registered.");
        }
    }

    public static void deleteRestaurantWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the restaurant you want to delete ");
        int id = in.nextInt();
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
        } else {
            System.out.println("Restaurant with id " + id + " is not registered.");
        }
    }

    public static void updateRestaurantWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the restaurant you want to update ");
        int id = in.nextInt();
        Restaurant restaurant = restaurantRepository.findById(id);
        if (restaurant != null) {
            updateAttributeOfRestaurant(restaurant);
        } else {
            System.out.println("Restaurant with id " + id + " is not registered.");
        }
    }

    public static void updateAttributeOfRestaurant(Restaurant restaurant) {
        Scanner in = new Scanner(System.in);
        System.out.println("Which of the restaurant features do you want to update? \n" +
                "1.Name \n" +
                "2.Type \n" +
                "3.Address \n" +
                "4.PhoneNumber \n");
        int select = in.nextInt();
        Scanner input = new Scanner(System.in);
        switch (select) {
            case 1:
                System.out.println("You choose name. Please, enter the new name");
                String name = input.nextLine();
                restaurant.setName(name);
                restaurantRepository.update(restaurant);
                break;
            case 2:
                System.out.println("You choose type. Please, enter the new type");
                String type = input.nextLine();
                restaurant.setType(type);
                restaurantRepository.update(restaurant);
                break;
            case 3:
                System.out.println("You choose address. Please, enter the new address");
                String address = input.nextLine();
                restaurant.setAddress(address);
                restaurantRepository.update(restaurant);
                break;
            case 4:
                System.out.println("You choose phoneNumber. Please, enter the new phoneNumber");
                String phone = input.nextLine();
                restaurant.setPhoneNumber(phone);
                restaurantRepository.update(restaurant);
                break;
        }
    }

    public static void createNewRestaurant() {
        Scanner in = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        System.out.println("Enter name:");
        String nameOfRestaurant = in.nextLine();
        System.out.println("Enter address:");
        String addressOfRestaurant = in.nextLine();
        System.out.println("Enter phoneNumber");
        String phoneNumberOfRestaurant = in.nextLine();
        System.out.println("Enter type");
        String typeOfRestaurant = in.nextLine();
        restaurant.setName(nameOfRestaurant);
        restaurant.setAddress(addressOfRestaurant);
        restaurant.setPhoneNumber(phoneNumberOfRestaurant);
        restaurant.setType(typeOfRestaurant);
        restaurantRepository.create(restaurant);
    }

    public static void selectFromMenuOfRestaurant() {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(" \n   Choose between these option: \n" +
                    "1. @Find if a restaurant with a given id exists \n" +
                    "2. @Create a new restaurant \n" +
                    "3. @Read all restaurant store \n" +
                    "4. @Update a restaurant with a given id \n" +
                    "5. @Delete a restaurant with a given id \n" +
                    "6. @Go to home page... \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    findRestaurantWithGivenId();
                    break;
                case 2:
                    createNewRestaurant();
                    break;
                case 3:
                    List<Restaurant> restaurants = restaurantRepository.read();
                    restaurants.forEach(restaurant ->
                            System.out.println(restaurant));
                    break;
                case 4:
                    updateRestaurantWithGivenId();
                    break;
                case 5:
                    deleteRestaurantWithGivenId();
                    break;
                case 6:
                    System.out.println("       *Welcome again to home page*     ");
                    exit = true;
                    break;
                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another.");
                    break;
            }
        } while (!exit);
    }


}
