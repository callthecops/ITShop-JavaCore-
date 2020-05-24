package servicemiddle;

import storage.Dao.ProductDao.ProductDao;
import storage.model.product.Keyboard;
import storage.model.product.Mouse;
import storage.model.product.Product;
import storage.model.user.admin.Admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

    public void addProductToStock(int userChoice, Admin admin) throws IOException {
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

                String keyString = keyboard.toString();
                String keyAdded = keyString + "|| was added by: " + "|UserName: " + admin.getUserName() + "|User Id:" + admin.getUserId() + "|User Role:" + admin.getRole();
                FileWriter fileWriter = new FileWriter("src/Stock.txt", true);
                FileWriter fileWriter2 = new FileWriter("src/AdminModifications.txt",true);
                fileWriter.write(keyString);
                fileWriter2.write(keyAdded);
                fileWriter.close();
                fileWriter2.close();
                System.out.println("Product has been added to stock");
                productDao.setProductList(productDao.getAll((new File("src/Stock.txt"))));
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

                String mouseString = mouse.toString();
                String mouseAdded = mouseString + "|| was added by: " + "|UserName: " + admin.getUserName() + "|User Id:" + admin.getUserId() + "|User Role:" + admin.getRole();
                FileWriter fileWriter3 = new FileWriter("src/Stock.txt", true);
                FileWriter fileWriter4 = new FileWriter("src/AdminModifications.txt", true);
                fileWriter3.write(mouseString);
                fileWriter4.write(mouseAdded);
                fileWriter3.close();
                fileWriter4.close();
                System.out.println("Product has been added to stock");
                productDao.setProductList(productDao.getAll((new File("src/Stock.txt"))));
                break;
        }
    }
}
