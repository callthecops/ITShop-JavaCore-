package presentation;

import servicemiddle.ProductService;
import servicemiddle.UserService;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;
import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Is calling the service layer when it needs to access data from the storage.
public class Display {
    private final ViewModel viewModel;
    private final UserService userService;
    private final ProductService productService;
    private final Router router;
    private final Scanner scanner;

    public Display(UserService userService, ProductService productService) {
        this.viewModel = new ViewModel(this);
        this.productService = productService;
        this.userService = userService;
        this.router = new Router(this);
        this.scanner = new Scanner(System.in);
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    ///////////////////////////////////////////////////////////GENERAL VIEWS/////////////////////////////////////////////////////////////

    //General Screen for all users
    public void displayWelcomeScreen() {
        System.out.println(" ");
        System.out.print("                                                      !@!@!@! WELCOME TO THE R2D2 COMPUTER ACCESSORIES SHOP !@!@!@!\n\n");
        System.out.print("Please choose a user by inputing the number in order to access the program\n\n");

        List<User> users = userService.retrieveUsers();
        for (User u : users) {
            System.out.println(u);
        }

        int input = retrieveUserInput();

        //selecting the user based on the above user input
        User user = userService.searchUserById(input);
        User customerOrAdmin = userService.createByRole(user);
        //Adding the User to the ViewModel to be used further
        viewModel.differentiateRoles(customerOrAdmin);
        //First route as a defined User Type.
        router.redirectByRole(customerOrAdmin);

    }


    // takes input for the 4 users available from the user
    public int retrieveUserInput() {
        int userChoice = 0;
        boolean test = true;
        while (test) {
            if (scanner.hasNextInt()) {
                userChoice = Integer.parseInt(scanner.nextLine());
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

///////////////////////////////////////////////////////////ADMIN VIEWS/////////////////////////////////////////////////////////////




                            //////////////////////////////////FIRST ADMIN DISPLAY AND INPUT//////////////////////////////////

    //This is the first admin panel, it presents the admin with a welcome screen and takes admin input.

    //First admin panel - Welcomes admin
    public void displayAdminPanel() {
        System.out.println("Welcome " + viewModel.getAdmin().getRole() + " " + viewModel.getAdmin().getUserName() + ".Please make your choice.\n");
        System.out.println("1.View/Add Stock\n");
        System.out.println("2.Back\n");
        int choice = retrieveAdminInputFromFirstPanel();
        router.redirectToWelcomeScreenOrToStock(choice);

    }

    //Method handles admin input
    public int retrieveAdminInputFromFirstPanel() {
        boolean test = true;
        while (test) {
            if (scanner.hasNextInt()) {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    return choice;
                } else if (choice == 2) {
                    return choice;
                } else {
                    System.out.println("Please enter the correct number " + viewModel.getAdmin().getUserName());
                }
            } else {
                System.out.println("Please enter number");
            }

        }
        return 0;
    }
                                         //////////////////////////////////SECOND ADMIN DISPLAY AND INPUT//////////////////////////////////

    //This is the second admin panel, it presents all the stock in the format needed for the admin and takes second input.

    //Method sorts and displays Products for the admin in descending order by quantity.Displays all prices.
    public void displayProductsPanel() {
        List<Product> products = productService.retrieveProducts();
        Collections.sort(products);
        for (Product product : products) {
            if (product.getProductType().equals("mouse")) {
                Mouse mouse = (Mouse) product;
                System.out.println("Code: " + mouse.getBarCode() + "|Type: " + mouse.getProductType() + "|Brand: " + mouse.getBrand() + "|Color: " + mouse.getColor()
                        + "|Connectivity: " + mouse.getConnectivity() + "|Quantity: " + mouse.getQuantity() + "|Original Price: "
                        + mouse.getOriginalPrice() + "|Retail Price: " + mouse.getRetailPrice() + "|Type: " + mouse.getType()
                        + "| NrOfButtons: " + mouse.getNrOfButtons());
            } else {
                Keyboard keyboard = (Keyboard) product;
                System.out.println("Code: " + keyboard.getBarCode() + "|Type: " + keyboard.getProductType() + "|Brand: " + keyboard.getBrand() + "|Color: " + keyboard.getColor()
                        + "|Connectivity: " + keyboard.getConnectivity() + "|Quantity: " + keyboard.getQuantity() + "|Original Price: "
                        + keyboard.getOriginalPrice() + "|Retail Price: " + keyboard.getRetailPrice() + "|Type: " + keyboard.getType()
                        + "| Layout: " + keyboard.getLayout());
            }
        }
        System.out.println(" ");
        System.out.println("Please make your choice" + viewModel.getAdmin().getRole() + "" + viewModel.getAdmin().getUserName());
        System.out.println("1.Add Product\n");
        System.out.println("2.Back\n");


        //Retrieving users choice
        int choice = retrieveAdminInputFromSecondPanel();

        //Calling router method to redirect to a new admin panel
        router.redirectToWelcomeOrToAddingStock(choice);
    }

    //Retrieve the admin input (Add Product or Back)
    public int retrieveAdminInputFromSecondPanel() {
        int choice = 0;
        boolean control = true;
        while (control) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1) {
                    return choice;
                } else if (choice == 2) {
                    return choice;
                } else {
                    System.out.println("Please enter the correct number");
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
        return choice;
    }

                                     //////////////////////////////THIRD ADMIN DISPLAY AND INPUT////////////////////////////////


    public void displayAddStockSelection() throws IOException {
        System.out.println("Please select the type of product you want to add !");
        System.out.println("1.Keyboard");
        System.out.println("2.Mouse");

        int choice = retrieveAdminInputFromThirdPanel();

        //ADDS NEW PRODUCT TO STOCK,ADDS DATA TO Stock.txt and AdminModifications.txt
        productService.addProductToStock(choice,viewModel.getAdmin());
        router.redirectToWelcomeScreenOrToStock(1);
    }

    public int retrieveAdminInputFromThirdPanel(){
        int userChoice = 0;
        int userChoiceValid=0;
        if (scanner.hasNextInt()) {
            userChoice = scanner.nextInt();
            if (userChoice == 1 || userChoice == 2) {
                userChoiceValid = userChoice;
            } else {
                System.out.println("Please enter 1 or 2.");
            }
        } else {
            System.out.println("Please enter a number");
        }
        return userChoiceValid;
    }






    ///////////////////////////////////////////////////////////CUSTOMER VIEWS/////////////////////////////////////////////////////////////


    public void displayCustomerPanel(Customer customer) {
        System.out.println("Customer Panel");
    }



}
