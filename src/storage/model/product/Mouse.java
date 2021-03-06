package storage.model.product;
/** Class representing one of the products the store sells.
 * @author Tudor
 */
public class Mouse extends Product {
    private String type;
    private int nrOfButtons;

    public Mouse() {
        super();
    }

    public Mouse(String type, int nrOfButtons) {

        this.type = type;
        this.nrOfButtons = nrOfButtons;
    }

    public Mouse(int barCode, String productType, String brand, String color, String connectivity, int quantity, double originalPrice, double retailPrice, String type, int nrOfButtons) {
        super(barCode, productType, brand, color, connectivity, quantity, originalPrice, retailPrice);
        this.type = type;
        this.nrOfButtons = nrOfButtons;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNrOfButtons() {
        return nrOfButtons;
    }

    public void setNrOfButtons(int nrOfButtons) {
        this.nrOfButtons = nrOfButtons;
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
        builder.append(this.getNrOfButtons());
        builder.append("\n");
        return builder.toString();
    }


}
