import presentation.Display;
import servicemiddle.UserService;
import storage.Dao.UserDao.UserDao;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Display display = new Display(new UserService(new UserDao()));

        display.displayWelcomeScreen();

    }
}