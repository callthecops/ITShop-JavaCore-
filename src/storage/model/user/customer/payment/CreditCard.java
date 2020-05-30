package storage.model.user.customer.payment;

public class CreditCard extends Payment {
    private Long cardNumber;
    private short securityCode;


    public Long getCardNumber() {
        return cardNumber;
    }

    public CreditCard setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public short getSecurityCode() {
        return securityCode;
    }

    public CreditCard setSecurityCode(short securityCode) {
        this.securityCode = securityCode;
        return this;
    }
}
