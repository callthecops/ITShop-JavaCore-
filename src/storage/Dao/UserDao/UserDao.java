package storage.Dao.UserDao;

import storage.model.user.Address;
import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//This class implements the Dao method.It also parses and assigns at initialisation time a list of users.We are doing this so we have all the user data
//to use in other classes by using the UserDao as a instance variable.
public class UserDao implements Dao<User> {
    private List<User> userList ;


    public UserDao() throws IOException{
        this.userList = getAll(new File("src/UserAccounts.txt"));
    }



    //parses the text file and returns the list of users wich contains admin and customer objects
    @Override
    public List<User> getAll(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        List<User> userList = new ArrayList<>();
        while (scanner.hasNext()) {
            Address address = new Address();
            String[] users = scanner.nextLine().split(",");
            //Parsing txt file and setting userid,usernam,usersurname
            if (users[6].equals("admin")) {
                Admin admin = new Admin();
                admin.setUserId(Integer.parseInt(users[0]));
                admin.setUserName(users[1]);
                admin.setUserSurName(users[2]);
                //Seting address
                String numberCorrection = users[3];
                address.setHouseNr(Integer.parseInt(numberCorrection.substring(1, 3)));
                address.setPostCode(users[4]);
                address.setCity(users[5]);
                //Setting useraddress and role(admin, customer)
                admin.setAddress(address);
                admin.setRole(users[6]);
                userList.add(admin);
            } else {
                Customer customer = new Customer();
                customer.setUserId(Integer.parseInt(users[0]));
                customer.setUserName(users[1]);
                customer.setUserSurName(users[2]);
                //Setting address
                String numberCorrection = users[3];
                address.setHouseNr(Integer.parseInt(numberCorrection.substring(1, 3)));
                address.setPostCode(users[4]);
                address.setCity(users[5]);
                //Setting useraddress and role(admin, customer)
                customer.setAddress(address);
                customer.setRole(users[6]);
                userList.add(customer);
            }
        }
        return userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
