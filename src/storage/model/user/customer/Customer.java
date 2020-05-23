package storage.model.user.customer;

import storage.model.user.User;

public class Customer extends User {
    private ShoppingBasket basket;
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
