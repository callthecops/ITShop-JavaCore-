package storage.model.product;

/**
 * Class parent of Keyboard and Mouse class
 *
 * @author Tudor
 */
public class Product implements Comparable<Product> {

    private int barCode;
    private String productType;
    private String brand;
    private String color;
    private String connectivity;
    private int quantity;
    private double originalPrice;
    private double retailPrice;


    public Product(int barCode, String productType, String brand, String color, String connectivity, int quantity, double originalPrice, double retailPrice) {
        this.barCode = barCode;
        this.productType = productType;
        this.brand = brand;
        this.color = color;
        this.connectivity = connectivity;
        this.quantity = quantity;
        this.originalPrice = originalPrice;
        this.retailPrice = retailPrice;
    }

    public Product() {
    }

    @Override
    public int compareTo(Product product) {
        return this.quantity - product.quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return barCode == product.barCode;
    }

    @Override
    public int hashCode() {
        return barCode;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

}