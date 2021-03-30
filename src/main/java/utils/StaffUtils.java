package utils;

import config.HibernateConfig;
import entities.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.impl.StaffRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class StaffUtils {

    private static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    private static Session session = sessionFactory.openSession();

    private static StaffRepositoryImpl staffRepository = new StaffRepositoryImpl(session);

    public static void updateMemberOfStaffWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the member of staff you want to update ");
        int id = in.nextInt();
        Staff staff = session.find(Staff.class, id);
        if (staff != null) {
            updateAttributeOfMemberStaff(staff);
        } else {
            System.out.println("Staff with id " + id + " is not registered.");
        }
    }

    public static void updateAttributeOfMemberStaff(Staff staff) {
        Scanner in = new Scanner(System.in);
        System.out.println("Which of the member features do you want to update? \n" +
                "1.First Name \n" +
                "2.Last Name \n" +
                "3.Description \n");
        int select = in.nextInt();
        Scanner input = new Scanner(System.in);
        switch (select) {
            case 1:
                System.out.println("You choose First Name. Please, enter the new First Name");
                String firstName = input.nextLine();
                staff.setFirstName(firstName);
                staffRepository.update(staff);
                break;
            case 2:
                System.out.println("You choose Last Name. Please, enter the new Last Name");
                String lastName = input.nextLine();
                staff.setLastName(lastName);
                staffRepository.update(staff);
                break;
            case 3:
                System.out.println("You choose description. Please, enter the new description");
                String description = input.nextLine();
                staff.setDescription(description);
                staffRepository.update(staff);
                break;
        }
    }

    public static void deleteMemberOfStaffWithGivenId() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the member of staff you want to delete ");
        int id = in.nextInt();
        Staff staff = session.find(Staff.class, id);
        if (staff != null) {
            staffRepository.delete(staff);
        } else {
            System.out.println("Member of staff with id " + id + " is not registered.");
        }
    }

    public static void createNewMemberOfStaff() {
        Scanner in = new Scanner(System.in);
        Staff staff = new Staff();
        System.out.println("Enter first name: ");
        String firstName = in.nextLine();
        System.out.println("Enter last name: ");
        String lastName = in.nextLine();
        System.out.println("Enter description: ");
        String description = in.nextLine();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setDescription(description);
        staffRepository.create(staff);
    }

    public static void selectFromMenuOfStaff() {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(" \n   Choose between these option: \n" +
                    "1. @Create a member of staff \n" +
                    "2. @Read all member of staff \n" +
                    "3. @Update a member of staff with given id \n" +
                    "4. @Delete a member of staff with given id \n" +
                    "5. @Go to home page... \n");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    createNewMemberOfStaff();
                    break;
                case 2:
                    List<Staff> staff = staffRepository.read();
                    staff.forEach(s ->
                            System.out.println(s));
                    break;
                case 3:
                    updateMemberOfStaffWithGivenId();
                    break;
                case 4:
                    deleteMemberOfStaffWithGivenId();
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
