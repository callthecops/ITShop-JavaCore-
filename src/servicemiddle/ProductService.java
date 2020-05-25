package servicemiddle;

import storage.Dao.ProductDao.ProductDao;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;
import storage.model.user.admin.Admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> retrieveProducts() {
        return productDao.getProductList();
    }

    //Retrieves the product by Barcode

    public Product retrieveBarcode(List<Product> products) {
        System.out.println("Please enter Barcode below:");
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int barcode = Integer.parseInt(scanner.nextLine());
                for (Product product : products) {
                    if (barcode == product.getBarCode()) {
                        return product;
                    }
                }
                System.out.println("There is no such product, please enter again");
            } else {
                System.out.println("Please enter a number");
            }
        }
        return null;
    }




}
