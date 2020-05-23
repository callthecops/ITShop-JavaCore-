package storage.model.user;

public class Address {
    private int houseNr;
    private String postCode;
    private String city;


    public Address(int houseNr, String postCode, String city) {
        this.houseNr = houseNr;
        this.postCode = postCode;
        this.city = city;
    }

    public Address() {
    }

    public int getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}