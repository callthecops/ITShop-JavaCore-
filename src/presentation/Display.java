package presentation;

import servicemiddle.ProductService;
import servicemiddle.UserService;
import storage.Dao.ProductDao.ProductDao;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;
import storage.model.product.ProductBrandComparator;
import storage.model.user.User;
import storage.model.user.admin.Admin;
import storage.model.user.customer.Customer;
import storage.model.user.customer.payment.CreditCard;
import storage.model.user.customer.payment.PayPal;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * I should have splitted the display class in 3 sepparate classes GeneralDisplay, AdminDisplay and CustomerDisplay but
 * i figured this out only after 70% of my program was already done.That way it would have been not so long.Not modifying it
 * would take to much time with no actual benefit for the application itself.
 * <p>
 * The class sole purpose is to function as a view for the application.A router is also called wich makes the class
 * completely decoupled from the dao and more or less decoupled from the service.
 *
 * @author Tudor
 */


//Is calling the service layer when it needs to access data from the storage.
public class Display {
    private final ViewModel viewModel;
    private final UserService userService;
    private final ProductService productService;
    private final Router router;


    public Display(UserService userService, ProductService productService) {
        this.viewModel = new ViewModel(this);
        this.productService = productService;
        this.userService = userService;
        this.router = new Router(this);

    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public ProductService getProductService() {
        return productService;
    }

    ///////////////////////////////////////////////////////////GENERAL VIEWS/////////////////////////////////////////////////////////////

    //General Screen for all users
    public void displayWelcomeScreen() {
        System.out.println(" ");
        System.out.print("                                                      !@!@!@! WELCOME TO THE R2D2 COMPUTER ACCESSORIES SHOP !@!@!@!\n\n");
        System.out.print("Please choose a user by inputing the number in order to access the program\n\n");

        List<User> users = userService.retrieveUsers();
        int number = 1;
        for (User u : users) {
            System.out.println(number + "." + u);
            number++;
        }
        System.out.println(number + ".Exit program.");

        int input = retrieveUserInput();
        //Exits the program.
        if (input == 5) {
            System.exit(0);
        }
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
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice <= 0 || userChoice > 5) {
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
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
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
        addProductToStock(choice, viewModel.getAdmin());
        router.redirectToWelcomeScreenOrToStock(1);
    }

    public int retrieveAdminInputFromThirdPanel() {
        int userChoice = 0;
        int userChoiceValid = 0;
        Scanner scanner = new Scanner(System.in);
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


    ////////ADD PRODUCT METHOD/////

    public void addProductToStock(int userChoice, Admin admin) throws IOException {
        ProductDao productDao = productService.getProductDao();
        switch (userChoice) {
            case 1:
                Keyboard keyboard = new Keyboard();
                int prodBarCode = 0, prodQuantity = 0;
                double origPrice = 0, retPrice = 0;
                boolean barCodeTest = true, typeTest = true, connectivityTest = true, layTest = true, prodQuantTest = true,
                        origPriceTest = true, retPriceTest = true;
                String brand = null, color = null, connectivity = null, keyType = null, layout = null;

                Scanner scanKey = new Scanner(System.in);
                //inputing barcode
                System.out.println("Please Enter Product BarCode");
                while (barCodeTest) {
                    try {
                        prodBarCode = Integer.parseInt(scanKey.nextLine());
                        barCodeTest = false;
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a number");
                    }
                }


                //inputing brand
                System.out.println("Please enter Product Brand");
                brand = scanKey.nextLine();


                //inputting Color
                System.out.println("Please enter Product Color");
                color = scanKey.nextLine();


                //inputing Connectivity
                System.out.println("Please enter Product Connectivity type:wireless or wired");
                while (connectivityTest) {
                    connectivity = scanKey.nextLine();
                    if (connectivity.equals("wireless") || connectivity.equals("wired")) {
                        connectivityTest = false;
                    } else {
                        System.out.println("Please enter 'wireless' or 'wired'");
                    }
                }

                //inputing quantity
                System.out.println("Please enter Product Quantity");
                while (prodQuantTest) {
                    try {
                        prodQuantity = Integer.parseInt(scanKey.nextLine());
                        prodQuantTest = false;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please enter a number");
                    }
                }

                //inputing origPrice
                System.out.println("Please enter original Price");
                while (origPriceTest) {
                    try {
                        origPrice = Double.parseDouble(scanKey.nextLine());
                        origPriceTest = false;
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a number");
                    }
                }
                //inputing retailPrice
                System.out.println("Please enter Product Retail Price");

                while (retPriceTest) {
                    try {
                        retPrice = Double.parseDouble(scanKey.nextLine());
                        retPriceTest = false;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please enter a number");
                    }
                }

                //inputing keyType
                System.out.println("Please enter Product Keyboard Type: gaming/internet/standard/flexible");
                while (typeTest) {
                    keyType = scanKey.nextLine();
                    if (keyType.equals("gaming") || keyType.equals("internet") ||
                            keyType.equals("standard") || keyType.equals("flexible")) {
                        typeTest = false;
                    } else {
                        System.out.println("Please enter one of the 4 options");
                    }
                }
                //inputing layout
                System.out.println("Please enter Product layout UK/US");
                while (layTest) {
                    layout = scanKey.nextLine();
                    if (layout.toUpperCase().equals("UK") || layout.toUpperCase().equals("US")) {
                        layTest = false;
                    } else {
                        System.out.println("Please enter one of the 2 options");
                    }
                }
                keyboard.setBarCode(prodBarCode);
                keyboard.setProductType("keyboard");
                keyboard.setBrand(brand);
                keyboard.setColor(color);
                keyboard.setConnectivity(connectivity);
                keyboard.setQuantity(prodQuantity);
                keyboard.setOriginalPrice(origPrice);
                keyboard.setRetailPrice(retPrice);
                keyboard.setType(keyType);
                keyboard.setLayout(layout);

                //CALL METHODS TO WRITING AND LOGGING
                productDao.writeKeyToFile(keyboard, admin);
                router.redirectToStockScreenAfterTheProductHasBeenAdded(productDao);
                break;
            case 2:
                Mouse mouse = new Mouse();

                int mouseBarCode = 0, mouseQuantity = 0, mouseButtonNr = 0;
                double mouseOrigPrice = 0, mouseRetPrice = 0;
                boolean mouseTypeTest = true, mouseConnectivityTest = true, mouseBarCodeTest = true, mouseQuantTest = true, mouseOrigPriceTest = true,
                        mouseRetPriceTest = true, mouseButtonNrTest = true;
                String mouseBrand = null, mouseColor = null, mouseConnectivity = null, mouseType = null;

                Scanner scanMouse = new Scanner(System.in);
                //inputing barcode

                //inputing barcode
                System.out.println("Please Enter Product BarCode");
                while (mouseBarCodeTest) {
                    try {
                        mouseBarCode = Integer.parseInt(scanMouse.nextLine());
                        mouseBarCodeTest = false;
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a number");
                    }
                }


                //inputing brand
                System.out.println("Please enter Product Brand");
                mouseBrand = scanMouse.nextLine();


                //inputting Color
                System.out.println("Please enter Product Color");
                mouseColor = scanMouse.nextLine();


                //inputing Connectivity
                System.out.println("Please enter Product Connectivity type:wireless or wired");
                while (mouseConnectivityTest) {
                    mouseConnectivity = scanMouse.nextLine();
                    if (mouseConnectivity.equals("wireless") || mouseConnectivity.equals("wired")) {
                        mouseConnectivityTest = false;
                    } else {
                        System.out.println("Please enter 'wireless' or 'wired'");
                    }
                }

                //inputing quantity
                System.out.println("Please enter Product Quantity");
                while (mouseQuantTest) {
                    try {
                        mouseQuantity = Integer.parseInt(scanMouse.nextLine());
                        mouseQuantTest = false;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please enter a number");
                    }
                }

                //inputing origPrice
                System.out.println("Please enter original Price");
                while (mouseOrigPriceTest) {
                    try {
                        mouseOrigPrice = Double.parseDouble(scanMouse.nextLine());
                        mouseOrigPriceTest = false;
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a number");
                    }
                }
                //inputing retailPrice
                System.out.println("Please enter Product Retail Price");

                while (mouseRetPriceTest) {
                    try {
                        mouseRetPrice = Double.parseDouble(scanMouse.nextLine());
                        mouseRetPriceTest = false;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please enter a number");
                    }
                }
                //inputing mouse type
                System.out.println("Please enter Product Mouse Type: standard/gaming");
                while (mouseTypeTest) {
                    mouseType = scanMouse.nextLine();
                    if (mouseType.equals("gaming") || mouseType.equals("standard")) {
                        mouseTypeTest = false;
                    } else {
                        System.out.println("Please enter one of the 2 options");
                    }
                }

                //inputing nr of buttons

                System.out.println("Please enter Product Nr of Buttons");
                while (mouseButtonNrTest) {
                    try {
                        mouseButtonNr = Integer.parseInt(scanMouse.nextLine());
                        mouseButtonNrTest = false;
                    } catch (NumberFormatException ex) {
                        System.out.println("Please enter a number");
                    }
                }
                mouse.setBarCode(mouseBarCode);
                mouse.setProductType("mouse");
                mouse.setBrand(mouseBrand);
                mouse.setColor(mouseColor);
                mouse.setConnectivity(mouseConnectivity);
                mouse.setQuantity(mouseQuantity);
                mouse.setOriginalPrice(mouseOrigPrice);
                mouse.setRetailPrice(mouseRetPrice);
                mouse.setType(mouseType);
                mouse.setNrOfButtons(mouseButtonNr);


                //CALL METHODS TO WRITING AND LOGGING
                productDao.writeMouseToFile(mouse, admin);
                router.redirectToStockScreenAfterTheProductHasBeenAdded(productDao);
                break;
        }
    }

    ///////////////////////////////////////////////////////////CUSTOMER VIEWS/////////////////////////////////////////////////////////////

    //////////////////////////////FIRST CUSTOMER DISPLAY AND INPUT////////////////////////////////

    public void displayCustomerPanel(Customer customer) {
        System.out.println("Welcome" + customer.getUserName() + "," + customer.getUserSurName() + ".Please make a Selection from the menu by pressing the coresponding key\n");
        System.out.println("1.Customer Panel\n");
        System.out.println("2.Back\n");

        int choice = retrieveCustomerInput();
        router.redirectToWelcomeScreenOrToCustomerPanel(choice);

    }

    public int retrieveCustomerInput() {
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 2) {
                    System.out.println("Please enter 1 or 2");
                } else {
                    return choice;
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
        return 0;
    }

    //////////////////////////////SECOND CUSTOMER DISPLAY AND INPUT////////////////////////////////

    public void displayCustomerPanelTwo(Customer customer) {
        System.out.println("This is your Customer Panel " + customer.getUserName() + "," + customer.getUserSurName());
        System.out.println("Please make your selection\n");
        System.out.println("1.Add Products To Basket.\n");
        System.out.println("2.Basket Options.\n");
        System.out.println("3.Display products by brand.\n");
        System.out.println("4.Back.\n");

        int choice = displayCustomerPanelTwoInput();
        router.multiCustomerRedirect(choice);

    }

    public int displayCustomerPanelTwoInput() {
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 4) {
                    System.out.println("Please enter the correct option");
                } else {
                    return choice;
                }
            } else {
                System.out.println("Please enter a number.");
            }
        }
        return 0;
    }

    //////////////////////////////THIRD CUSTOMER DISPLAY AND INPUT(ADD PRODUCTS TO BASKET)////////////////////////////////

    public void addProductsToBasket(List<Product> products) {
        System.out.println("This is our Stock.Please enter the Barcode for the product you want to purchase.\n");
        for (Product product : products) {
            if (product.getProductType().equals("keyboard")) {
                Keyboard keyboard = (Keyboard) product;
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Layout:%s|Quantity:%s|Price:%s",
                        keyboard.getBarCode(), keyboard.getProductType(), keyboard.getType(), keyboard.getBrand(), keyboard.getColor(),
                        keyboard.getConnectivity(), keyboard.getLayout(), keyboard.getQuantity(), keyboard.getRetailPrice() + "\n");
            } else {
                Mouse mouse = (Mouse) product;
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Nr of Buttons:%s|Quantity:%s|Price:%s",
                        mouse.getBarCode(), mouse.getProductType(), mouse.getType(), mouse.getBrand(), mouse.getColor(),
                        mouse.getConnectivity(), mouse.getNrOfButtons(), mouse.getQuantity(), mouse.getRetailPrice() + "\n");
            }
        }

        //STARTS A CHAIN OF EVENTS SHOWCASED BY THE "XXX IN ORDER "COMMENTS
        retrieveProductBarcode(products, viewModel.getCustomer());
    }

    //FIRST IN ORDER:This method retrieves the barcode that was inputed by the user.(COMPLEMENTS IN PRODUCT SERVICE)
    public void retrieveProductBarcode(List<Product> products, Customer customer) {
        System.out.println("Please enter Product barcode:");
        boolean test = true;
        while (test) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int barcodeInput = Integer.parseInt(sc.nextLine());
                test = false;
                checkIfBarcodeExists(barcodeInput, products, customer);
            } else {
                System.out.println("Please enter a number");
            }
        }
    }

    //SECOND IN ORDER:This method checks if barcode exists among the list.If it does it redirect to next method wich asks for quantity
    public void checkIfBarcodeExists(int barcodeInput, List<Product> products, Customer customer) {
        boolean doesExist = false;
        for (Product product : products) {
            if (barcodeInput == product.getBarCode()) {
                System.out.println("You have selected:|Barcode: " + product.getBarCode() + "|Type:" + product.getProductType() +
                        "|Brand: " + product.getBrand() + "|Quantity: " + product.getQuantity() + "|Connectivity: " +
                        product.getConnectivity() + "|Price: " + product.getRetailPrice());
                doesExist = true;
                retrieveCustomerQuantityInput(product, customer, products);
            }
        }
        if (doesExist == false) {
            System.out.println("The Barcode you have entered does not belong to any product, please enter again..");
            retrieveProductBarcode(products, customer);
        }
    }


    //THIRD IN ORDER:This method ask user for quantity of the product that he desires.Also Adds product to basket if
    //quantity matches the quantity in stock
    public void retrieveCustomerQuantityInput(Product product, Customer customer, List<Product> products) {
        System.out.println("Please enter quantity");
        int barCode;
        boolean test = true;
        while (test) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int quantity = Integer.parseInt(sc.nextLine());
                if (quantity > product.getQuantity() || quantity <= 0) {
                    System.out.println("The quantity is incorrect, please enter again.Product quantity is: " + product.getQuantity());
                } else {
                    //Adds product to the basket with the quantity the user inputed
                    productService.addProductToTheBasket(product, quantity, customer);
                    try {
                        barCode = product.getBarCode();
                        //Sets the quantity to the product stock to the remaning quantity after user added some to basket
                        productService.updateStock(barCode, quantity, product, products);
                        //SIXTH IN ORDER : rewrites the stock.txt file
                        productService.getProductDao().writeFileWithUpdatedStock(products);
                        //GOES BACK TO THE CUSTOMER 1,2,3,4 selection screen
                        displayCustomerPanelTwo(customer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
    }

    //////////////////////////////FORTH CUSTOMER DISPLAY AND INPUT(BASKET OPTIONS)////////////////////////////////

    public void displayBasketContentsAndShowOptions() {
        List<Product> products = viewModel.getCustomer().getBasket().getProducts();
        System.out.println("Your basket contains the following products:\n");
        for (Product product : products) {
            if (product.getProductType().equals("keyboard")) {
                Keyboard keyboard = (Keyboard) product;
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Layout:%s|Quantity:%s|Price:%s",
                        keyboard.getBarCode(), keyboard.getProductType(), keyboard.getType(), keyboard.getBrand(), keyboard.getColor(),
                        keyboard.getConnectivity(), keyboard.getLayout(), keyboard.getQuantity(), keyboard.getRetailPrice() + "\n");

            } else {
                Mouse mouse = (Mouse) product;
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Nr of Buttons:%s|Quantity:%s|Price:%s",
                        mouse.getBarCode(), mouse.getProductType(), mouse.getType(), mouse.getBrand(), mouse.getColor(),
                        mouse.getConnectivity(), mouse.getNrOfButtons(), mouse.getQuantity(), mouse.getRetailPrice() + "\n");
            }
        }
        System.out.println("Please choose one of the following options:\n");
        System.out.println("1.Buy");
        System.out.println("2.Save Basket For Later");
        System.out.println("3.Empty Basket");
        System.out.println("4.Back");
        int choice = displayCustomerPanelFourInput();
        router.basketOptionsRedirect(choice);
    }


    public int displayCustomerPanelFourInput() {
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choice = Integer.parseInt(scanner.nextLine());
                return choice;
            } else {
                System.out.println("Please enter a number");
            }
        }
        return 0;
    }

    //////////////////////EVERYTHING RELATED TO THE BUY PANEL HERE /////////////////////////////////

    public void displayCustomerBuyOptionPanel() {
        System.out.println("Please choose the option for payment:\n");
        System.out.println("1.Paypal\n");
        System.out.println("2.Credit Card\n");
        int choice = retrieveCustomerBuyOptionInput();
        router.redirectByPaymentOption(choice);
    }

    //customer input for first panel
    public int retrieveCustomerBuyOptionInput() {
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return Integer.parseInt(scanner.nextLine());
            } else {
                System.out.println("Please enter a number");
            }
        }
        return 0;
    }

    //panel representing paypal option and input

    public void displayBuyOptionPaypalPanel() {
        System.out.println("You have choosen Paypal to pay for your products,");
        System.out.println("please enter your Paypal email in order to finalize the transaction.\n");
        //Calling the service to execute buy and clear basket
        //productService.buyWithPaypal(getViewModel().getCustomer(), displayBuyOptionPaypalPanelInput());
        PayPal payPal = displayBuyOptionPaypalPanelInput();
        viewModel.getCustomer().setPayment(payPal);
        productService.buyWithPaypalOrCreditCard(viewModel.getCustomer());
        displayCustomerPanelTwo(getViewModel().getCustomer());
    }

    public PayPal displayBuyOptionPaypalPanelInput() {
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        PayPal payPal = new PayPal();
        payPal.seteMail(email);
        return payPal;
    }


    //panel representing creditcard option and input
    public void displayBuyOptionCreditCardPanel() {
        System.out.println("You have choosen Credit Card to pay for your products,");
        System.out.println("please enter your Credit Card number followed by the Security code\n");
        long cardNumber = retrieveCustomerCardNumber();
        short securityNumber = retrieveCustomerSecurityNumber();
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(cardNumber);
        creditCard.setSecurityCode(securityNumber);
        viewModel.getCustomer().setPayment(creditCard);
        productService.buyWithPaypalOrCreditCard(viewModel.getCustomer());
        displayCustomerPanelTwo(getViewModel().getCustomer());
    }

    public long retrieveCustomerCardNumber() {
        System.out.println("Please enter card number..");
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return Integer.parseInt(scanner.nextLine());
            } else {
                System.out.println("Please enter number");
            }
        }
        return 0;
    }

    public short retrieveCustomerSecurityNumber() {
        System.out.println("Please enter security number..");
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return Short.parseShort(scanner.nextLine());
            } else {
                System.out.println("Please enter a number");
            }
        }
        return 0;
    }

    //////////////////////////////FIFTH CUSTOMER DISPLAY AND INPUT(SORT BY BRAND)////////////////////////////////

    public void displayCustomerSortByBrandPanel() {
        System.out.println("All the products sorted by brand:\n");
        List<Product> products = productService.getProductDao().getProductList();
        Collections.sort(products, new ProductBrandComparator());
        for (Product prod : products) {
            if (prod.getProductType().equals("keyboard")) {
                Keyboard keyboard = (Keyboard) prod;
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Layout:%s|Quantity:%s|Price:%s",
                        keyboard.getBarCode(), keyboard.getProductType(), keyboard.getType(), keyboard.getBrand(), keyboard.getColor(),
                        keyboard.getConnectivity(), keyboard.getLayout(), keyboard.getQuantity(), keyboard.getRetailPrice() + "\n");

            } else {
                Mouse mouse = (Mouse) prod;
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Nr of Buttons:%s|Quantity:%s|Price:%s",
                        mouse.getBarCode(), mouse.getProductType(), mouse.getType(), mouse.getBrand(), mouse.getColor(),
                        mouse.getConnectivity(), mouse.getNrOfButtons(), mouse.getQuantity(), mouse.getRetailPrice() + "\n");
            }
        }
        System.out.println("1.Back");
        int choice = displayCustomerSortByBrandPanelInput();
        router.redirectFromBrandDisplayToCustomerPanelTwo();
    }

    public int displayCustomerSortByBrandPanelInput() {
        boolean test = true;
        while (test) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    return choice;
                } else {
                    System.out.println("Please enter 1");
                }
            } else {
                System.out.println("Please enter a number");
            }
        }
        return 0;
    }

}


