import presentation.Display;
import servicemiddle.ProductService;
import servicemiddle.UserService;
import storage.Dao.ProductDao.ProductDao;
import storage.Dao.UserDao.UserDao;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Display display = new Display(new UserService(new UserDao()),new ProductService(new ProductDao()));

        display.displayWelcomeScreen();

    }
}
