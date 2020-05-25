package storage.Dao.ProductDao;

import storage.Dao.UserDao.Dao;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;
import storage.model.user.admin.Admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao implements Dao<Product> {
    public List<Product> productList;

    public ProductDao() throws IOException {
        this.productList = getAll(new File("src/Stock.txt"));
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
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


    //Method Wich writes new keyboard to file and adds admin modifications to file

    public void writeKeyToFile(Keyboard keyboard, Admin admin) throws IOException {
        String keyString = keyboard.toString();
        String keyAdded = keyString + "|| was added by: " + "|UserName: " + admin.getUserName() + "|User Id:" + admin.getUserId() + "|User Role:" + admin.getRole();
        FileWriter fileWriter = new FileWriter("src/Stock.txt", true);
        FileWriter logWriter = new FileWriter("src/AdminModifications.txt", true);
        fileWriter.write(keyString);
        logWriter.write(keyAdded);
        fileWriter.close();
        logWriter.close();
        System.out.println("Product has been added to stock");
    }


    //Method wich writes new mouse to file and adds admin modifications to file

    public void writeMouseToFile(Mouse mouse,Admin admin) throws IOException {
        String mouseString = mouse.toString();
        String mouseAdded = mouseString + "|| was added by: " + "|UserName: " + admin.getUserName() + "|User Id:" + admin.getUserId() + "|User Role:" + admin.getRole();
        FileWriter fileWriter3 = new FileWriter("src/Stock.txt", true);
        FileWriter logWriter2 = new FileWriter("src/AdminModifications.txt", true);
        fileWriter3.write(mouseString);
        logWriter2.write(mouseAdded);
        fileWriter3.close();
        logWriter2.close();
        System.out.println("Product has been added to stock");

    }

}
