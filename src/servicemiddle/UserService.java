package servicemiddle;

import presentation.Display;
import storage.Dao.UserDao.UserDao;
import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;

import java.io.IOException;
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




    //Selects based on role of the user and calls one of the following 2 methods.Then it takes the user choice and
    public User redirectByRole(User user) {
        if (user.getRole().equals(" admin")) {
            Admin admin = new Admin();
            admin.setAddress(user.getAddress());
            admin.setRole(user.getRole());
            admin.setUserId(user.getUserId());
            admin.setUserName(user.getUserName());
            admin.setUserSurName(user.getUserSurName());
            System.out.println("ADMIN:");
            return admin;
        } else {
            // Redirect to customer user panel
            Customer customer = new Customer();
            customer.setAddress(user.getAddress());
            customer.setRole(user.getRole());
            customer.setUserId(user.getUserId());
            customer.setUserName(user.getUserName());
            customer.setUserSurName(user.getUserSurName());
            System.out.println("CUSTOMER");
            return customer;
        }
    }



}
