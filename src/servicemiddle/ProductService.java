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

}
