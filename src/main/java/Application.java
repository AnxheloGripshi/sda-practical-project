import utils.*;

import java.util.Scanner;

public class Application {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println();
        System.out.println("      *  Welcome in our application *");
        while (true) {
            System.out.println(
                    "            1.Create new User \n" +
                            "            2.Log in \n" +
                            "            3.Exit \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    UserUtils.createUser();
                    break;
                case 2:
                    boolean result = UserUtils.logIn();
                    if (result == true) {
                        selectOneOfOption();
                    }
                    break;
                case 3:
                    System.out.println("Exiting Program... Thank you");
                    System.exit(0);
                    break;
                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another.");
                    break;
            }
        }
    }

    private static void selectOneOfOption() {
        boolean exit = false;
        do {
            System.out.println("  \n      ^-^ Click one of the option ^-^ \n" +
                    "            1.@Restaurant \n" +
                    "            2.@Staff \n" +
                    "            3.@Reservation \n" +
                    "            4.@User \n" +
                    "            5.@Order \n" +
                    "            6.@Review \n" +
                    "            7.@Exiting this page... \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    RestaurantUtils.selectFromMenuOfRestaurant();
                    break;
                case 2:
                    StaffUtils.selectFromMenuOfStaff();
                    break;
                case 3:
                    ReservationUtils.selectFromMenuOfReservation();
                    break;
                case 4:
                    UserUtils.selectFromMenuOfUser();
                    break;
                case 5:
                    OrderUtils.selectFromMenuOfOrder();
                    break;
                case 6:
                    ReviewUtils.selectFromMenuOfReview();
                    break;
                case 7:
                    System.out.println("   #Welcome again in our application# ");
                    exit = true;
                    break;
                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another.");
                    break;
            }
        } while (!exit);
    }
}


