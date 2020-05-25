package storage.model.user.customer;

import storage.model.product.Product;

import java.util.List;

public class ShoppingBasket {
    private List<Product> products;
    private String[] status = {"purchased", "saved", "cancelled"};

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String[] getStatus() {
        return status;
    }


}
