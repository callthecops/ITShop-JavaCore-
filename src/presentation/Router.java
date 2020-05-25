package presentation;

import storage.Dao.ProductDao.ProductDao;
import storage.model.user.User;
import storage.model.user.customer.Customer;

import java.io.File;
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

    //Third Redirect
    public void redirectToStockScreenAfterTheProductHasBeenAdded(ProductDao productDao) throws IOException {

        productDao.setProductList(productDao.getAll((new File("src/Stock.txt"))));

    }

    //////////////////////////////////////////////////////CUSTOMER REDIRECTS///////////////////////////////////////////////////////////////////


    //First redirect - back to welcome screen or to customer panel

    public void redirectToWelcomeScreenOrToCustomerPanel(int userInput) {
        if (userInput == 1) {
            display.displayCustomerPanelTwo(display.getViewModel().getCustomer());
        } else {
            display.displayWelcomeScreen();
        }
    }

    //Second redirect - Add products to basket/Basket Options/Search/Back

    public void multiCustomerRedirect(int input){
        if(input==1){
                display.addProductsToBasket(display.getProductService().getProductDao().getProductList());
        }else if(input==2){

        }else if(input==3){

        }else{
            display.displayCustomerPanel(display.getViewModel().getCustomer());
        }
    }

}
