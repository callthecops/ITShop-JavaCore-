package storage.model.user.customer.payment;

public class PayPal extends Payment {
    private String eMail;

    public String geteMail() {
        return eMail;
    }

    public PayPal seteMail(String eMail) {
        this.eMail = eMail;
        return this;
    }


}
