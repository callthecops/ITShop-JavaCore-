package storage.model.product;

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
}
