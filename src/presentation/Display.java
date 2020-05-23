package presentation;

import servicemiddle.UserService;
import storage.Dao.UserDao.UserDao;
import storage.model.user.User;

import java.util.List;
import java.util.Scanner;

//Is calling the service layer when it needs to access data from the storage.
public class Display {
    private UserService userService;

    public void displayWelcomeScreen() {
        System.out.println("");
        System.out.print("                                                      !@!@!@! WELCOME TO THE R2D2 COMPUTER ACCESSORIES SHOP !@!@!@!\n\n");
        System.out.print("Please choose a user by inputing the number in order to access the program\n\n");

        List<User> users = userService.getUserDao().getUserList();
        for (User u : users) {
            System.out.println(u);
        }

        int input =userService.getUserInput();
        System.out.println(userService.searchUserById(input));

    }




    public Display(UserService userService) {
        this.userService = userService;
    }

}
