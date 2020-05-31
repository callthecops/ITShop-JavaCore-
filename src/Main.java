import presentation.Display;
import servicemiddle.ProductService;
import servicemiddle.UserService;
import storage.Dao.ProductDao.ProductDao;
import storage.Dao.UserDao.UserDao;

import java.io.IOException;

/**
 * The main class that starts the application.The application is a 3 tier app having a View-service-dao.Along with them
 * i also have a router class to switch display screens.The app is a simple IT store wich sells keyboards and mouse hardware.
 * The app allows user to choose his role (admin/customer).The admin can add product to stock while the customer can
 * do different basket actions including buy,empty,save basket with the products.The whole app was made by me after i found
 * some IT University project paper with the requirements.This document can also be found on github in this project.
 *
 * @author Tudor
 */

public class Main {
    public static void main(String[] args) throws IOException {

        Display display = new Display(new UserService(new UserDao()), new ProductService(new ProductDao()));

        display.displayWelcomeScreen();

    }
}
