package utils;

import config.HibernateConfig;
import entities.Restaurant;
import entities.Review;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.impl.RestaurantRepositoryImpl;
import repositories.impl.ReviewRepositoryImpl;
import repositories.impl.UserRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class ReviewUtils {

    public static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public static Session session = sessionFactory.openSession();

    public static ReviewRepositoryImpl reviewRepository = new ReviewRepositoryImpl(session);

    public static UserRepositoryImpl userRepository = new UserRepositoryImpl(session);

    public static RestaurantRepositoryImpl restaurantRepository = new RestaurantRepositoryImpl(session);


    public static void createReview() {
        Scanner in = new Scanner(System.in);
        Review review = new Review();
        System.out.println("Write a description: ");
        String description = in.nextLine();
        System.out.println("Enter your rate: ");
        int rate = in.nextInt();
        System.out.println("Enter id of restaurant where you want to give a review: ");
        int idOfRestaurant = in.nextInt();
        Restaurant restaurant = restaurantRepository.findById(idOfRestaurant);
        System.out.println("Enter id of user who  want to give a review");
        int idOfUser = in.nextInt();
        User user = userRepository.findById(idOfUser);
        review.setRate(rate);
        review.setDescription(description);
        review.setRestaurant(restaurant);
        review.setUser(user);
        reviewRepository.create(review);
    }

    public static void deleteReview() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of review you want to delete ");
        int id = in.nextInt();
        Review review = session.find(Review.class, id);
        if (review != null) {
            reviewRepository.delete(review);
        } else {
            System.out.println("Review with id " + id + " is not registered.");
        }
    }

    public static void updateAttributeOfReview(Review review) {
        Scanner in = new Scanner(System.in);
        System.out.println("Which of the review features do you want to update? \n" +
                "1.Rate \n" +
                "2.Description \n");
        int select = in.nextInt();
        Scanner input = new Scanner(System.in);
        switch (select) {
            case 1:
                System.out.println("You choose Rate. Please, enter the new Rate");
                int rate = input.nextInt();
                review.setRate(rate);
                reviewRepository.update(review);
                break;
            case 2:
                System.out.println("You choose Description. Please, enter the new Description");
                String description = input.nextLine();
                review.setDescription(description);
                reviewRepository.update(review);
                break;
        }
    }

    public static void updateReviewWithGivenId() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of review you want to update ");
        int id = in.nextInt();
        Review review = session.find(Review.class, id);
        if (review != null) {
            updateAttributeOfReview(review);
        } else {
            System.out.println("Review with id " + id + " is not registered.");
        }
    }

    public static void selectFromMenuOfReview() {

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(" \n   Choose between these option: \n" +
                    "1. @Create a review \n" +
                    "2. @Read all store review \n" +
                    "3. @Update a review with given id \n" +
                    "4. @Delete a review\n" +
                    "5. @Go to home page... \n");

            int option = in.nextInt();
            switch (option) {
                case 1:
                    createReview();
                    break;
                case 2:
                    List<Review> reviews = reviewRepository.read();
                    reviews.forEach(review ->
                            System.out.println(review));
                    break;
                case 3:
                    updateReviewWithGivenId();
                    break;
                case 4:
                    deleteReview();
                    break;
                case 5:
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
