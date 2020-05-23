package servicemiddle;

import storage.Dao.UserDao.UserDao;
import storage.model.user.User;

import java.util.List;
import java.util.Scanner;

//Sole purposs of this class is to act as an intermediary between the view layer(presentation) and the storage layer
//This means that we call this class from the view and this class calls the storage.All this is done by adding
//the class wich we will use in the controller so that this class is initialized with all the contents of the other class.
//As such when we create a UserService class and pass it a new UserDao argument, the UserService will ne able to access
//all the user data that the userdao has parsed from the text file and as such also pass it further to the presentation layer.
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    // takes input for the 4 users available from the user
    public int getUserInput() {
        int userChoice = 0;
        boolean test = true;
        while (test) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                userChoice = Integer.parseInt(sc.nextLine());
                if (userChoice < 0 || userChoice > 4) {
                    System.out.println("Please enter the correct number ");
                } else {
                    return userChoice;
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
        return -1;
    }

    //Method that takes the user input of 1,2,3,4 when choosing a user in the starting menu and selects the coresponding user form the list.
    public User searchUserById(int userInputNumber) {
        int control = 100;
        int userId = control + userInputNumber;
        User user = new User();
        for (User u : userDao.getUserList()) {
            if (u.getUserId() == userId) {
                user = u;
            }
        }
        return user;
    }


}
