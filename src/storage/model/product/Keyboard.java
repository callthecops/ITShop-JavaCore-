package storage.model.product;


/** Class representing one of the products the store sells.
 * @author Tudor
 */
public class Keyboard extends Product {
    private String type;
    private String layout;

    public Keyboard() {
        super();
    }

    public Keyboard(String type, String layout) {
        this.type = type;
        this.layout = layout;
    }

    public Keyboard(int barCode, String productType, String brand, String color, String connectivity, int quantity, double originalPrice, double retailPrice, String type, String layout) {
        super(barCode, productType, brand, color, connectivity, quantity, originalPrice, retailPrice);
        this.type = type;
        this.layout = layout;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getBarCode()).append(", ");
        builder.append(this.getProductType()).append(", ");
        builder.append(this.getType()).append(", ");
        builder.append(this.getBrand()).append(", ");
        builder.append(this.getColor()).append(", ");
        builder.append(this.getConnectivity()).append(", ");
        builder.append(this.getQuantity()).append(", ");
        builder.append(this.getOriginalPrice()).append(", ");
        builder.append(this.getRetailPrice()).append(", ");
        builder.append(this.getLayout());
        builder.append("\n");
        return builder.toString();
    }


}
