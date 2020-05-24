package presentation;

import servicemiddle.UserService;
import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;

import java.util.List;
import java.util.Scanner;

//Is calling the service layer when it needs to access data from the storage.
public class Display {
    private UserService userService=new UserService();

    public Display() {
    }

    public Display(UserService userService) {
        this.userService = userService;
    }
///////////////////////////////////////////////////////////GENERAL VIEWS/////////////////////////////////////////////////////////////

    //General Screen for all users
    public void displayWelcomeScreen() {
        System.out.println("");
        System.out.print("                                                      !@!@!@! WELCOME TO THE R2D2 COMPUTER ACCESSORIES SHOP !@!@!@!\n\n");
        System.out.print("Please choose a user by inputing the number in order to access the program\n\n");

        List<User> users = userService.retrieveUsers();
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

///////////////////////////////////////////////////////////ADMIN VIEWS/////////////////////////////////////////////////////////////
    //First admin panel
    public void displayAdminPanel(Admin admin) {
        System.out.println("Welcome " + admin.getRole() + " " + admin.getUserName() + ".Please make your choice.\n");
        System.out.println("1.View/Add Stock\n");
        System.out.println("2.Back\n");
        int choice = retrieveAdminInputFromFirstPanel();
        userService.redirectAdminBackOrToStock(new Display(),choice);

    }

    //Retrieve admin first input
    public int retrieveAdminInputFromFirstPanel() {
        boolean test = true;
        while (test) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    return choice;
                } else if (choice == 2) {
                    return choice;
                } else {
                    System.out.println("Please enter the correct number");
                }
            } else {
                System.out.println("Please enter correctly");
            }
        }
        return 0;
    }


    ///////////////////////////////////////////////////////////CUSTOMER VIEWS/////////////////////////////////////////////////////////////


    public static void displayCustomerPanel(Customer customer) {
        System.out.println("Customer Panel");
    }

}
