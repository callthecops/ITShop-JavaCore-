package servicemiddle;

import storage.Dao.ProductDao.ProductDao;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;
import storage.model.user.customer.Customer;
import storage.model.user.customer.ShoppingBasket;
import storage.model.user.customer.payment.CreditCard;
import storage.model.user.customer.payment.PayPal;

import java.io.IOException;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> retrieveProducts() {
        return productDao.getProductList();
    }


    //FOURTH IN ORDER:THIS METHOD CHECKS IF THE PRODUCT IS KEYBOARD OR MOUSE AND ADDS IT TO THE BASKET
    public void addProductToTheBasket(Product product, int quantity, Customer customer) {
        List<Product> basketListOfProducts = customer.getBasket().getProducts();
        if (basketListOfProducts.isEmpty() || !basketListOfProducts.contains(product)) {
            if (product.getProductType().equals("keyboard")) {
                //We need 2 object references in memory for each Keyboard, one for the one that remains in the stock, and the
                //other for the one that is added to the basket we need to create a new object and assign only the values of the
                //other one without also assingin the old reference.
                Keyboard testKeyboard = (Keyboard) product;
                Keyboard keyboard = new Keyboard(testKeyboard.getBarCode(), testKeyboard.getProductType(),
                        testKeyboard.getBrand(), testKeyboard.getColor(), testKeyboard.getConnectivity(),
                        testKeyboard.getQuantity(), testKeyboard.getOriginalPrice(), testKeyboard.getRetailPrice(),
                        testKeyboard.getType(), testKeyboard.getLayout());
                keyboard.setQuantity(quantity);
                basketListOfProducts.add(keyboard);
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Layout:%s|Quantity:%s|Price:%s" + "has been added to the basket.\n",
                        keyboard.getBarCode(), keyboard.getProductType(), keyboard.getType(), keyboard.getBrand(), keyboard.getColor(),
                        keyboard.getConnectivity(), keyboard.getLayout(), keyboard.getQuantity(), keyboard.getRetailPrice() + "\n");
            } else {
                Mouse testMouse = (Mouse) product;
                Mouse mouse = new Mouse(testMouse.getBarCode(), testMouse.getProductType(), testMouse.getBrand(),
                        testMouse.getColor(), testMouse.getConnectivity(), testMouse.getQuantity(),
                        testMouse.getOriginalPrice(), testMouse.getRetailPrice(), testMouse.getType(),
                        testMouse.getNrOfButtons());
                mouse.setQuantity(quantity);
                basketListOfProducts.add(mouse);
                System.out.printf("|Barcode:%s|Type:%s|Product Type:%s|Brand:%s|Color:%s|Connectivity:%s|Nr of Buttons:%s|Quantity:%s|Price:%s" + "has been added to the basket.\n",
                        mouse.getBarCode(), mouse.getProductType(), mouse.getType(), mouse.getBrand(), mouse.getColor(),
                        mouse.getConnectivity(), mouse.getNrOfButtons(), mouse.getQuantity(), mouse.getRetailPrice() + "\n");

            }
        } else if (basketListOfProducts.contains(product)) {
            //This loops sole reason of existing is to not create new product objects if the item in the basket does alreayd
            //exist, but to just modify its quantity accordingly
            for (Product prod : basketListOfProducts) {
                if (prod.getBarCode() == product.getBarCode()) {
                    int productInBasketQuantity = prod.getQuantity();
                    productInBasketQuantity += quantity;
                    prod.setQuantity(productInBasketQuantity);
                }
            }
        }
    }

    //FIFTH IN ORDER:THIS METHOD UPDATES THE STOCK WITH THE REMAINING QUANTITY.IT IS CALLED BY THE ABOVE METHOD.
    //USED TOGETHER WITH ROUTER TO REDIRECT TO PREVIOUS SCREEN

    public void updateStock(int barCode, int quantity, Product product, List<Product> products) throws IOException {
        int rest = product.getQuantity() - quantity;
        for (Product prod : products) {
            if (prod.getBarCode() == barCode) {
                if (rest <= 0) {
                    products.remove(prod);
                    break;
                } else {
                    prod.setQuantity(rest);
                    break;
                }
            }
        }
    }

    //This method saves the basket for later by emptying and writing the objects to a file with the status "saved".
    public void saveBasketForLater(ShoppingBasket basket, Customer customer) {
        try {
            productDao.writeBasketContentsToText(basket, customer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        List<Product> products = customer.getBasket().getProducts();
        products.removeAll(products);

    }


    //This method empties the basket , returns the stock of the products and rewrites the file
    public void emptyCustomerBasket(Customer customer) throws IOException {
        List<Product> basketProducts = customer.getBasket().getProducts();
        List<Product> stockProducts = productDao.getProductList();

        for (Product basketProduct : basketProducts) {
            //This way i add the quantity from the basket back to the stock
            for (Product stockProduct : stockProducts) {
                int stockProductQuantityQuantity = stockProduct.getQuantity();
                if (basketProduct.getBarCode() == stockProduct.getBarCode()) {
                    int basketProductQuantity = basketProduct.getQuantity();
                    stockProductQuantityQuantity += basketProductQuantity;
                    stockProduct.setQuantity(stockProductQuantityQuantity);
                }
            }
        }
        //Writes logging to txt file
        productDao.writeBasketContentsToTextAfterEmptyOption(customer.getBasket(), customer);
        //removes products from basket
        basketProducts.removeAll(basketProducts);
        //rewrites stock file
        productDao.writeFileWithUpdatedStock(stockProducts);
    }

    ///////////////////////////////////////////BUY OPTION METHODS//////////////////////////////////////////////


    public void buyWithPaypalOrCreditCard(Customer customer) {
        List<Product> basketProducts = customer.getBasket().getProducts();
        double totalToPay = 0;
        for (Product product : basketProducts) {
            totalToPay += product.getRetailPrice();
        }
        if (customer.getPayment() instanceof PayPal) {
            PayPal payPal = (PayPal) customer.getPayment();
            System.out.println("Loading...\n");
            System.out.println("Your total ammount of: " + totalToPay + " has been payed with Paypal using " +
                    "the address: " + payPal.geteMail());
        } else {
            CreditCard creditCard = (CreditCard) customer.getPayment();
            System.out.println("Loading...\n");
            System.out.println("Your total ammount of: " + totalToPay + " has been payed with CreditCard using " +
                    "the card number: " + creditCard.getCardNumber());
        }
        try {
            productDao.writeBasketContentsToTextAfterPaying(customer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        basketProducts.removeAll(basketProducts);
    }
}
