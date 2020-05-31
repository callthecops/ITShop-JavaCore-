package storage.model.user;
// TO ADD
//enum UserRole {
//    ADMIN(" admin"),CUSTOMER(" user");
//
//    public String userRole;
//
//    private UserRole(String userRole){
//        this.userRole = userRole;
//    }
//}

/** Parent Class of Customer and Admin
 * @author Tudor
 */

public class User {
    private int userId;
    private String userName;
    private String userSurName;
    private Address address;
    private String role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "|ID: " + getUserId() + "|UserName: " + getUserName() + "|User Name: " + getUserSurName() +" |Address: " + getAddress().getHouseNr() + " " + getAddress().getPostCode()
                + " " + getAddress().getCity() + " " + "|Role: " + getRole()+"\n";
    }


}