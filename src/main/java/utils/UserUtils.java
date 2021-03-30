package utils;

import config.HibernateConfig;
import entities.Role;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.impl.RoleRepositoryImpl;
import repositories.impl.UserRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserUtils {

    public static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
    public static Session session = sessionFactory.openSession();
    public static UserRepositoryImpl userRepository = new UserRepositoryImpl(session);
    public static RoleRepositoryImpl roleRepository = new RoleRepositoryImpl(session);

    public static void updateUserWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of user you want to update ");
        int id = in.nextInt();
        User user = session.find(User.class, id);
        if (user != null) {
            updateAttributeOfUser(user);
        } else {
            System.out.println("User with id " + id + " is not registered.");
        }
    }

    public static void updateAttributeOfUser(User user) {
        Scanner in = new Scanner(System.in);
        System.out.println("Which of the user features do you want to update? \n" +
                "1.First Name \n" +
                "2.Last Name \n" +
                "3.Username \n" +
                "4.Password \n" +
                "5.Phone number \n" +
                "6.Email \n");
        int select = in.nextInt();
        Scanner input = new Scanner(System.in);
        switch (select) {
            case 1:
                System.out.println("You choose First Name. Please, enter the new First Name");
                String firstName = input.nextLine();
                user.setFirstName(firstName);
                userRepository.update(user);
                break;
            case 2:
                System.out.println("You choose Last Name. Please, enter the new Last Name");
                String lastName = input.nextLine();
                user.setLastName(lastName);
                userRepository.update(user);
                break;
            case 3:
                System.out.println("You choose Username. Please, enter the new username");
                String username = input.nextLine();
                user.setUserName(username);
                userRepository.update(user);
                break;
            case 4:
                System.out.println("You choose Password. Please, enter the new password");
                String password = input.nextLine();
                user.setPassword(password);
                userRepository.update(user);
                break;
            case 5:
                System.out.println("You choose Phone number. Please, enter the new phone number");
                String phoneNumber = input.nextLine();
                user.setPhoneNumber(phoneNumber);
                userRepository.update(user);
                break;
            case 6:
                System.out.println("You choose Email. Please, enter the new email");
                String email = input.nextLine();
                user.setEmail(email);
                userRepository.update(user);
                break;
        }
    }

    public static void deleteUserWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of user you want to delete ");
        int id = in.nextInt();
        User user = session.find(User.class, id);
        if (user != null) {
            userRepository.delete(user);
        } else {
            System.out.println("User with id " + id + " is not registered.");
        }
    }

    public static void createUser() {
        Scanner in = new Scanner(System.in);
        User user = new User();
        System.out.println("Enter first name: ");
        String firstName = in.nextLine();
        System.out.println("Enter last name: ");
        String lastName = in.nextLine();
        System.out.println("Enter username: ");
        String userName = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter email: ");
        String email = in.nextLine();
        System.out.println("Select your user role: ");
        System.out.println("1. Admin \n" +
                "3. Client");
        int option = in.nextInt();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        switch (option) {

            case 1:
                role = roleRepository.findById(1);
                roles.add(role);
                user.setRoles(roles);
                break;
            case 3:
                role = roleRepository.findById(3);
                roles.add(role);
                user.setRoles(roles);
                break;
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        userRepository.create(user);
    }

    public static void selectFromMenuOfUser() {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(" \n   Choose between these option: \n" +
                    "1. @Create a user \n" +
                    "2. @Read all users \n" +
                    "3. @Update a user with given id \n" +
                    "4. @Delete a user with given id \n" +
                    "5. @Go to home page... \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    createUser();
                    break;
                case 2:
                    List<User> users = userRepository.read();
                    users.forEach(user ->
                            System.out.println(user));
                    break;
                case 3:
                    updateUserWithGivenId();
                    break;
                case 4:
                    deleteUserWithGivenId();
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

    public static boolean logIn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter username:");
        String userName = in.nextLine();
        System.out.println("Enter password");
        String password = in.nextLine();
        User user = userRepository.findByUsernameAndPassword(userName, password);
        if (user == null) {
            System.out.println("Username or password is incorrect. Try again ^_^");
            return false;
        }
        System.out.println("UserName and Password are correct *-*");
        return true;
    }


}
