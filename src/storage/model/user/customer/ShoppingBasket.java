package storage.model.user.customer;

import storage.model.product.Product;

import java.util.List;

public class ShoppingBasket {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}