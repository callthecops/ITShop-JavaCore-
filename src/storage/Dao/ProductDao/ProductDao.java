package storage.Dao.ProductDao;

import storage.Dao.UserDao.Dao;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao implements Dao<Product> {
    List<Product> productList;

    public ProductDao() throws IOException {
        this.productList = getAll(new File("src/Stock.txt"));
    }


    @Override
    public List<Product> getAll(File file) throws IOException {
        Scanner sc = new Scanner(file);
        List<Product> productList = new ArrayList<>();
        while (sc.hasNext()) {
            String[] products = sc.nextLine().replace(" ", "").split(",");
            if (products[1].equals("mouse")) {
                Mouse mouse = new Mouse();
                mouse.setBarCode(Integer.parseInt(products[0]));
                mouse.setProductType(products[1]);
                mouse.setType(products[2]);
                mouse.setBrand(products[3]);
                mouse.setColor(products[4]);
                mouse.setConnectivity(products[5]);
                mouse.setQuantity(Integer.parseInt(products[6]));
                mouse.setOriginalPrice(Double.parseDouble(products[7]));
                mouse.setRetailPrice(Double.parseDouble(products[8]));
                mouse.setNrOfButtons(Integer.parseInt(products[9]));
                productList.add(mouse);
            } else {
                Keyboard keyboard = new Keyboard();
                keyboard.setBarCode(Integer.parseInt(products[0]));
                keyboard.setProductType(products[1]);
                keyboard.setType(products[2]);
                keyboard.setBrand(products[3]);
                keyboard.setColor(products[4]);
                keyboard.setConnectivity(products[5]);
                keyboard.setQuantity(Integer.parseInt(products[6]));
                keyboard.setOriginalPrice(Double.parseDouble(products[7]));
                keyboard.setRetailPrice(Double.parseDouble(products[8]));
                keyboard.setLayout(products[9]);
                productList.add(keyboard);
            }
        }
        return productList;
    }
}
