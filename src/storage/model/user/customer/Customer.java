package storage.model.user.customer;

import storage.model.user.User;
import storage.model.user.customer.payment.Payment;



public class Customer extends User {
    private ShoppingBasket basket = new ShoppingBasket();
    private Payment payment;

    public ShoppingBasket getBasket() {
        return basket;
    }

    public void setBasket(ShoppingBasket basket) {
        this.basket = basket;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
