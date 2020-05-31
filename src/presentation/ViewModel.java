package presentation;

import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;
import storage.model.user.customer.ShoppingBasket;

/**
 * The purpose of this class is to enable easy access to a selected user.Once a user is selected from the inputed list
 * we can easely access it in Display ,Service and Router with this class in order to perform operations on it.
 * @author Tudor
 */

public class ViewModel {
    private Admin admin;
    private Customer customer;
    private Display display;

    public ViewModel(Display display) {
        this.display = display;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }


    public void differentiateRoles(User user) {
        if (user.getRole().equals(" admin")) {
            setAdmin((Admin) user);
        } else {
            ShoppingBasket basket = new ShoppingBasket();
            setCustomer((Customer) user);

        }
    }
}
