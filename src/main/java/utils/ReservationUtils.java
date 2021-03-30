package utils;

import config.HibernateConfig;
import entities.Reservation;
import entities.RestaurantTable;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.impl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ReservationUtils {

    public static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public static Session session = sessionFactory.openSession();

    public static ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl(session);

    public static RestaurantTableRepositoryImpl restaurantTableRepository = new RestaurantTableRepositoryImpl(session);

    public static UserRepositoryImpl userRepository = new UserRepositoryImpl(session);

    public static Scanner in = new Scanner(System.in);

    public static void createNewReservation() {
        Reservation reservation = new Reservation();
        System.out.println("Enter month/day/time for reservation : ");
        int year = 2020;
        int month = in.nextInt();
        int day = in.nextInt();
        String time = in.next();
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println("Enter id of user who wants to reserve");
        int id = in.nextInt();
        User user = userRepository.findById(id);
        reservation.setUsers(user);
        System.out.println("Enter id of the table you want to reserve:");
        int tableId = in.nextInt();
        RestaurantTable restaurantTable = restaurantTableRepository.findById(tableId);
        Set<RestaurantTable> restaurantTableSet = new HashSet<>();
        restaurantTableSet.add(restaurantTable);
        reservation.setRestaurantTables(restaurantTableSet);
        reservation.setReservationTime(localDateTime);
        reservationRepository.create(reservation);
    }

    public static void findReservationWithGivenId() {
        System.out.println("Enter the id of the reservation you are looking for : ");
        int id = in.nextInt();
        Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            System.out.println(reservation.toString());
        } else {
            System.out.println("Reservation with id " + id + " is not registered.");
        }
    }

    public static void updateReservationWithGivenId() {
        System.out.println("Enter the id of reservation you want to update ");
        int id = in.nextInt();
        Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            System.out.println("Enter new month/day/time for reservation :");
            int year = 2020;
            int month = in.nextInt();
            int day = in.nextInt();
            String time = in.next();
            LocalDate localDate = LocalDate.of(year, month, day);
            LocalTime localTime = LocalTime.parse(time);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            reservation.setReservationTime(localDateTime);
            reservationRepository.update(reservation);
        } else {
            System.out.println("Reservation with id " + id + " is not registered.");
        }
    }

    public static void deleteReservationWithGivenId() {
        System.out.println("Enter the id of the reservation you want to delete ");
        int id = in.nextInt();
        Reservation reservation = reservationRepository.findById(id);
        if (reservation != null) {
            reservationRepository.delete(reservation);
        } else {
            System.out.println("Reservation with id " + id + " is not registered.");
        }
    }

    public static void selectFromMenuOfReservation() {
        boolean exit = false;
        do {
            System.out.println(" \n   Choose between these option: \n" +
                    "1. @Find if a reservation with a given id exists \n" +
                    "2. @Create a new reservation \n" +
                    "3. @Read all reservation store \n" +
                    "4. @Update a reservation with a given id \n" +
                    "5. @Delete a reservation with a given id \n" +
                    "6. @Go to home page... \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    findReservationWithGivenId();
                    break;
                case 2:
                    createNewReservation();
                    break;
                case 3:
                    List<Reservation> reservations = reservationRepository.read();
                    reservations.forEach(reservation -> System.out.println(reservation));
                    break;
                case 4:
                    updateReservationWithGivenId();
                    break;
                case 5:
                    deleteReservationWithGivenId();
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
