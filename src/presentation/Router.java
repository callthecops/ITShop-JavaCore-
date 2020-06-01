package presentation;

import storage.Dao.ProductDao.ProductDao;
import storage.model.user.User;
import storage.model.user.customer.Customer;

import java.io.File;
import java.io.IOException;

/**Router class is used along with display to switch screens.
 * @author Tudor
 */


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

    //Third Redirect
    public void redirectToStockScreenAfterTheProductHasBeenAdded(ProductDao productDao) throws IOException {

        productDao.setProductList(productDao.getAll((new File("C:/Users/Tudor/Desktop/Proiecte/Portofoliu/ITShop-JavaCore-/Stock.txt"))));

    }

    //////////////////////////////////////////////////////CUSTOMER REDIRECTS///////////////////////////////////////////////////////////////////


    //First redirect - back to welcome screen or to customer panel
    public void redirectToWelcomeScreenOrToCustomerPanel(int userInput) {
        if (userInput == 1) {
            display.displayCustomerPanelTwo(display.getViewModel().getCustomer());
        } else {
            //when customer returns to user selection screen, this method auto emtpies basket and readds items to stock
            try {

                display.getProductService().emptyCustomerBasket(display.getViewModel().getCustomer());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            //then it redirects to user selection screen
            display.displayWelcomeScreen();
        }
    }

    //Second redirect - Add products to basket/Basket Options/Search/Back
    public void multiCustomerRedirect(int input) {
        if (input == 1) {
            display.addProductsToBasket(display.getProductService().getProductDao().getProductList());
        } else if (input == 2) {
            if (display.getViewModel().getCustomer().getBasket().getProducts().isEmpty()) {
                System.out.println("Your basket is empty, you have to add items first");
                display.displayCustomerPanelTwo(display.getViewModel().getCustomer());
            }
            display.displayBasketContentsAndShowOptions();
        } else if (input == 3) {
            //Displays products sorted by brand
            display.displayCustomerSortByBrandPanel();
        } else {
            display.displayCustomerPanel(display.getViewModel().getCustomer());
        }
    }

    //Basket Options redirects
    public void basketOptionsRedirect(int choice) {
        if (choice == 1) {
            display.displayCustomerBuyOptionPanel();
        } else if (choice == 2) {

            //SAVES BASKET TO TEXT
            display.getProductService().saveBasketForLater(display.getViewModel().getCustomer().getBasket(), display.getViewModel().getCustomer());
            System.out.println("Your basket has been saved for later");
            display.displayCustomerPanelTwo(display.getViewModel().getCustomer());

        } else if (choice == 3) {
            //EMPTIES THE BASKET AND RESTORES STOCK
            try {
                display.getProductService().emptyCustomerBasket(display.getViewModel().getCustomer());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            System.out.println("Basket has been emptied");
            display.displayCustomerPanelTwo(display.getViewModel().getCustomer());
        } else {
            //BACK TO PANEL
            display.displayCustomerPanelTwo(display.getViewModel().getCustomer());
        }
    }

    //redirects from single input window - sorted by brand products
    public void redirectFromBrandDisplayToCustomerPanelTwo() {
        display.displayCustomerPanelTwo(display.getViewModel().getCustomer());
    }

    //redirects based on the choice that the customer made regarding the payment option
    //this method is called once the customer has selected the payment option(paypal or creditcard)
    public void redirectByPaymentOption(int choice) {
        if (choice == 1) {
            display.displayBuyOptionPaypalPanel();
        } else {
            display.displayBuyOptionCreditCardPanel();
        }
    }
}
