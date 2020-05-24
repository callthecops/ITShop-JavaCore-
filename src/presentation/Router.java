package presentation;

import storage.model.user.User;
import storage.model.user.customer.Customer;

import java.io.IOException;

public class Router {
    private final Display display;

    public Router(Display display) {
        this.display = display;

    }

    //This assigns the user a role the first time he selects one of the users from the list.
    public void redirectByRole(User user) {
        if (user.getRole().equals(" admin")) {
            display.displayAdminPanel();
        } else {
            display.displayCustomerPanel((Customer) user);
        }
    }


    //////////////////////////////////////////////////////ADMIN REDIRECTS///////////////////////////////////////////////////////////////////

    //First Redirect

    public void redirectToWelcomeScreenOrToStock(int userInput) {
        if (userInput == 1) {
            display.displayProductsPanel();
        } else {
            display.displayWelcomeScreen();
        }
    }

    //Second Redirect

    public void redirectToWelcomeOrToAddingStock(int userInput) {
        if (userInput == 1) {
            try {
                display.displayAddStockSelection();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            display.displayWelcomeScreen();
        }
    }


    //////////////////////////////////////////////////////CUSTOMER REDIRECTS///////////////////////////////////////////////////////////////////


}
