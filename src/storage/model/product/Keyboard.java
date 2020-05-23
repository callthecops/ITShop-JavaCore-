package storage.model.product;

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

}
