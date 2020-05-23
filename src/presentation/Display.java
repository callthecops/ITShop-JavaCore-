package presentation;

import servicemiddle.UserService;
import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;

import java.util.List;
import java.util.Scanner;

//Is calling the service layer when it needs to access data from the storage.
public class Display {
    private UserService userService;

    public Display(UserService userService) {
        this.userService = userService;
    }

    //General Screen for all users
    public void displayWelcomeScreen() {
        System.out.println("");
        System.out.print("                                                      !@!@!@! WELCOME TO THE R2D2 COMPUTER ACCESSORIES SHOP !@!@!@!\n\n");
        System.out.print("Please choose a user by inputing the number in order to access the program\n\n");

        List<User> users = userService.getUserDao().getUserList();
        for (User u : users) {
            System.out.println(u);
        }

        int input = retrieveUserInput();

        User user = userService.searchUserById(input);
        User CustomerOrAdmin = userService.createByRole(user);
        userService.redirectByRole(CustomerOrAdmin);

    }


    // takes input for the 4 users available from the user
    public int retrieveUserInput() {
        int userChoice = 0;
        boolean test = true;
        while (test) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                userChoice = Integer.parseInt(sc.nextLine());
                if (userChoice < 0 || userChoice > 4) {
                    System.out.println("Please enter the correct number ");
                } else {
                    return userChoice;
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
        return -1;
    }


    //Screen for user1 wich is the only admin from the list
    public static void displayAdminPanel(Admin admin) {
        System.out.println("Admin Panel");
    }

    public static void displayCustomerPanel(Customer customer) {
        System.out.println("Customer Panel");
    }

}
