package presentation;

import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;

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
            setCustomer((Customer) user);
        }
    }
}
